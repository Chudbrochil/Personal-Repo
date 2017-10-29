import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
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
    private static Image trackImg;                    // Image that we use to draw a track.
    private RailLight trackLight;                     // Light that is affixed on a track.
    //current train var?

    public RailTrack()
    {
        NAME = "Track" + trackIncrement;
        trackIncrement++;
        // Doing this to save the resources from creating a million images for each track.
        if(trackImg == null) { trackImg = new Image("Track.png"); }
    }
    public RailTrack(RailLight trackLight)
    {
        this();
        this.trackLight = trackLight;
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
     *  RESERVE_ROUTE
     *      Reserves itself todo: (And the light, if applicable)
     *      Then pops the next member off the sender list in Message m and forwards the message to that Rail component
     *      IF it is a neighbor of this track. If it is not, an error message is printed and the message is dropped.
     */
    private void readMessage(Message m)
    { //todo: switchcase?
        if(m.type == MessageType.HELLOTEST)
        {
            m.pushSenderList(this);
            if(rightNeighbor!=null) sendMessage(m, rightNeighbor);
            else if(DEBUG) System.out.println("End of the line reached at "+this.toString());
        }
        else if(m.type == MessageType.SEARCH_FOR_ROUTE)
        {
            //Was it a train that sent the message? If so, you'll need to send one to both neighbors.
            IMessagable mostRecentSender = m.peekSenderList();
            IMessagable neighborToSendTo=null;
            m.pushSenderList(this);
            //todo: Delete this? this goes in Station, because trains only request routes from stations.
            if(mostRecentSender instanceof Train)
            {
                if(leftNeighbor!=null)  sendMessage(m.clone(),leftNeighbor); //two instances of message now.
                if(rightNeighbor!=null) sendMessage(m,rightNeighbor);
            }
            
            //look for which neighbor sent this message. Send this message to your other neighbors.
            
            //If the message came from your right, send it to your left, and vis versa.
            else if(mostRecentSender==leftNeighbor || mostRecentSender==rightNeighbor)
            {
                if(mostRecentSender==this.leftNeighbor) neighborToSendTo = rightNeighbor;
                if(mostRecentSender==this.rightNeighbor) neighborToSendTo = leftNeighbor;
                if(neighborToSendTo!=null)
                {
                    sendMessage(m,neighborToSendTo);
                    //Only one instance of this message needed because only one instance is being sent out.
                }
                //else... //todo: If we need to send a negative 'no route found' message back, we can do that here.
                //maybe the train only acts if it finds a route. Otherwise... It just sits? Maybe after a while it gets
                // a new destination. If we send emssages back we'd have to wait a set amount of time for all the answers
                //to 'come in' as well.
            }
            else
            {
                if(DEBUG) printNeighborDebug(mostRecentSender, m.type.toString());
                printNeighborError(m.type.toString());
            }
        }
        else if(m.type == MessageType.RESERVE_ROUTE)
        {
            //Tracks don't need to check if they CAN protect. They don't have anything to do.
            //todo: light. Check if light. If so, which direction do we need to protect? look at the next neighbor. That
            //is where train will come from. (And, you can assume, the other direction is where the train is going
            // /where the message came from.
            
            reserve();
            //Actually pop the sender this time. It will be either the right or left neighbor, if this was done correctly.
            IMessagable nextSenderInList = m.popSenderList();
            if(nextSenderInList == leftNeighbor) sendMessage(m, leftNeighbor);
            else if(nextSenderInList == rightNeighbor) sendMessage(m,rightNeighbor);
            else
            {
                if(DEBUG) printNeighborDebug(nextSenderInList, m.type.toString());
                printNeighborError(m.type.toString());
            }
        }
        if(m.type == MessageType.REQUEST_NEXT_TRACK)
        {
            if(m.peekSenderList() instanceof Train)
            {
                Train train = (Train)m.popSenderList();
                IMessagable trainPrevTrack = m.popSenderList();
                IMessagable nextForTrain = null;
                if(trainPrevTrack == leftNeighbor) nextForTrain = rightNeighbor;
                else if(trainPrevTrack == rightNeighbor) nextForTrain = leftNeighbor;
                else
                {
                    System.err.println(toString()+"got a request from a train that didn't just come from its neighbor.");
                }
                m.pushSenderList(this);
                m.pushSenderList(nextForTrain);
                sendMessage(m, train);
            }
            else
            {
                System.err.println(toString()+" got a message of type REQUEST_NEXT_TRACK from "+m.peekSenderList().toString()
                    +" is not a train.");
            }
        }
    }
    
    private void printNeighborDebug(IMessagable mostRecentSender, String messageType)
    {
        System.out.println(this.toString()+" just got a message (type "+messageType+") from "+mostRecentSender+", which is"
            +" not a neighbor. No message sent.");
    }
    
    private void printNeighborError(String type)
    {
        System.err.println("Message passed from Rail piece to another that was not a neighbor. Message type: "+type);
    }
    
    /**
     *
     * @param x x location to begin drawing on the canvas
     * @param y y location to begin drawing on the canvas
     * Draws the object on a canvas at location x,y according to its currrent state.
     */
    public void draw(int x, int y, GraphicsContext gcDraw)
    {
        //TODO: Change color when the track is reserved
        gcDraw.fillText(this.toString(), x, y);
        gcDraw.drawImage(trackImg, x, y);
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
