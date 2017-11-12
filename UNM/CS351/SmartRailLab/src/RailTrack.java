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

    public RailTrack()
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
    }

    public RailTrack(RailLight trackLight)
    {
        this();
        this.trackLight = trackLight;
    }

    public RailTrack(GraphicsContext gcDraw, int x, int y)
    {
        this();
        this.gcDraw = gcDraw;
        canvasX = x;
        canvasY = y;
    }

    public RailTrack(RailLight trackLight, GraphicsContext gcDraw, int x, int y)
    {
        this(trackLight);
        this.gcDraw = gcDraw;
        canvasX = x;
        canvasY = y;
    }

    /**
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
     * @return true if this track has a light.
     */
    public boolean hasLight()
    {
        return trackLight == null;
    }

    /**
     * @return the direction in which the light is green.
     * Returns null if the track has no light.
     */
    public Direction trackLightGreenDirection()
    {
        if (hasLight()) return trackLight.getGreenDirection();
        else return null;
    }

    /**
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
     * @param m message
     *          input
     *          first message in pendingMessages.
     *          Any non-null Message.
     *          <p>
     *          Parses and acts on the given Message.
     *          <p>
     *          SEARCH_FOR_ROUTE
     *          Adds itself to the sender list.
     *          Checks who the message is from and forwards the message to its other neighbor.
     *          If it came from the left and the message is going to the right but right is null, for example, the message
     *          just doesn't get sent anywhere.
     *          RESERVE_ROUTE
     *          Reserves itself and its light, if applicable.
     *          Then pops the next member off the sender list in Message m and forwards the message to that Rail component
     *          IF it is a neighbor of this track. If it is not, an error message is printed and the message is dropped.
     *          Adds itself to the sender list, then sends it off.
     *          REQUEST_NEXT_TRACK
     *          Pulls train from the sender list
     *          Pops the next sender, which is the track the train was previously on
     *          Pushes itself and then the next track the train should be going to to the sender list
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
                //else... //todo: If we need to send a negative 'no route found' message back, we can do that here.
                //maybe the train only acts if it finds a route. Otherwise... It just sits? Maybe after a while it gets
                // a new destination. If we send emssages back we'd have to wait a set amount of time for all the answers
                //to 'come in' as well.
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
                m.type = MessageType.ABORT_RESERVE_ROUTE;
                m.popRouteList(); //pop yourself off so that you don't cause bugs.
                
                m.reverseRouteList();
                //will now go backwards to the Rail component that just sent this message.
                IMessagable nextIMessagableToInform = m.popRouteList();
                
                if(nextIMessagableToInform == leftNeighbor) sendMessage(m, leftNeighbor);
                else if(nextIMessagableToInform == rightNeighbor) sendMessage(m, rightNeighbor);
                else
                {
                    if (Main.DEBUG) printNeighborDebug(nextIMessagableToInform, m.type.toString());
                    printNeighborError(m.type.toString());
                }

                //todo: WAIT_FOR_ROUTE send message back to train saying "TrainX is in your way. Wait and request the route again later."
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
        
        //ABORT_RESERVE_ROUTE
        else if(m.type == MessageType.ABORT_RESERVE_ROUTE)
        {
            if(reserved && trainReservedFor.equals(m.TRAIN))
            {
                unreserve();
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
