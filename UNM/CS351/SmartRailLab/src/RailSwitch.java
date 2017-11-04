import javafx.scene.canvas.GraphicsContext;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Anna Carey
 *
 * This switch has two neighbors on the left and one neighbor on the right. It is drawn to go UP and straight across.
 *
 * To use this class, you MUST call setNeighbors with all three parameters OR setNeighbors with two parameters
 *   and setSwitchNeighbor to set its neighbor.
 *   rightUpNeighbor must be a RailDownSwitch.
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

    public RailSwitch()
    {
        NAME = "RailSwitch"+switchIncrement;
        switchIncrement++;
        switchEngaged = false;
        reserved = true;
    }
    
    public void draw(int x, int y, GraphicsContext gc)
    {

    }
    
    /**
     * @param trainComingFrom Direction train is coming from. May affect where the switch needs to sit.?
     *                        //todo: find out which way the train needs to go.
     */
    private void reserve(Direction trainComingFrom)
    {
        reserved = true;
    }
    private void unreserve()
    {
        reserved = false;
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
     *  HELLOTEST
     *      NOT IMPLEMENTED
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
        if(m.type == MessageType.SEARCH_FOR_ROUTE)
        {
            IMessagable switchSideOtherNeighbor;
            IMessagable aloneNeighbor;
    
            //Find out which side the 'switchNeighbor' is on. Adjust which neighbor is its 'partner' in direction accordingly.
            if(switchSide == Direction.RIGHT)
            {
                switchSideOtherNeighbor = rightNeighbor;
                aloneNeighbor = leftNeighbor;
            }
            else //switchSide == Direction.LEFT
            {
                switchSideOtherNeighbor = leftNeighbor;
                aloneNeighbor = rightNeighbor;
            }
            
            //look for which neighbor sent this message. Send this message to your other neighbor or neighbors.
            IMessagable mostRecentSender = m.peekSenderList();
            m.pushSenderList(this); //sign the sender list before you pass it on.
    
            //Now actually find out which way the message came from and send it on its way.
            if(mostRecentSender==switchSideOtherNeighbor || mostRecentSender== switchNeighbor)
            {
                if(aloneNeighbor!=null) sendMessage(m, aloneNeighbor);
            }
            else if(mostRecentSender==aloneNeighbor)
            {
                Message mClone = m.clone();
                if(switchSideOtherNeighbor !=null) sendMessage(m, switchSideOtherNeighbor);
                if(switchNeighbor !=null) sendMessage(mClone, switchNeighbor);
            }
            else
            {
                if(DEBUG) printNeighborDebug(mostRecentSender, m.type.toString());
                printNeighborError(m.type.toString());
            }
        }
        else if(m.type == MessageType.RESERVE_ROUTE)
        {
            System.out.println("Message type 'RESERVE_ROUTE' not yet implemented in "+toString());
        }
        else if(m.type == MessageType.REQUEST_NEXT_TRACK)
        {
            System.out.println("Message type 'REQUEST_NEXT_TRACK' not yet implemented in "+toString());
        }
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
     * @param side Direction side of the Switch the connection is on.
     *             (LEFT = down, RIGHT = up.)
     */
    public void setSwitchNeighbor(IMessagable switchN, Direction side)
    {
        switchNeighbor = switchN;
        switchSide = side;
    }
    
    /**
     * @param left IMessagable piece to the left of this piece. Initialized at runtime.
     * @param right IMessagable piece to the right of this piece. Initialized at runtime.
     *       null if no neighbor or a IMessagable class to which 'this' can pass messages.
     * @param switchN IMessagable piece above this switch.
     * @param side Direction side of the Switch the connection is on.
     *             (LEFT = down, RIGHT = up.)
     */
    public void setNeighbors(IMessagable left, IMessagable right, IMessagable switchN, Direction side)
    {
        leftNeighbor = left;
        rightNeighbor = right;
        switchNeighbor = switchN;
        switchSide = side;
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
