import java.util.ArrayList;

/**
 * Anna Carey on 10/18/17
 *
 * Class for all track pieces. Assumes a train can sit on it and it has neighbors.
 * Will extend thread. Run method will consist of checking for and reacting to mesages.
 *
 *
 */



public class RailTrack implements IMessagable, IDrawable {
    
    private IMessagable leftNeighbor = null;           //left neighbor 'this' can send and receive messages from
    private IMessagable rightNeighbor = null;          //left neighbor 'this' can send and receive messages from
    private ArrayList<Message> pendingMessages = new ArrayList<>(); //list of all messages, held in order of receiving them, to be acknowledged.
    private boolean DEBUG = true;                     //turn this flag on to print out a message log.
    //current train var?
    
    //todo: Above variable is used as "First In First Out." Is there a better data structure?
    
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
    {}
    
    public void unreserve()
    {}
    
    /**
     * When this class extends Thread, this method will handle itself and loop infinitely.
     * For now, we're just calling it from the main thread each time we want it to be called.
     */
    public void run()
    {
        //while program running
        if(!pendingMessages.isEmpty())
        {
            readMessage(pendingMessages.remove(0));
        }
    }
    
    /**
     * @param m
     *  input
     *  first message in pendingMessages.
     *  Any non-null Message.
     *
     *  Parses and acts on the given Message.
     */
    private void readMessage(Message m)
    {
        if(m.TYPE == MessageType.HELLOTEST)
        {
            m.setMostRecentSender(this);
            if(rightNeighbor!=null) sendMessage(m, rightNeighbor);
            else if(DEBUG) System.out.println("End of the line reached at "+this.toString());
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

    }
    
    /**
     * This method sends a MessageType.HELLOTEST down the line.
     */
    public void sendTestMessage()
    {
        sendMessage(new Message("TestTrain", this, MessageType.HELLOTEST), rightNeighbor);
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





}
