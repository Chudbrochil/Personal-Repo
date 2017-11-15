import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Stations are placed at the end of lines and are destinations for Trains in the SmartRail simulation.
 * <p>
 * To use this class, you MUST set neighbor by calling setNeighbors() with ONLY ONE non-null Parameter. That non-null parameter
 * will determine which "side" the station knows it's on. For instance, if setNeigbhors(null, track1) is called,
 * the station will know that it is on the left most size of the tracks and that its neighobr lies to the right.
 * Thus, neighborSide gets set to Direction.LEFT.
 */
public class Station extends Thread implements IMessagable, IDrawable
{
    private String NAME;             //Default name of "Station#" given, or a name can be specified
    private static int stationIncrement = 1;   //Static int that gives the stations their ID's.
    private Queue<Message> pendingMessages = new ConcurrentLinkedQueue<>(); //list of all messages, held in order of receiving them, to be acknowledged.
    private IMessagable neighbor; //track piece the Station is connected to.
    private Direction neighborSide; //Determined by which neighbor is non-null in the method call to setNeighbors().

    private GraphicsContext gcDraw;
    private int canvasX;
    private int canvasY;
    private int side;

    /**
     * Station()
     * Station's constructor. This is a station that will serve as a loading point for trains and destination point
     * for trains. Station's are critical to messaging as this is where "GO" messages will be sent from as well as
     * termination for SEARCH_FOR_ROUTE and other critical messaging pieces.
     * @param name Name of the station.
     * @param gcDraw Graphics context to draw the station on the canvas
     * @param x x-coord of the station
     * @param y y-coord of the station
     */
    public Station(String name, GraphicsContext gcDraw, int x, int y)
    {
        NAME = "Station" + stationIncrement;
        stationIncrement++;
        NAME = name;
        this.gcDraw = gcDraw;
        canvasX = x;
        canvasY = y;
        side = 80;
    }

    /**
     * getCanvasX()
     * @return The int x-coord where this station is drawn
     */
    public int getCanvasX()
    {
        return canvasX;
    }

    /**
     * getCanvasY()
     * @return The int y-coord where this station is drawn
     */
    public int getCanvasY()
    {
        return canvasY;
    }

    /**
     * setNeighbors()
     * @param left  Station's neighbor if it is on the left. null otherwise
     * @param right Station's neighbor if it is on the right. null otherwise.
     *              This method MUST be called to use a Station.
     *              left or right MUST be null. The parameter that is not null will determine whether neighborSide
     *              is set to Direction.LEFT or Direction.RIGHT. This determines the train headings for the trains that are
     *              in the stations.
     */
    public void setNeighbors(IMessagable left, IMessagable right)
    {
        if (left != null)
        {
            neighbor = left;
            neighborSide = Direction.LEFT;
        }
        else
        {
            neighbor = right;
            neighborSide = Direction.RIGHT;
        }
    }

    /**
     * run()
     * Reads any messages in the queue
     */
    public void run()
    {
        while (true)
        {
            while (!pendingMessages.isEmpty())
            {
                readMessage(pendingMessages.poll());
            }
            try
            {
                wait();
            }
            catch (Exception e)
            {
            }
        }
    }

    /**
     * draw()
     * Draws the object on a canvas at location x,y according to its currrent state.
     */
    public void draw()
    {
        gcDraw.setFill(Color.WHITE);
        gcDraw.fillRect(canvasX + 10, canvasY - 20, side, side);
        gcDraw.setFill(Color.BLACK);
        gcDraw.fillText(this.toString(), canvasX + 30, canvasY + 30);
    }

    /**
     * toString()
     * @return String representing the name of the Station.
     */
    @Override
    public String toString()
    {
        return NAME;
    }

    /**
     * isInClickedArea()
     * @param x x-coord we are checking
     * @param y y-coord we are checking
     * @return True if the clicked spot is in the stations coordinates. False if not.
     */
    public boolean isInClickedArea(int x, int y)
    {
        if (x >= canvasX + 10 && x <= canvasX + 10 + side &&
                y >= canvasY - 20 && y <= canvasY - 20 + side)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    
    
    /**
     * TODO: document responses. XD
     *
     * readMessage()
     * ABORT_RESERVE_ROUTE
     *   Prints a debug statement
     * @param m
     */
    private void readMessage(Message m)
    {
        if (m.type == MessageType.SEARCH_FOR_ROUTE)
        {
            //If it's a train, send the message on.
            if (m.peekRouteList() instanceof Train)
            {
                m.pushRouteList(this);
                sendMessage(m, neighbor);
                //todo: How should we handle if a train requests a route to a station it's ON?
            }
            //If the first sender isn't a train, it must be a track and the message is coming in.
            else if (m.STATION.equals(this.NAME))
            {
                if (Main.DEBUG) System.out.println("Route to Station " + NAME + " has been found!");
                m.pushRouteList(this);
                m.type = MessageType.RESERVE_ROUTE;
                IMessagable mostRecentSender = m.getMostRecentSender();
                if (mostRecentSender == neighbor)
                {
                    m.popRouteList(); //Pop yourself off the route list. Still saved in the reverse list inside m.
                    m.popRouteList(); //Pop the neighbor you're about to send it to off the list. Still saved in the reverse list inside m.
                    sendMessage(m, neighbor);
                }
                else
                {
                    if (Main.DEBUG) printNeighborDebug(mostRecentSender, m.type.toString());
                    printNeighborError(m.type.toString());
                }
            }
            //todo: else, send a negative response?
        }
        else if (m.type == MessageType.RESERVE_ROUTE)
        {
            //If this message is received and the final sender is the train, then this is an answer to a SEARCH_FOR_ROUTE
            //message that the train that is IN this station

            //The first sender is who sent this message. Station doesn't care about that--just the Train it's going to.
            //***m.popRouteList();
            IMessagable nextSenderInList = m.popRouteList();
            if (nextSenderInList instanceof Train)
            {
                Message goMessage = new Message(MessageType.GO, ((Train) nextSenderInList).NAME, this, m.STATION, neighborSide);
                sendMessage(goMessage, nextSenderInList);
            }
            else
            {
                if (Main.DEBUG)
                    System.out.println(this.toString() + " just got a message (type " + m.type.toString() + ") whose next " +
                            "reference is " + nextSenderInList.toString() + ", which is not a train. No message sent.");
                System.err.println("RESERVE_ROUTE Message arrived at Station but next sender is not a Train.");
                return;
            }
        }

        //WAIT_FOR_CLEAR_ROUTE
        else if(m.type == MessageType.WAIT_FOR_CLEAR_ROUTE)
        {
            //Continue sending on the message in the direction it was going. This will eventually get to the train.
            IMessagable nextIMessagableToInform = m.popRouteList();
    
            if(nextIMessagableToInform instanceof Train) sendMessage(m, nextIMessagableToInform);
            else
            {
                if (Main.DEBUG) System.out.println(toString()+" received a message type "+m.type.toString()+" from "+m.getMostRecentSender().toString()+
                ", but the last RouteList variable was "+ nextIMessagableToInform.toString()+", which is not a Train.");
                System.err.println("Station received a message type "+ m.type.toString()+" but the next sender in the route list was not a Train.");
            }
        }
        
        else if (m.type == MessageType.ABORT_RESERVE_ROUTE)
        {
            if(Main.DEBUG) System.out.println("RESERVE_ROUTE to "+this.toString()+" by request of "+m.TRAIN+" successfully aborted.");
        }
        
        else if (m.type == MessageType.REQUEST_NEXT_TRACK)
        {
            //Should be the first request a train makes.
            if (m.peekRouteList() instanceof Train)
            {
                Train train = (Train) m.popRouteList();
                m.pushRouteList(this);
                m.pushRouteList(neighbor);
                sendMessage(m, train);
            }
            else
            {
                System.err.println(toString() + " got a message of type REQUEST_NEXT_TRACK from " + m.peekRouteList().toString()
                        + " is not a train.");
            }
        }
        
        //Does not implement ABORT_RESERVE_ROUTE.
    }

    /**
     * printNeighborDebug()
     * TODO: Comment this.
     * @param mostRecentSender
     * @param messageType
     */
    private void printNeighborDebug(IMessagable mostRecentSender, String messageType)
    {
        System.out.println(this.toString() + " just got a message (type " + messageType + ") from " + mostRecentSender + ", which is"
                + "not a neighbor. No message sent.");
    }

    /**
     * printNeighborError()
     * TODO: Comment this.
     * @param type
     */
    private void printNeighborError(String type)
    {
        System.err.println("Message passed from Rail piece to another that was not a neighbor. Message type: " + type);
    }
    
    /**
     * sendMessage()
     * @param message The Message to send
     * @param neighbor IMessagable to which to send the message.
     *
     * sets the mostRecentSender in message to this and then calls recvMessage(message) on neighbor.
     */
    private synchronized void sendMessage(Message message, IMessagable neighbor)
    {
        message.setMostRecentSender(this);
        message.setHeading(neighborSide);
        if (Main.DEBUG)
            System.out.println(this.toString() + " sending message to " + neighbor.toString() + ". Message is: " + message.toString());
        neighbor.recvMessage(message);
    }

    /**
     * recvMessage()
     * TODO: Comment this...
     * @param message
     */
    public synchronized void recvMessage(Message message)
    {
        if (Main.DEBUG) System.out.println(this.toString() + " received a message. Message is: " + message.toString());
        pendingMessages.add(message);
        this.notify();
    }

}
