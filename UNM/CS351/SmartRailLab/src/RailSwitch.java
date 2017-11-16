import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.util.HashMap;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Anna Carey
 * <p>
 * This switch has two neighbors on the left and one neighbor on the right. It is drawn to go UP and straight across.
 * <p>
 * To use this class, you MUST call setNeighbors with all four parameters OR setNeighbors with two parameters
 * and setSwitchNeighbor to set its neighbor and a direction.
 * Every switchNeighbor MUST be an instance of Switch.
 * If the Switch is of
 */
public class RailSwitch extends Thread implements IMessagable, IDrawable
{
    public final String NAME;
    private static int switchIncrement = 1;
    private Queue<Message> pendingMessages = new ConcurrentLinkedQueue<>(); //list of all messages, held in order of receiving them, to be acknowledged.
    IMessagable leftNeighbor;      //any track piece
    IMessagable rightNeighbor;     //any track piece
    IMessagable switchNeighbor;    //another switch with the opposite Direction switchDirection
    private Direction switchDirection;  //if LEFT, this is a 'down' switch. if RIGHT, this is a 'up' switch.
    //This also determines whiether the 'switch' neighbor functions as a second
    //right neighbor or a second left neighbor.
    private boolean switchEngaged; //is the switch currently connected to its other switch neighbor, or lying flat left to right?
    private boolean reserved;
    private String trainReservedFor = ""; //The Train name for which this route is reserved
    
    /** If this switch clones a REQUESTS_ROUTE message, it creates a key in this map with null stored under it.
     * Then if a 'NO_ROUTE_FOUND' message is received and a key exists for that train (which means the request message was cloned by this switch),
     * if the IMessagable under that key is null, it sets it to the getMostRecentSender() of the message. If it is not null, that means
     * no route is found from this switch, so the switch forwards the NO_ROUTE_FOUND to the next neighbor.
     */
    private HashMap<Train, IMessagable> routeRequestsSent;

    private GraphicsContext gcDraw;
    private int canvasX;
    private int canvasY;

    private RailLight trackLight;

    private static Image switchUpRightImg;
    private static Image switchUpLeftImg;
    private static Image trackImg;
    private static Image reserveTrackImg;
    private static Image switchUprightDiagonalReserveImg;
    private static Image switchUprightRegularReserveImg;
    private static Image switchUpLeftDiagonalReserveImg;
    private static Image switchUpLeftRegularReserveImg;


    /**
     * RailSwitch constructor
     * RailSwitch is a switch that a train can travel on to switch from one trackline to another. For each full switch
     * there will be 2 objects. A top switch and a bottom switch. This makes sense because ultimately a switch is a
     * piece on the top track and bottom track. These objects will get attached to eachother later in the initialization
     * of objects.
     * @param trackLight Light that is attached to a given switch
     * @param gcDraw Graphics context to draw on
     * @param x x-coord to draw on
     * @param y y-coord to draw on
     * @param switchDirection Direction side of the switch the connection is on.
     *                        As a default, LEFT is changed to DOWNLEFT and RIGHT is changed to UPRIGHT.
     *                        A switch should either be upright, upleft, downleft, or downright.
     */
    public RailSwitch(RailLight trackLight, GraphicsContext gcDraw, int x, int y, Direction switchDirection)
    {
        NAME = "RailSwitch" + switchIncrement;
        switchIncrement++;
        switchEngaged = false;
        reserved = false;
        if (switchUpRightImg == null)
        {
            switchUpRightImg = new Image("Switch-UpRight.png");
        }
        if(switchUpLeftImg == null)
        {
            switchUpLeftImg = new Image("Switch-UpLeft.png");
        }
        if (trackImg == null)
        {
            trackImg = new Image("Track.png");
        }
        if (reserveTrackImg == null)
        {
            reserveTrackImg = new Image("Track-Reserve.png");
        }
        if (switchUprightDiagonalReserveImg == null)
        {
            switchUprightDiagonalReserveImg = new Image("Switch-UpRightDiagonalReserve.png");
        }
        if (switchUprightRegularReserveImg == null)
        {
            switchUprightRegularReserveImg = new Image("Switch-UpRightRegularReserve.png");
        }
        if(switchUpLeftDiagonalReserveImg == null)
        {
            switchUpLeftDiagonalReserveImg = new Image("Switch-UpLeftDiagonalReserve.png");
        }
        if(switchUpLeftRegularReserveImg == null)
        {
            switchUpLeftRegularReserveImg = new Image ("Switch-UpLeftRegularReserve.png");
        }
        this.trackLight = trackLight;

        this.gcDraw = gcDraw;
        this.switchDirection = switchDirection;
        canvasX = x;
        canvasY = y;

        // This is the default setting of switch going from downleft to upright
        if (switchDirection == Direction.RIGHT)
        {
            this.switchDirection = Direction.UPRIGHT;
        }
        else if (switchDirection == Direction.LEFT)
        {
            this.switchDirection = Direction.DOWNLEFT;
        }
    }

    /**
     * draw()
     * Draws a switch depending on it's current configuration. If the switch is a "downleft" switch then it is drawn
     * as a regular track. Otherwise we have special graphics for the "upright" switch.
     */
    public void draw()
    {
        // Various ways in which a switch should paint itself.
        if (switchDirection == Direction.UPRIGHT && switchEngaged && reserved)
        {
            gcDraw.drawImage(switchUprightDiagonalReserveImg, canvasX, canvasY - 70);
        }
        else if (switchDirection == Direction.UPRIGHT && reserved)
        {
            gcDraw.drawImage(switchUprightRegularReserveImg, canvasX, canvasY - 70);
        }
        else if (switchDirection == Direction.UPRIGHT)
        {
            gcDraw.drawImage(switchUpRightImg, canvasX, canvasY - 70);
        }
        else if (switchDirection == Direction.UPLEFT && switchEngaged && reserved)
        {
            gcDraw.drawImage(switchUpLeftDiagonalReserveImg, canvasX, canvasY - 70);
        }
        else if (switchDirection == Direction.UPLEFT && reserved)
        {
            gcDraw.drawImage(switchUpLeftRegularReserveImg, canvasX, canvasY - 70);
        }
        else if (switchDirection == Direction.UPLEFT)
        {
            gcDraw.drawImage(switchUpLeftImg, canvasX, canvasY - 70);
        }
        // A DOWNLEFT and DOWNRIGHT switch is drawn as a regular track
        else if (switchDirection == Direction.DOWNLEFT && reserved || switchDirection == Direction.DOWNRIGHT && reserved)
        {
            gcDraw.drawImage(reserveTrackImg, canvasX, canvasY);
        }
        else if (switchDirection == Direction.DOWNLEFT || switchDirection == Direction.DOWNRIGHT)
        {
            gcDraw.drawImage(trackImg, canvasX, canvasY);
        }

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
     * getPsuedoNeighbors()
     * This code returns the left and right neighbors in an array relative to whether they're 'alone' on that side
     * or if they are on the side of two neighbors (the other being the switchNeighbor.)
     * The intention is for this to make the messaging code module for different switch types.
     * @return IMessagable[2] = {switchSideOtherNeighbor, aloneNeighbor}
     *          where switchSideOtherNeighbor - If the switchNeighbor is a left neighbor, (UPLEFT or DOWNLEFT switches),
     *          switchSideOtherNeighbor will be leftNeighbor. Will be rightNeighbor for other switch types (UPRIGHT or DOWNRIGHT switches)
     *
     *          and aloneNeighbor will be leftNeighbor if the switch is of type UPRIGHT or DOWNRIGHT,
     *          or rightNeighbor if the switch is of type UPLEFT or DOWNLEFT.
     *
     */
    private IMessagable[] getPsuedoNeighbors()
    {
        IMessagable switchSideOtherNeighbor;
        IMessagable aloneNeighbor;
    
        //Find out which side the 'switchNeighbor' is on. Adjust which neighbor is its 'partner' in direction accordingly.
        if (switchDirection == Direction.UPRIGHT || switchDirection == Direction.DOWNRIGHT)
        {
            switchSideOtherNeighbor = rightNeighbor;
            aloneNeighbor = leftNeighbor;
        }
        else //switchDirection == Direction.DOWNLEFT || switchDirection == Direction.UPLEFT
        {
            switchSideOtherNeighbor = leftNeighbor;
            aloneNeighbor = rightNeighbor;
        }
        
        return new IMessagable[] {switchSideOtherNeighbor, aloneNeighbor};
    }
    
    /**
     * readMessage()
     * @param m message sent to this train from any other IMessagable object.
     * Observes what type of message m is and calls the appropriate method to respond to it, if implementation
     *          for that type of message has been written.
     * Implementations written for: SEARCH_FOR_ROUTE, RESERVE_ROUTE, WAIT_FOR_CLEAR_ROUTE, ABORT_ROUTE, REQUEST_NEXT_TRACK,
     *          TRAIN_GOODBYE_UNRESERVE.
     */
    private void readMessage(Message m)
    {
        IMessagable switchSideOtherNeighbor = getPsuedoNeighbors()[0];
        IMessagable aloneNeighbor = getPsuedoNeighbors()[1];
    
        switch(m.type)
        {
            case SEARCH_FOR_ROUTE:  readMessageSearchForRoute(m, switchSideOtherNeighbor, aloneNeighbor);
                break;
            case RESERVE_ROUTE: readMessageReserveRoute(m, switchSideOtherNeighbor, aloneNeighbor);
                break;
            case WAIT_FOR_CLEAR_ROUTE:  readMessageWaitForClearRoute(m);
                break;
            case ABORT_RESERVE_ROUTE:   readMessageAbortReserveRoute(m);
                break;
            case REQUEST_NEXT_TRACK: readMessageRequestNextTrack(m, switchSideOtherNeighbor, aloneNeighbor);
                break;
            case TRAIN_GOODBYE_UNRESERVE: readMessageTrainGoodbyeUnreserve(m);
                break;
            default: if(Main.DEBUG) System.out.println(toString()+ "received a message of type "+m.type.toString()+
                " for which there is no implementation.");
                break;
        }
    }
    
    /**
     * readMessageSearchForRoute()
     * @param m message of MessageType.SEARCH_FOR_ROUTe
     * @param switchSideOtherNeighbor IMessagable returned by the first index of getPseudoNeighbors()
     * @param aloneNeighbor IMessagable returned by the second index of getPseudoNeighbors()
     * SEARCH_FOR_ROUTE
     *          Adds itself to the route list.
     *          Checks who the message is from and forwards the message to its other neighbor or neighbors.
     *          If it came from the left, the switch sends it to its two right neighbors.
     *          If it came from either of the right neighbors, it sends it to the left neighbor.
     *          If it came from the left and the message is going to the right but right is null, for example, the message
     *          just doesn't get sent anywhere.
     */
    private void readMessageSearchForRoute(Message m, IMessagable switchSideOtherNeighbor, IMessagable aloneNeighbor)
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
                m.setHeading(switchDirection);
                sendMessage(mClone, switchNeighbor);
            }
        }
        else
        {
            if (Main.DEBUG) printNeighborDebug(mostRecentSender, m.type.toString());
            printNeighborError(m.type.toString());
        }
    }
    
    /**
     * readMessageReserveRoute()
     * @param m message of MessageType.RESERVE_ROUTE
     * @param switchSideOtherNeighbor IMessagable returned by the first index of getPseudoNeighbors()
     * @param aloneNeighbor IMessagable returned by the second index of getPseudoNeighbors()
     * RESERVE_ROUTE
     *          Checks if this RailSwitch is already reserved. If it is, reverses the route list in m and changes the type to ABORT_RESERVE_ROUTE.
     *          If RailSwitch is not reserved, it reserves itself and its light, if applicable.
     *          Then pops the next member off the route list in Message m and forwards the message to that Rail component
     *          IF it is a neighbor of this track. If it is not, an error message is printed and the message is dropped.
     */
    private void readMessageReserveRoute(Message m, IMessagable switchSideOtherNeighbor, IMessagable aloneNeighbor)
    {
        if(reserved)
        {
            initiateAbortReserve(m);
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
                reserve(true, switchDirection, m.TRAIN);
                m.setHeading(switchDirection);
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
                reserve(true, switchDirection, m.TRAIN);
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
    
    /**
     * @param m Message of MessageType.RESERVE_ROUTE
     *   Called if message of type RESERVE_ROUTE is received but the track is already reserved.
     *   Does two things:
     *          1) Changes Message m to type ABORT_RESERVE_ROUTE, reverses the route list, and sends the message
     *             'backward' (to the component that sent the message most recently.)
     *          2) Takes a clone of Message m (before the route list was reversed)  and changes its type to
     *             WAIT_FOR_CLEAR_ROUTE, then sends it to the next sender on that list. (to arrive at the train.)
     *   This is different than RailTrack because there is an extra track component th at must be considered.
     */
    private void initiateAbortReserve(Message m)
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
    }
    
    /**
     * readMessageWaitForClearRoute()
     * @param m message of type MessageType.WAIT_FOR_CLEAR_ROUTE
     * WAIT_FOR_CLEAR_ROUTE
     *          Forwards the message to the next route list neighbor.
     */
    private void readMessageWaitForClearRoute(Message m)
    {
        //Continue sending on the message in the direction it was going. This will eventually get to the train.
        IMessagable nextIMessagableToInform = m.popRouteList();
    
        //todo: if time, extract to 'forward message' method. Used in a few places.
        if(nextIMessagableToInform == leftNeighbor) sendMessage(m, leftNeighbor);
        else if(nextIMessagableToInform == rightNeighbor) sendMessage(m, rightNeighbor);
        else if(nextIMessagableToInform == switchNeighbor) sendMessage(m, switchNeighbor);
        else
        {
            if (Main.DEBUG) printNeighborDebug(nextIMessagableToInform, m.type.toString());
            printNeighborError(m.type.toString());
        }
    }
    /**
     * readMessageAbortReserveRoute()
     * @param m message of MessageType.ABORT_RESERVE_ROUTE
     * ABORT_REVERSE_ROUTE
     *          If this track is reserved on behalf of the train who first made this message, it unreserves itself and
     *          pass on the message to the next track, one of your neighbors, that needs to be unreserved.
     *          (Obtained from the route list)
     */
    private void readMessageAbortReserveRoute(Message m)
    {
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
    
    /**
     * readMessageRequestNextTrack()
     * @param m message of MessageType.REQUEST_NEXT_TRACK
     * @param switchSideOtherNeighbor IMessagable returned by the first index of getPseudoNeighbors()
     * @param aloneNeighbor IMessagable returned by the second index of getPseudoNeighbors()
     * REQUEST_NEXT_TRACK
     *          Pulls train from the route list
     *          Pops the next sender, which is the track the train was previously on
     *          Pushes itself and then the next track the train should be going to to the route list
     *          Sends the message back to the train.
     *
     */
    private void readMessageRequestNextTrack(Message m, IMessagable switchSideOtherNeighbor, IMessagable aloneNeighbor)
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
                    headingForTrain = switchDirection;
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
    
    /**
     * readMessageTrainGoodbyeUnreserve()
     * @param m message of MessageType.TRAIN_GOODBYE_UNRESERVE
     * TRAIN_GOODBYE_UNRESERVE
     *          If the first sender is a train, it calls 'unreserve' on itself. To be sent when the train leaves the track.
     *          else, prints to System.err
     */
    private void readMessageTrainGoodbyeUnreserve(Message m)
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

    /**
     * getNeighborSide()
     * @param trackNeighbor The neighbor that you are checking
     * @return The direction that this neighbor is on.
     * Used to get the correct heading for the message if it is not switchNeighbor and rightNeighbor and leftNeighbor
     * have been given 'pseudonames.'
     */
    private Direction getNeighborSide(IMessagable trackNeighbor)
    {
        if (trackNeighbor == rightNeighbor) return Direction.RIGHT;
        else if(trackNeighbor == leftNeighbor) return Direction.LEFT;
        else return switchDirection;
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
        //***message.setHeading(getNeighborSide(neighbor));
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
