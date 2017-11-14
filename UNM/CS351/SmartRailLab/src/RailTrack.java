import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Anna Carey on 10/18/17
 * <p>
 * Class for all track pieces. Assumes a train can sit on it and it has neighbors.
 * Will extend thread. Run method will consist of checking for and reacting to messages.
 */

public class RailTrack extends Thread implements IMessagable, IDrawable
{

    private String NAME;                              // Formal name of the train, useful for trace
    private static int trackIncrement = 1;            // ID number for a given track piece
    private Queue<Message> pendingMessages = new ConcurrentLinkedQueue<>();  //list of all messages, held in order of receiving them, to be acknowledged.
    private IMessagable leftNeighbor = null;           //left neighbor 'this' can send and receive messages from
    private IMessagable rightNeighbor = null;          //right neighbor 'this' can send and receive messages from
    private static Image trackImg;                    // Image that we use to draw a track.
    private static Image reserveTrackImg;                // Image for a blue track, typically for reserved track.
    private RailLight trackLight;                     // Light that is affixed on a track.
    private boolean reserved;
    private String trainReservedFor = "";             //The Train name for which this route is reserved

    private GraphicsContext gcDraw;
    private int canvasX;
    private int canvasY;


    /**
     * railTrack()
     * RailTrack constructor.
     * @param gcDraw Graphics context to draw on
     * @param x x-coord of track
     * @param y y-coord of track
     */
    public RailTrack(GraphicsContext gcDraw, int x, int y)
    {
        NAME = "Track" + trackIncrement;
        trackIncrement++;
        // Doing this to save the resources from creating a million images for each track.
        if (trackImg == null)
        {
            trackImg = new Image("Track.png");
        }
        if (reserveTrackImg == null)
        {
            reserveTrackImg = new Image("Track-Reserve.png");
        }
        reserved = false;
        //this();
        this.gcDraw = gcDraw;
        canvasX = x;
        canvasY = y;
    }

    /**
     * railTrack()
     * RailTrack constructor with a light.
     * @param trackLight Light attached to the track.
     * @param gcDraw Graphics context to draw on
     * @param x x-coord of track
     * @param y y-coord of track
     */
    public RailTrack(RailLight trackLight, GraphicsContext gcDraw, int x, int y)
    {
        this(gcDraw, x, y);
        this.trackLight = trackLight;
    }

    /**
     * setNeighbors()
     * @param left  IMessagable piece to the left of this piece. Initialized at runtime.
     * @param right IMessagable piece to the right of this piece. Initialized at runtime.
     *              null if no neighbor or a IMessagable class to which 'this' can pass messages.
     */
    public void setNeighbors(IMessagable left, IMessagable right)
    {
        leftNeighbor = left;
        rightNeighbor = right;
    }

    /**
     * hasLight()
     * @return true if this track has a light.
     */
    public boolean hasLight()
    {
        return trackLight == null;
    }

    /**
     * TODO: Do we need this? I still don't get the two-direction facing lights...
     * trackLightGreenDirection()
     * @return the direction in which the light is green.
     * Returns null if the track has no light.
     */
    public Direction trackLightGreenDirection()
    {
        if (hasLight()) return trackLight.getGreenDirection();
        else return null;
    }

    /**
     * run()
     * Check pendingMessages. If it is empty, wait. (Notify is in the recMessage() method)
     */
    public void run()
    {
        while (true)
        {
            while (!pendingMessages.isEmpty())
            {
                readMessage(pendingMessages.poll());
            }
            //wait
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
        gcDraw.fillText(this.toString(), canvasX, canvasY);
        if (reserved)
        {
            gcDraw.drawImage(reserveTrackImg, canvasX, canvasY);
        }
        else
        {
            gcDraw.drawImage(trackImg, canvasX, canvasY);
        }
    }

    /**
     * recvMessage()
     * This is where a RailTrack receives a message and then notifies to process it.
     * @param message Message that was sent to railswitch
     */
    public synchronized void recvMessage(Message message)
    {
        if (Main.DEBUG) System.out.println(this.toString() + " received a message. Message is: " + message.toString());
        pendingMessages.add(message);
        this.notify();
    }

    @Override
    public String toString()
    {
        return NAME;
    }
    
    /**
     * reserve()
     * @param trainComingFrom Direction the train will approach this track from (RIGHT or LEFT). This is used to reserve
     *                        the light as green in the right direction.
     * @param trainName String train parameter of the Message m. Indicates which Train this track is reserved on behalf of.
     */
    private void reserve(Direction trainComingFrom, String trainName)
    {
        reserved = true;
        trainReservedFor = trainName;
        if (trackLight != null)
        {
            trackLight.reserve(trainComingFrom);
        }
    }
    
    /**
     * unreserve()
     * Unreserves the track to allow other trains to pass over it.
     *
     * reserved becomes false, trainReservedFor becomes null, and unreserves light if applicable.
     */
    private void unreserve()
    {
        reserved = false;
        trainReservedFor = "";
        if (trackLight != null)
        {
            trackLight.unreserve();
        }
    }

    /**
     * readMessage()
     * @param m message
     *          input
     *          first message in pendingMessages.
     *          Any non-null Message.
     *          <p>
     *          Parses and acts on the given Message.
     *          <p>
     *          SEARCH_FOR_ROUTE
     *          Adds itself to the route list.
     *          Checks who the message is from and forwards the message to its other neighbor.
     *          If it came from the left and the message is going to the right but right is null, for example, the message
     *          just doesn't get sent anywhere.
     *          RESERVE_ROUTE
     *          Checks if this RailTrack is already reserved. If it is, reverses the route list in m and changes the type to ABORT_RESERVE_ROUTE.
     *          If RailTrack is not reserved, it reserves itself and its light, if applicable.
     *          Then pops the next member off the route list in Message m and forwards the message to that Rail component
     *          IF it is a neighbor of this track. If it is not, an error message is printed and the message is dropped.
     *          WAIT_FOR_CLEAR_ROUTE
     *          Forwards the message to the next route list neighbor.
     *          ABORT_REVERSE_ROUTE
     *          If this track is reserved on behalf of the train who first made this message, unreserve yourself and
     *          pass on the message to the next track, one of your neighbors, that needs to be unreserved. (Obtained from the Route List.)
     *          REQUEST_NEXT_TRACK
     *          Pulls train from the route list
     *          Pops the next sender, which is the track the train was previously on
     *          Pushes itself and then the next track the train should be going to to the route list
     *          Sends the message back to the train.
     *          TRAIN_GOODBYE_UNRESERVE
     *          If the first sender is a train, it calls 'unreserve' on itself. To be sent when the train leaves the track.
     *          else, prints to System.err
     */
    private void readMessage(Message m)
    {
        //SEARCH_FOR_ROUTE
        if (m.type == MessageType.SEARCH_FOR_ROUTE)
        {
            IMessagable mostRecentSender = m.peekRouteList();
            IMessagable neighborToSendTo = null;
            m.pushRouteList(this); //sign before you pass it on.

            //look for which neighbor sent this message. Send this message to your other neighbors.

            //If the message came from your right, send it to your left, and vis versa.
            if (mostRecentSender == leftNeighbor || mostRecentSender == rightNeighbor)
            {
                if (mostRecentSender == this.leftNeighbor)
                {
                    m.setHeading(Direction.RIGHT);
                    neighborToSendTo = rightNeighbor;
                }
                if (mostRecentSender == this.rightNeighbor)
                {
                    m.setHeading(Direction.LEFT);
                    neighborToSendTo = leftNeighbor;
                }
                if (neighborToSendTo != null)
                {
                    sendMessage(m, neighborToSendTo);
                    //Only one instance of this message needed because only one instance is being sent out.
                }
            }
            else
            {
                if (Main.DEBUG) printNeighborDebug(mostRecentSender, m.type.toString());
                printNeighborError(m.type.toString());
            }
        }

        //RESERVE_ROUTE
        else if (m.type == MessageType.RESERVE_ROUTE)
        {
            //If the track is already reserved, this new route must be aborted and tried again later.
            if(reserved)
            {
                Message waitMessage = m.clone();
                
                //ABORT_RESERVE_ROUTE message
                m.type = MessageType.ABORT_RESERVE_ROUTE;
                m.reverseRouteList();
                m.popRouteList(); //pop yourself off so that you don't cause bugs.
                
                //will now go backwards to the Rail component that just sent this message.
                IMessagable nextIMessagableToInform = m.popRouteList();
                
                if(nextIMessagableToInform == leftNeighbor) sendMessage(m, leftNeighbor);
                else if(nextIMessagableToInform == rightNeighbor) sendMessage(m, rightNeighbor);
                else
                {
                    if (Main.DEBUG) printNeighborDebug(nextIMessagableToInform, m.type.toString());
                    printNeighborError(m.type.toString());
                }
                
                //WAIT_FOR_CLEAR_ROUTE message
                waitMessage.type = MessageType.WAIT_FOR_CLEAR_ROUTE;
                //Continue sending on the message in the direction it was going. This will eventually get to the train.
                nextIMessagableToInform = waitMessage.popRouteList();
                
                if(nextIMessagableToInform == leftNeighbor) sendMessage(waitMessage, leftNeighbor);
                else if(nextIMessagableToInform == rightNeighbor) sendMessage(waitMessage, rightNeighbor);
                else
                {
                    if (Main.DEBUG) printNeighborDebug(nextIMessagableToInform, waitMessage.type.toString());
                    printNeighborError(waitMessage.type.toString());
                }
                return;
            }

            //Actually pop the sender this time. It will be either the right or left neighbor.
            IMessagable nextIMessagableToReserve = m.popRouteList();
            if (nextIMessagableToReserve == leftNeighbor)
            {
                //the train will be coming from the left to the right; The light should be green facing the left.
                reserve(Direction.LEFT, m.TRAIN);
                m.setHeading(Direction.LEFT);
                sendMessage(m, leftNeighbor);
            }
            else if (nextIMessagableToReserve == rightNeighbor)
            {
                reserve(Direction.RIGHT, m.TRAIN);
                m.setHeading(Direction.RIGHT);
                sendMessage(m, rightNeighbor);
            }
            else
            {
                if (Main.DEBUG) printNeighborDebug(nextIMessagableToReserve, m.type.toString());
                printNeighborError(m.type.toString());
            }
        }
        
        //WAIT_FOR_CLEAR_ROUTE
        else if(m.type == MessageType.WAIT_FOR_CLEAR_ROUTE)
        {
            //Continue sending on the message in the direction it was going. This will eventually get to the train.
            IMessagable nextIMessagableToInform = m.popRouteList();
    
            if(nextIMessagableToInform == leftNeighbor) sendMessage(m, leftNeighbor);
            else if(nextIMessagableToInform == rightNeighbor) sendMessage(m, rightNeighbor);
            else
            {
                if (Main.DEBUG) printNeighborDebug(nextIMessagableToInform, m.type.toString());
                printNeighborError(m.type.toString());
            }
        }
        
        //ABORT_RESERVE_ROUTE
        else if(m.type == MessageType.ABORT_RESERVE_ROUTE)
        {
            //todo: implement heading?
            if(reserved && trainReservedFor.equals(m.TRAIN))
            {
                unreserve();
                //should be the next track to be unreserved
                IMessagable nextIMessagableToInform = m.popRouteList();
                if(nextIMessagableToInform == leftNeighbor) sendMessage(m, leftNeighbor);
                else if(nextIMessagableToInform == rightNeighbor) sendMessage(m, rightNeighbor);
                else
                {
                    if (Main.DEBUG) printNeighborDebug(nextIMessagableToInform, m.type.toString());
                    printNeighborError(m.type.toString());
                }
            }
            else
            {
                if(!reserved)
                {
                    if (Main.DEBUG) System.out.println(this.toString() + " received an ABORT_RESERVE_ROUTE from " +
                        m.getMostRecentSender().toString() + "while unreserved. No message sent.");
                    System.err.println(this.toString() + " received an ABORT_RESERVE_ROUTE when unreserved.");
                }
            }
        }

        //REQUEST_NEXT_TRACK
        else if (m.type == MessageType.REQUEST_NEXT_TRACK)
        {
            if (m.peekRouteList() instanceof Train)
            {
                Train train = (Train) m.popRouteList();
                IMessagable trainPrevTrack = m.popRouteList();
                IMessagable nextForTrain = null;
                if (trainPrevTrack == leftNeighbor)
                {
                    nextForTrain = rightNeighbor;
                    m.setHeading(Direction.RIGHT);
                }
                else if (trainPrevTrack == rightNeighbor)
                {
                    nextForTrain = leftNeighbor;
                    m.setHeading(Direction.LEFT);
                }
                else
                {
                    System.err.println(toString() + "got a request from a train that didn't just come from its neighbor.");
                }
                m.pushRouteList(this);
                m.pushRouteList(nextForTrain);
                sendMessage(m, train);
            }
            else
            {
                System.err.println(toString() + " got a message of type REQUEST_NEXT_TRACK from " + m.peekRouteList().toString()
                        + " is not a train.");
            }
        }
        else if(m.type == MessageType.TRAIN_GOODBYE_UNRESERVE)
        {
            if(m.peekRouteList() instanceof Train)
            {
                unreserve();
            }
            else
            {
                System.err.println(toString()+ "got a message of type TRAIN_GOODBYE_UNRESERVE from "+
                    m.peekRouteList().toString()+", which is not a Train.");
            }
        }
    }

    /**
     * printNeighborDebug()
     * @param mostRecentSender Neighbor who just sent the message
     * @param messageType      Type of message that went wrong
     *                         <p>
     *                         This method prints a debug statement "this.toString just got a message type messageType from mostRecentSender,
     *                         which is not a neighbor. No message sent."
     */
    private void printNeighborDebug(IMessagable mostRecentSender, String messageType)
    {
        System.out.println(this.toString() + " just got a message (type " + messageType + ") from " + mostRecentSender + ", which is"
                + " not a neighbor. No message sent.");
    }

    /**
     * printNeighborError()
     * @param type Type of message that went wrong
     *             <p>
     *             This method prints a System err statement "Message passed from Rail peice to another that was not a neighbor.
     *             Message type: type."
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
        if (Main.DEBUG)
            System.out.println(this.toString() + " sending message to " + neighbor.toString() + ". Message is: " + message.toString());
        neighbor.recvMessage(message);
    }

}
