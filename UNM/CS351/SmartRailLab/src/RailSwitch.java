import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Anna Carey
 *
 * This switch has two neighbors on the left and one neighbor on the right. It is drawn to go UP and straight across.
 *
 * To use this class, you MUST call setNeighbors with all four parameters OR setNeighbors with two parameters
 *   and setSwitchNeighbor to set its neighbor and a direction.
 *
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
    private boolean DEBUG = true;
    private boolean reserved;

    private GraphicsContext gcDraw;
    private int canvasX;
    private int canvasY;

    private RailLight trackLight;

    private static Image switchImg;
    private static Image trackImg;
    private static Image reserveTrackImg;
    private static Image switchRegularReserveImg;
    private static Image switchDiagonalReserveImg;


    public RailSwitch()
    {
        NAME = "RailSwitch"+switchIncrement;
        switchIncrement++;
        switchEngaged = false;
        reserved = true;
        if(switchImg == null) { switchImg = new Image("Switch.png"); }
        if(trackImg == null) { trackImg = new Image("Track.png"); }
        if(reserveTrackImg == null) { reserveTrackImg = new Image("Track-Reserve.png"); }
        if(switchRegularReserveImg == null) { switchRegularReserveImg = new Image("Switch-RegularReserve.png"); }
        if(switchDiagonalReserveImg == null) { switchDiagonalReserveImg = new Image("Switch-DiagonalReserve.png"); }
    }

    public RailSwitch(RailLight trackLight)
    {
        this();
        this.trackLight = trackLight;
    }

    /**
     * // TODO: FIX COMMENTS
     *      * @param side Direction side of the Switch the connection is on.
     *             (RIGHT and UPRIGHT are synonymous
     *             (LEFT and DOWNLEFT are synonymous)
     * @param trackLight
     * @param gcDraw
     * @param x
     * @param y
     * @param switchSide
     */
    public RailSwitch(RailLight trackLight, GraphicsContext gcDraw, int x, int y, Direction switchSide)
    {
        this(trackLight);
        this.gcDraw = gcDraw;
        this.switchSide = switchSide;
        canvasX = x;
        canvasY = y;


        if(switchSide==Direction.RIGHT || switchSide==Direction.UPRIGHT)
        {
            this.switchSide = Direction.UPRIGHT;
        }
        else //side==Direction.LEFT || side==Direction.DOWNLEFT
        {
            this.switchSide = Direction.DOWNLEFT;
        }
    }
    
    public void draw()
    {
        // Only need one switch piece to draw itself...
        if(switchSide == Direction.UPRIGHT)
        {
            gcDraw.drawImage(switchImg, canvasX, canvasY - 70);
        }
        else if(switchSide == Direction.UPRIGHT && switchEngaged && reserved)
        {
            gcDraw.drawImage(switchDiagonalReserveImg, canvasX, canvasY - 70);
        }
        else if(switchSide == Direction.UPRIGHT && reserved)
        {
            gcDraw.drawImage(switchRegularReserveImg, canvasX, canvasY - 70);
        }
        else if(switchSide == Direction.DOWNLEFT)
        {
            gcDraw.drawImage(trackImg, canvasX, canvasY);
        }
        else if(switchSide == Direction.DOWNLEFT && reserved)
        {
            gcDraw.drawImage(reserveTrackImg, canvasX, canvasY);
        }

    }
    
    /**
     * @param switchEng Whether the train needs the switch engaged or not.
     *                        //todo: find out which way the train needs to go.
     */
    private void reserve(boolean switchEng)
    {
        switchEngaged = switchEng;
        reserved = true;
        trackLight.reserve(switchSide);
    }
    private void unreserve()
    {
        reserved = false;
        trackLight.unreserve();
    }
    
    /**
     * Check pendingMessages. If it is empty, wait. (Notify is in the recMessage() method)
     */
    public void run()
    {
        while(true)
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
            catch(Exception e) {}
        }
    }
    
    /**
     * @param m message
     *  input
     *  first message in pendingMessages.
     *  Any non-null Message.
     *
     *  Parses and acts on the given Message.
     *
     *  SEARCH_FOR_ROUTE
     *      Adds itself to the sender list.
     *      Checks who the message is from and forwards the message to its other neighbor or neighbors.
     *      If it came from the left, the switch sends it to its two right neighbors.
     *      If it came from either of the right neighbors, it sends it to the left neighbor.
     *      If it came from the left and the message is going to the right but right is null, for example, the message
     *          just doesn't get sent anywhere.
     *  RESERVE_ROUTE
     *      Reserves itself and its light, if applicable.
     *      Then pops the next member off the sender list in Message m and forwards the message to that Rail component
     *      IF it is a neighbor of this track. If it is not, an error message is printed and the message is dropped.
     *      Adds itself to the sender list, then sends it off.
     *  REQUEST_NEXT_TRACK
     *      Pulls train from the sender list
     *      Pops the next sender, which is the track the train was previously on
     *      Pushes itself and then the next track the train should be going to to the sender list
     *      Sends the message back to the train.
     */
    private void readMessage(Message m)
    {
        /** This code executes to assign these members to make code module moving forward.**/
        IMessagable switchSideOtherNeighbor;
        IMessagable aloneNeighbor;
    
        //Find out which side the 'switchNeighbor' is on. Adjust which neighbor is its 'partner' in direction accordingly.
        if(switchSide == Direction.UPRIGHT)
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
        if(m.type == MessageType.SEARCH_FOR_ROUTE)
        {
            //look for which neighbor sent this message. Send this message to your other neighbor or neighbors.
            IMessagable mostRecentSender = m.peekSenderList();
            m.pushSenderList(this); //sign the sender list before you pass it on.
    
            //Now actually find out which way the message came from and send it on its way.
            if(mostRecentSender==switchSideOtherNeighbor || mostRecentSender==switchNeighbor)
            {
                if(aloneNeighbor!=null)
                {
                    m.setHeading(getSide(aloneNeighbor));
                    sendMessage(m, aloneNeighbor);
                }
            }
            else if(mostRecentSender==aloneNeighbor)
            {
                Message mClone = m.clone();
                if(switchSideOtherNeighbor !=null)
                {
                    m.setHeading(getSide(switchSideOtherNeighbor));
                    sendMessage(m, switchSideOtherNeighbor);
                }
                if(switchNeighbor !=null)
                {
                    m.setHeading(switchSide);
                    sendMessage(mClone, switchNeighbor);
                }
            }
            else
            {
                if(DEBUG) printNeighborDebug(mostRecentSender, m.type.toString());
                printNeighborError(m.type.toString());
            }
        }
        else if(m.type == MessageType.RESERVE_ROUTE)
        {
            //todo: Check if already reserved? Second train
    
            //Actually pop the sender this time. It will be either the right or left neighbor, if this was done correctly.
            IMessagable cameFrom = m.popSenderList(); //RailSwitch cares who it came from.
            IMessagable goingTo = m.popSenderList(); //Also cares where it's going
            m.pushSenderList(this);             //Sign the message
            
            
            if(cameFrom == aloneNeighbor)
            {
                if(goingTo == switchSideOtherNeighbor)
                {
                    //don't need the switch neighbor
                    reserve(false);
                    m.setHeading(getSide(switchSideOtherNeighbor));
                    sendMessage(m, switchSideOtherNeighbor);
                }
                else if(goingTo == switchNeighbor)
                {
                    reserve(true);
                    m.setHeading(switchSide);
                    sendMessage(m, switchNeighbor);
                }
                else
                {
                    System.err.println(toString()+" cannot reserve for the neighbor "+goingTo.toString()+" it received.");
                }
            }
            else if(cameFrom == switchSideOtherNeighbor)
            {
                reserve(false);
                if(goingTo == aloneNeighbor)
                {
                    m.setHeading(getSide(aloneNeighbor));
                    sendMessage(m, aloneNeighbor);
                }
                else System.err.println(toString()+" cannot reserve for the neighbor "+goingTo.toString()+" it received.");
            }
            else if(cameFrom == switchNeighbor)
            {
                reserve(true);
                if(goingTo == aloneNeighbor)
                {
                    m.setHeading(getSide(switchNeighbor));
                    sendMessage(m, aloneNeighbor);
                }
                else System.err.println(toString()+" cannot reserve for the neighbor "+goingTo.toString()+" it received.");
            }
            else
            {
                if(DEBUG) printNeighborDebug(cameFrom, m.type.toString());
                printNeighborError(m.type.toString());
            }
        }
        else if(m.type == MessageType.REQUEST_NEXT_TRACK)
        {
            if(m.peekSenderList() instanceof Train)
            {
                Train train = (Train)m.popSenderList();
                IMessagable trainPrevTrack = m.popSenderList();
                IMessagable nextForTrain = null;
                Direction headingForTrain = null;
                if(trainPrevTrack == aloneNeighbor)
                {
                    if(switchEngaged)
                    {
                        nextForTrain = switchNeighbor;
                        headingForTrain = switchSide;
                    }
                    else
                    {
                        nextForTrain = switchSideOtherNeighbor;
                        headingForTrain = getSide(switchSideOtherNeighbor);
                    }
                }
                else if(trainPrevTrack == switchNeighbor || trainPrevTrack == switchSideOtherNeighbor)
                {
                    nextForTrain = aloneNeighbor;
                    headingForTrain = getSide(aloneNeighbor);
                }
                else
                {
                    System.err.println(toString()+"got a request from a train that didn't just come from its neighbor.");
                }
        
                m.pushSenderList(this);
                m.pushSenderList(nextForTrain);
                m.setHeading(headingForTrain);
                unreserve();
                sendMessage(m, train);
            }
            else
            {
                System.err.println(toString()+" got a message of type REQUEST_NEXT_TRACK from "+m.peekSenderList().toString()
                    +" is not a train.");
            }
        }
    }
    
    private Direction getSide(IMessagable trackNeighbor)
    {
        if(trackNeighbor==rightNeighbor) return Direction.RIGHT;
        else return Direction.LEFT;
    }
    
    private synchronized void sendMessage(Message message, IMessagable neighbor)
    {
        if(DEBUG) System.out.println(this.toString()+" sending message to "+neighbor.toString()+". Message is: "+message.toString());
        neighbor.recvMessage(message);
    }
    
    public synchronized void recvMessage(Message message)
    {
        if(DEBUG) System.out.println(this.toString()+" received a message. Message is: "+message.toString());
        pendingMessages.add(message);
        this.notify();
    }
    
    /**
     * @param left IMessagable piece to the left of this piece. Initialized at runtime.
     * @param right IMessagable piece to the right of this piece. Initialized at runtime.
     *       null if no neighbor or a IMessagable class to which 'this' can pass messages.
     */
    public void setNeighbors(IMessagable left, IMessagable right)
    {
        leftNeighbor = left;
        rightNeighbor = right;
    }
    
    /**
     * @param switchN IMessagable piece above this switch.

     */
    public void setSwitchNeighbor(IMessagable switchN)
    {
        switchNeighbor = switchN;

    }
    
    /**
     * This method is for setting all 3 neighbors at once.
     *
     * @param left IMessagable piece to the left of this piece. Initialized at runtime.
     * @param right IMessagable piece to the right of this piece. Initialized at runtime.
     *       null if no neighbor or a IMessagable class to which 'this' can pass messages.
     * @param switchN IMessagable piece above this switch.
     */
    public void setNeighbors(IMessagable left, IMessagable right, IMessagable switchN)
    {
        this.setNeighbors(left,right);
        this.setSwitchNeighbor(switchN);
    }
    
    
    /**
     * @param mostRecentSender Neighbor who just sent the message
     * @param messageType Type of message that went wrong
     *
     * This method prints a debug statement "this.toString just got a message type messageType from mostRecentSender,
     *                    which is not a neighbor. No message sent."
     */
    private void printNeighborDebug(IMessagable mostRecentSender, String messageType)
    {
        System.out.println(this.toString()+" just got a message (type "+messageType+") from "+mostRecentSender+", which is"
            +" not a neighbor. No message sent.");
    }
    
    /**
     * @param type Type of message that went wrong
     *
     * This method prints a System err statement "Message passed from Rail peice to another that was not a neighbor.
     *             Message type: type."
     */
    private void printNeighborError(String type)
    {
        System.err.println("Message passed from Rail piece to another that was not a neighbor. Message type: "+type);
    }
    
    
    @Override
    public String toString()
    {
        return NAME;
    }
}
