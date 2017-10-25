import javafx.scene.paint.Color;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Anna Carey on 10/18/17
 *
 * Class for all track pieces. Assumes a train can sit on it and it has neighbors.
 * Will extend thread. Run method will consist of checking for and reacting to messages.
 *
 *
 */

public class RailTrack implements IMessagable, IDrawable {
    
    private String NAME;                              // Formal name of the train, useful for trace
    private static int trackIncrement = 1;            // ID number for a given track piece
    private Queue<Message> pendingMessages = new ConcurrentLinkedQueue<>();  //list of all messages, held in order of receiving them, to be acknowledged.
    private IMessagable leftNeighbor = null;           //left neighbor 'this' can send and receive messages from
    private IMessagable rightNeighbor = null;          //left neighbor 'this' can send and receive messages from
    private Color drawColor = Color.BLUE;             //blue if unreserved; green if reserved.
    private boolean DEBUG = true;                     //turn this flag on to print out a message log.
    //current train var?

    public RailTrack()
    {
        NAME = "Track" + trackIncrement;
        trackIncrement++;
    }
    
    /**
     * todo: Should these be combined? I left them separate for clarity.
     * @param neighbor IMessagable piece to the left of this piece. Initialized at runtime.
     *       input
     *       null if no neighbor or a IMessagable class to which 'this' can pass messages.
     */
    public void setLeftNeighbor(IMessagable neighbor)
    {
        leftNeighbor = neighbor;
    }
    public void setRightNeighbor(IMessagable neighbor)
    {
        rightNeighbor = neighbor;
    }
    
    //Reserves the track and (ideally) prevents any other traffic from passing over it.
    public void reserve()
    {
        drawColor = Color.GREEN;
    }
    
    public void unreserve()
    {
        drawColor = Color.BLUE;
    }
    
    /**
     * When this class extends Thread, this method will handle itself and loop infinitely.
     * For now, we're just calling it from the main thread each time we want it to be called.
     */
    public void run()
    {
        //while program running
        if(!pendingMessages.isEmpty())
        {
            readMessage(pendingMessages.poll());
        }

        //TODO: wait(), this will eventually come alive with a notify() later in the receive message
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
     *      Forwards message to right neighbor.
     *  SEARCH_FOR_ROUTE
     *      Adds itself to the sender list.
     *      Checks who the message is from and forwards the message to its other neighbor.
     *      If it came from the left and the message is going to the right but right is null, for example, the message
     *          just doesn't get sent anywhere.
     */
    private void readMessage(Message m)
    { //todo: switchcase?
        if(m.type == MessageType.HELLOTEST)
        {
            m.pushSenderList(this);
            if(rightNeighbor!=null) sendMessage(m, rightNeighbor);
            else if(DEBUG) System.out.println("End of the line reached at "+this.toString());
        }
        if(m.type == MessageType.SEARCH_FOR_ROUTE)
        {
            //look for which neighbor sent this message. Send this message to your other neighbors.
            IMessagable mostRecentSender = m.peekSenderList();
            IMessagable neighborToSendTo=null;
            m.pushSenderList(this);
    
            //If the message came from your right, send it to your left, and vis versa.
            if(mostRecentSender==leftNeighbor || mostRecentSender==rightNeighbor)
            {
                if(mostRecentSender==this.leftNeighbor) neighborToSendTo = rightNeighbor;
                if(mostRecentSender==this.rightNeighbor) neighborToSendTo = leftNeighbor;
                if(neighborToSendTo!=null)
                {
                    sendMessage(m,neighborToSendTo);
                }
                //else... //todo: If we need to send a negative 'no route found' message back, we can do that here.
                //maybe the train only acts if it finds a route. Otherwise... It just sits? Maybe after a while it gets
                // a new destination. If we send emssages back we'd have to wait a set amount of time for all the answers
                //to 'come in' as well.
            }
            else
            {
                if(DEBUG) System.out.println(this.toString()+" just got a message from "+mostRecentSender+", which is"
                    +"not a neighbor. No message sent.");
                System.err.println("Message passed from one Rail piece to another that was not a neighbor.");
            }
        }
    }
    
    /**
     *
     * @param x x location to begin drawing on the canvas
     * @param y y location to begin drawing on the canvas
     * Draws the object on a canvas at location x,y according to its currrent state.
     */
    public void draw(int x, int y)
    {
        //todo: take in Graphics Context. Just draw a line using the railColor,
        // which changes whether the track is reserved or not.
    }
    
    /**
     * This method sends a MessageType.HELLOTEST down the line.
     */
    public void sendTestMessage()
    {
        sendMessage(new Message("TestTrain", this, MessageType.HELLOTEST, null), rightNeighbor);
    }
    
    //todo: I feel like this should maybe be a private method.
    public void sendMessage(Message message, IMessagable neighbor)
    {
        if(DEBUG) System.out.println(this.toString()+" sending message to "+neighbor.toString()+". Message is: "+message.toString());
        neighbor.recvMessage(message);
    }
    public void recvMessage(Message message)
    {
        if(DEBUG) System.out.println(this.toString()+" received a message. Message is: "+message.toString());
        pendingMessages.add(message);
    }

    @Override
    public String toString()
    {
        return NAME;
    }





}
