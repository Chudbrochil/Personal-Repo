import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Class that represents a 'train' in the SmartRail simulation. Trains can request routes and receive routes back,
 *   then (todo: still to implement) travel along routes.
 *
 * To make a Train, you must give it a name and set the current track via the setCurrentTrack() method.
 *   Then, you can have it request routes
 */
public class Train implements IMessagable, IDrawable
{
    public final String NAME;
    private static int trainIncrement = 1;
    private Queue<Message> pendingMessages = new ConcurrentLinkedQueue<>(); //list of all messages, held in order of receiving them, to be acknowledged.
    private IMessagable currentTrack;
    private boolean DEBUG = true;
    
    //todo: list of stations you can visit?
    public Train()
    {
        NAME = "Train" + trainIncrement;
        trainIncrement++;
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
    
    public void draw(int x, int y)
    {

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
    
    //generate random destination, could call that method for 'computer' trains and use user input still via requestRoute()
}
