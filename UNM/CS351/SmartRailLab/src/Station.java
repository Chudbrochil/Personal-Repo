import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Stations are placed at the end of lines and are deistinations for Trains in the SmartRail simulation.
 *
 * To use this class, you MUST set neighbor by calling setNeighbor().
 */
public class Station implements IMessagable, IDrawable
{
    public String NAME;             //Default name of "Station#" given, or a name can be specified
    private static int stationIncrement = 1;   //Static int that gives the stations their ID's.
    private Queue<Message> pendingMessages = new ConcurrentLinkedQueue<>(); //list of all messages, held in order of receiving them, to be acknowledged.
    private IMessagable neighbor; //track piece the Station is connected to.
    private boolean DEBUG = true;   //Debug flag
    
    public Station()
    {
        NAME = "Station+" + stationIncrement;
        stationIncrement++;
    }
    public Station(String name)
    {
        NAME = name;
    }
    
    //TODO: Do we want a left/right neighbor? Does it matter?
    //(Can the train sit on it? My vote is yes.)
    public void setNeighbor(IMessagable n)
    {
      neighbor = n;
    }
    
    
    public void run()
    {
        //while program running
        if(!pendingMessages.isEmpty())
        {
            readMessage(pendingMessages.poll());
        }
    }
    
    private void readMessage(Message m)
    {
      if(m.type == MessageType.SEARCH_FOR_ROUTE)
      {
          if(m.STATION == this.NAME)
          {
              System.out.println("Route to Station "+NAME+" has been found!");
              //todo: implement
              //m.setType(MessageType.FOUND_ROUTE);
              //sendMessage(message, neighbor);
              //we don't want route destroyed on the way back, though. We want to keep that.
          }
          //todo: else, send a negative response?
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
    
    public void sendMessage(Message message, IMessagable neighbor)
    {
        if(DEBUG) System.out.println(this.toString()+" sending message to "+neighbor.toString()+". Message is: "+message.toString());
        neighbor.recvMessage(message);
    }
    
    public void recvMessage(Message message)
    {
        if(DEBUG) System.out.println(this.toString()+" received a message. Message is: "+message.toString());
        pendingMessages.add(message);
        //this.notify();
    }

}
