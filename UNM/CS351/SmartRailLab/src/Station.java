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
            catch (Exception e) {}
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
     * recvMessage()
     * This is where a the IMessagable receives a message and then is notified to process it.
     * @param message Message to be added to pendingMessages
     */
    public synchronized void recvMessage(Message message)
    {
        if (Main.DEBUG) System.out.println(this.toString() + " received a message. Message is: " + message.toString());
        pendingMessages.add(message);
        this.notify();
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
     * readMessage()
     * @param m message sent to this train from any other IMessagable object.
     * Observes what type of message m is and calls the appropriate method to respond to it, if implementation
     *          for that type of message has been written.
     * Implementations written for: SEARCH_FOR_ROUTE, RESERVE_ROUTE, WAIT_FOR_CLEAR_ROUTE, REQUEST_NEXT_TRACK,
     *          and ABORT_RESERVE_ROUTE
     */
    private void readMessage(Message m)
    {
        
        switch(m.type)
        {
            case SEARCH_FOR_ROUTE: readMessageSearchForRoute(m);
                break;
            case RESERVE_ROUTE: readMessageReserveRoute(m);
                break;
            case WAIT_FOR_CLEAR_ROUTE: readMessageWaitForClearRoute(m);
                break;
            case ABORT_RESERVE_ROUTE: if(Main.DEBUG) {System.out.println("RESERVE_ROUTE to "+this.toString()+" by request of "+m.TRAIN+" successfully aborted.");}
                break;
            case REQUEST_NEXT_TRACK: readMessageRequestNextTrack(m);
                break;
            default: if(Main.DEBUG) {System.out.println(toString()+ "received a message of type "+m.type.toString()+
            " for which there is no implementation.");}
                break;
        }
    }
    
    /**
     * readMessageReserveRoute()
     * @param m Message of MessageType.RESERVE_ROUTE
     * RESERVE_ROUTE
     *          Pops the next member on the route list. If it's a train, that means that a SEARCH_FOR_ROUTE from the
     *          train has come back as positive, and the Train that made this request is IN this station.
     *          The Station then makes a GO Message and sends it to the Train.
     *          If it is not a train, an error message is printed.
     */
    private void readMessageReserveRoute(Message m)
    {
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
    
    /**
     * readMessageWaitForClearRoute()
     * @param m Message of MessageType.WAIT_FOR_CLEAR_ROUTE
     * WAIT_FOR_CLEAR_ROUTE
     *          Forward the message to the train that SHOULD be next on the message list.
     *          If it's not a train next, print an error.
     */
    private void readMessageWaitForClearRoute(Message m)
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
    
    /**
     * readMessageRequestNextTrack()
     * @param m Message of MessageType.REQUEST_NEXT_TRACK
     * REQUEST_NEXT_TRACK
     *          Train requests a track.
     *          Pop the train, push yourself, and push your neighbor. Then send message back to Train.
     */
    private void readMessageRequestNextTrack(Message m)
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
    
    /**
     * readMessageSearchForRoute()
     * @param m Message of MessageType.SEARCH_FOR_ROUTE
     * SEARCH_FOR_ROUTE
     *          If the first sender is the train, send the message on.
     *          Else, it's from the Station's neighbor. Checks to see if the route is looking for this station.
     *          If m.STATION.equals(this.NAME), a route has been found. Switches message type to RESERVE_ROUTE
     *          and sends it to its neighbor.
     */
    private void readMessageSearchForRoute(Message m)
    {
        //If it's a train, send the message on.
        if (m.peekRouteList() instanceof Train)
        {
            if(m.STATION.equals(toString()))
            {
                // TODO: Afterwards send a no route found to the train.
                Notifications.updateUserAlert(m.TRAIN + " is already in " + toString()+ ".");
                sendMessage(new Message(MessageType.NO_ROUTE_FOUND, null , this,toString(), null));
            }
            else
            {
                m.pushRouteList(this);
                sendMessage(m, neighbor);
            }
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

    /**
     * printNeighborDebug()
     * @param mostRecentSender Sender that was not a neighbor that sent a message to this piece
     * @param messageType type of message received
     * Prints a debug message about which piece received a message from whom and of what type.
     * To be used when a track piece receives a message from another track piece that isn't a neighbor.
     */
    private void printNeighborDebug(IMessagable mostRecentSender, String messageType)
    {
        System.out.println(this.toString() + " just got a message (type " + messageType + ") from " + mostRecentSender + ", which is"
                + "not a neighbor. No message sent.");
    }

    /**
     * printNeighborError()
     * @param type type of message received
     * Prints a standard error message.
     * To be used when a track piece receives a message from another track piece that isn't a neighbor.
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

    

}
