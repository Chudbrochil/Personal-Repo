import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Class that represents a 'train' in the SmartRail simulation. Trains can request routes and receive routes back,
 *   then (todo: still to implement) travel along routes.
 *
 * To make a Train, you must give it a name and set the current track via the setCurrentTrack() method.
 *   Then, you can have it request routes. This is currently a public method (10/26/17) but may be internal later?
 *
 */
public class Train implements IMessagable, IDrawable
{
    public final String NAME;
    private static int trainIncrement = 1;
    private Queue<Message> pendingMessages = new ConcurrentLinkedQueue<>(); //list of all messages, held in order of receiving them, to be acknowledged.
    private IMessagable currentTrack;
    private boolean DEBUG = true;
    private Image redTrainImg;
    
    //todo: list of stations you can visit?
    public Train()
    {
        NAME = "Train" + trainIncrement;
        trainIncrement++;
        if(redTrainImg == null) { redTrainImg = new Image("RedTrain.jpg"); }
    }
    
    public Train(String n)
    {
        NAME = n;
    }
    
    /**
     * To use a train, you MUST set its current track.
     * @param currentTrack
     */
    public void setCurrentTrack(IMessagable currentTrack)
    {
        this.currentTrack = currentTrack;
    }
    
    public void draw(int x, int y, GraphicsContext gcDraw)
    {
        gcDraw.drawImage(redTrainImg, x, y);
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
    
    private void readMessage(Message m)
    {
        if(m.type == MessageType.GO)
        {
            System.out.println(toString()+" has received a message from "+m.peekSenderList().toString()+" to proceed to" +
                m.STATION.toString());
        }
    }
    
    //todo: I feel like this should maybe be a private method.
    //todo: I'm literally copy and pasting both these messages... THat makes me think maybe we should make a rail class of some kind. Abstract, even.
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
    
    //todo: make private?
    
    /**
     * @param station: NAME of the station to which the Train wishes to travel.
     *   input
     *   Any String. For a station to be found, it must match a Station's NAME field.
     *   Messages will be passed to search for a route.
     * Searches for a route. If a route is found, a message indicating this will be received.
     */
    public void requestRoute(String station)
    {
        Message message = new Message(NAME, this, MessageType.SEARCH_FOR_ROUTE, station);
        sendMessage(message, currentTrack);
    }
    
    @Override
    public String toString()
    {
        return NAME;
    }
    
    //generate random destination, could call that method for 'computer' trains and use user input still via requestRoute()
}
