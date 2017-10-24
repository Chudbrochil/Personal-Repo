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
    private IMessagable currentTrack;
    //todo: list of stations you can visit?
    
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
    
    public void sendMessage(Message message, IMessagable neighbor){}
    public void recvMessage(Message message){}
    
    //todo: make private?
    
    /**
     * @param station Station to which the Train wishes to travel. Messages will be passed to search for a route.
     * Searches for a route. If a route is found, a message indicating this will be received.
     */
    public void requestRoute(Station station)
    {
        Message message = new Message(NAME, this, MessageType.SEARCH_FOR_ROUTE, station.NAME);
        sendMessage(message, currentTrack);
    }
    
    //generate random destination, could call that method for 'computer' trains and use user input still via requestRoute()
}
