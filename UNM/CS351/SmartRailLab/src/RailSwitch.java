import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Anna Carey
 * <p>
 * This switch has two neighbors on the left and one neighbor on the right. It is drawn to go UP and straight across.
 * <p>
 * To use this class, you MUST call setNeighbors with all four parameters OR setNeighbors with two parameters
 * and setSwitchNeighbor to set its neighbor and a direction.
 */
public class RailSwitch extends Thread implements IMessagable, IDrawable
{
    public final String NAME;
    private static int switchIncrement = 1;
    private Queue<Message> pendingMessages = new ConcurrentLinkedQueue<>(); //list of all messages, held in order of receiving them, to be acknowledged.
    IMessagable leftNeighbor;      //any track piece
    IMessagable rightNeighbor;     //any track piece
    IMessagable switchNeighbor;    //another switch with the opposite Direction switchSide
    private Direction switchSide;  //if LEFT, this is a 'down' switch. if RIGHT, this is a 'up' switch.
    //This also determines whiether the 'switch' neighbor functions as a second
    //right neighbor or a second left neighbor.
    private boolean switchEngaged; //is the switch currently connected to its other switch neighbor, or lying flat left to right?
    private boolean reserved;
    private String trainReservedFor = ""; //The Train name for which this route is reserved

    private GraphicsContext gcDraw;
    private int canvasX;
    private int canvasY;

    private RailLight trackLight;

    private static Image switchImg;
    private static Image trackImg;
    private static Image reserveTrackImg;
    private static Image switchRegularReserveImg;
    private static Image switchDiagonalReserveImg;

    /**
     * RailSwitch constructor
     * @param trackLight Light that is attached to a given switch
     * @param gcDraw Graphics context to draw on
     * @param x x-coord to draw on
     * @param y y-coord to draw on
     * @param switchSide Direction side of the switch the connection is on.
     */
    public RailSwitch(RailLight trackLight, GraphicsContext gcDraw, int x, int y, Direction switchSide)
    {
        NAME = "RailSwitch" + switchIncrement;
        switchIncrement++;
        switchEngaged = false;
        reserved = false;
        if (switchImg == null)
        {
            switchImg = new Image("Switch.png");
        }
        if (trackImg == null)
        {
            trackImg = new Image("Track.png");
        }
        if (reserveTrackImg == null)
        {
            reserveTrackImg = new Image("Track-Reserve.png");
        }
        if (switchRegularReserveImg == null)
        {
            switchRegularReserveImg = new Image("Switch-RegularReserve.png");
        }
        if (switchDiagonalReserveImg == null)
        {
            switchDiagonalReserveImg = new Image("Switch-DiagonalReserve.png");
        }
        this.trackLight = trackLight;

        this.gcDraw = gcDraw;
        this.switchSide = switchSide;
        canvasX = x;
        canvasY = y;


        if (switchSide == Direction.RIGHT || switchSide == Direction.UPRIGHT)
        {
            this.switchSide = Direction.UPRIGHT;
        }
        else
        {
            this.switchSide = Direction.DOWNLEFT;
        }
    }

    public void draw()
    {
        // Various ways in which a switch should paint itself.
        if (switchSide == Direction.UPRIGHT && switchEngaged && reserved)
        {
            gcDraw.drawImage(switchDiagonalReserveImg, canvasX, canvasY - 70);
        }
        else if (switchSide == Direction.UPRIGHT && reserved)
        {
            gcDraw.drawImage(switchRegularReserveImg, canvasX, canvasY - 70);
        }
        else if (switchSide == Direction.UPRIGHT)
        {
            gcDraw.drawImage(switchImg, canvasX, canvasY - 70);
        }
        // A DOWNLEFT switch is drawn as a regular track
        else if (switchSide == Direction.DOWNLEFT && reserved)
        {
            gcDraw.drawImage(reserveTrackImg, canvasX, canvasY);
        }
        else if (switchSide == Direction.DOWNLEFT)
        {
            gcDraw.drawImage(trackImg, canvasX, canvasY);
        }

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
     * recvMessage()
     * This is where a RailSwitch receives a message and then notifies to process it.
     * @param message Message that was sent to railswitch
     */
    public synchronized void recvMessage(Message message)
    {
        if (Main.DEBUG) System.out.println(this.toString() + " received a message. Message is: " + message.toString());
        pendingMessages.add(message);
        this.notify();
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
     * setSwitchNeighbor()
     * @param switchN IMessagable piece above this switch.
     */
    public void setSwitchNeighbor(IMessagable switchN)
    {
        switchNeighbor = switchN;
    }

    @Override
    public String toString()
    {
        return NAME;
    }

    /**
     * reserve()
     * @param switchEng Whether the train needs the switch engaged or not.
     *    Engages the switch, reserves the light.
     * @param trainComingFrom Direction the train will come from. I.e, direction light should shine green.
     * @param trainName String train parameter of the Message m. Indicates which Train this track is reserved on behalf of.
     */
    private void reserve(boolean switchEng, Direction trainComingFrom, String trainName)
    {
        switchEngaged = switchEng;
        reserved = true;
        trainReservedFor = trainName;
        trackLight.reserve(switchEng, trainComingFrom);
    }

    /**
     * unreserve()
     * Unreserves this switch for future use by other trains.
     */
    private void unreserve()
    {
        reserved = false;
        trainReservedFor = "";
        trackLight.unreserve();
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
     *          Checks who the message is from and forwards the message to its other neighbor or neighbors.
     *          If it came from the left, the switch sends it to its two right neighbors.
     *          If it came from either of the right neighbors, it sends it to the left neighbor.
     *          If it came from the left and the message is going to the right but right is null, for example, the message
     *          just doesn't get sent anywhere.
     *          RESERVE_ROUTE
     *          Checks if this RailSwitch is already reserved. If it is, reverses the route list in m and changes the type to ABORT_RESERVE_ROUTE.
     *          If RailSwitch is not reserved, it reserves itself and its light, if applicable.
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
        /** This code labels the left and right neighbors to make the following code module for different switch types (up or down).**/
        IMessagable switchSideOtherNeighbor;
        IMessagable aloneNeighbor;

        //Find out which side the 'switchNeighbor' is on. Adjust which neighbor is its 'partner' in direction accordingly.
        if (switchSide == Direction.UPRIGHT)
        {
            switchSideOtherNeighbor = rightNeighbor;
            aloneNeighbor = leftNeighbor;
        }
        else //switchSide == Direction.DOWNLEFT
        {
            switchSideOtherNeighbor = leftNeighbor;
            aloneNeighbor = rightNeighbor;
        }

        /** Begin message typing **/
        if (m.type == MessageType.SEARCH_FOR_ROUTE)
        {
            //look for which neighbor sent this message. Send this message to your other neighbor or neighbors.
            IMessagable mostRecentSender = m.peekRouteList();
            m.pushRouteList(this); //sign the sender list before you pass it on.

            //Now actually find out which way the message came from and send it on its way.
            if (mostRecentSender == switchSideOtherNeighbor || mostRecentSender == switchNeighbor)
            {
                if (aloneNeighbor != null)
                {
                    m.setHeading(getNeighborSide(aloneNeighbor));
                    sendMessage(m, aloneNeighbor);
                }
            }
            else if (mostRecentSender == aloneNeighbor)
            {
                Message mClone = m.clone();
                if (switchSideOtherNeighbor != null)
                {
                    m.setHeading(getNeighborSide(switchSideOtherNeighbor));
                    sendMessage(m, switchSideOtherNeighbor);
                }
                if (switchNeighbor != null)
                {
                    m.setHeading(switchSide);
                    sendMessage(mClone, switchNeighbor);
                }
            }
            else
            {
                if (Main.DEBUG) printNeighborDebug(mostRecentSender, m.type.toString());
                printNeighborError(m.type.toString());
            }
        }
        
        else if (m.type == MessageType.RESERVE_ROUTE)
        {
            if(reserved)
            {
                Message waitMessage = m.clone();
                
                //ABORT_ROUTE message
                m.type = MessageType.ABORT_RESERVE_ROUTE;
                m.reverseRouteList();
                m.popRouteList(); //pop yourself off so that you don't cause bugs.
                //will now go backwards to the Rail component that just sent this message.
                IMessagable nextIMessagableToInform = m.popRouteList();
        
                if(nextIMessagableToInform == leftNeighbor) sendMessage(m, leftNeighbor);
                else if(nextIMessagableToInform == rightNeighbor) sendMessage(m, rightNeighbor);
                else if(nextIMessagableToInform == switchNeighbor) sendMessage(m, switchNeighbor);
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
                else if(nextIMessagableToInform == switchNeighbor) sendMessage(waitMessage, switchNeighbor);
                else
                {
                    if (Main.DEBUG) printNeighborDebug(nextIMessagableToInform, waitMessage.type.toString());
                    printNeighborError(waitMessage.type.toString());
                }
                
                return;
            }

            //Actually pop the sender this time. It will be either the right or left neighbor, if this was done correctly.
            IMessagable cameFrom = m.getMostRecentSender(); //RailSwitch cares who it came from.
            IMessagable goingTo = m.popRouteList(); //Also cares where it's going
            
            if (cameFrom == aloneNeighbor)
            {
                if (goingTo == switchSideOtherNeighbor)
                {
                    //don't need the switch neighbor
                    reserve(false, getNeighborSide(switchSideOtherNeighbor), m.TRAIN);
                    m.setHeading(getNeighborSide(switchSideOtherNeighbor));
                    sendMessage(m, switchSideOtherNeighbor);
                }
                else if (goingTo == switchNeighbor)
                {
                    reserve(true, switchSide, m.TRAIN);
                    m.setHeading(switchSide);
                    sendMessage(m, switchNeighbor);
                }
                else
                {
                    System.err.println(toString() + " cannot reserve for the neighbor " + goingTo.toString() + " it received.");
                }
            }
            else if (cameFrom == switchSideOtherNeighbor)
            {
                if (goingTo == aloneNeighbor)
                {
                    reserve(false, getNeighborSide(aloneNeighbor), m.TRAIN);
                    m.setHeading(getNeighborSide(aloneNeighbor));
                    sendMessage(m, aloneNeighbor);
                }
                else
                {
                    System.err.println(toString() + " cannot reserve for the neighbor " + goingTo.toString() + " it received.");
                }
            }
            else if (cameFrom == switchNeighbor)
            {
                if (goingTo == aloneNeighbor)
                {
                    reserve(true, switchSide, m.TRAIN);
                    m.setHeading(getNeighborSide(switchNeighbor));
                    sendMessage(m, aloneNeighbor);
                }
                else
                    System.err.println(toString() + " cannot reserve for the neighbor " + goingTo.toString() + " it received.");
            }
            else
            {
                if (Main.DEBUG) printNeighborDebug(cameFrom, m.type.toString());
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
            else if(nextIMessagableToInform == switchNeighbor) sendMessage(m, switchNeighbor);
            else
            {
                if (Main.DEBUG) printNeighborDebug(nextIMessagableToInform, m.type.toString());
                printNeighborError(m.type.toString());
            }
        }
        
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
                else if(nextIMessagableToInform == switchNeighbor) sendMessage(m, switchNeighbor);
                else
                {
                    if (Main.DEBUG) printNeighborDebug(m.getMostRecentSender(), m.type.toString());
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
        
        else if (m.type == MessageType.REQUEST_NEXT_TRACK)
        {
            if (m.peekRouteList() instanceof Train)
            {
                Train train = (Train) m.popRouteList();
                IMessagable trainPrevTrack = m.popRouteList();
                IMessagable nextForTrain = null;
                Direction headingForTrain = null;
                if (trainPrevTrack == aloneNeighbor)
                {
                    if (switchEngaged)
                    {
                        nextForTrain = switchNeighbor;
                        headingForTrain = switchSide;
                    }
                    else
                    {
                        nextForTrain = switchSideOtherNeighbor;
                        headingForTrain = getNeighborSide(switchSideOtherNeighbor);
                    }
                }
                else if (trainPrevTrack == switchNeighbor || trainPrevTrack == switchSideOtherNeighbor)
                {
                    nextForTrain = aloneNeighbor;
                    headingForTrain = getNeighborSide(aloneNeighbor);
                }
                else
                {
                    System.err.println(toString() + "got a request from a train that didn't just come from its neighbor.");
                }

                m.pushRouteList(this);
                m.pushRouteList(nextForTrain);
                m.setHeading(headingForTrain);
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
     * TODO: I'm not sure if I'm commenting this right.
     * getNeighborSide()
     * @param trackNeighbor The neighbor that you are checking
     * @return The direction that this neighbor is on.
     */
    private Direction getNeighborSide(IMessagable trackNeighbor)
    {
        if (trackNeighbor == rightNeighbor) return Direction.RIGHT;
        else return Direction.LEFT;
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

}
