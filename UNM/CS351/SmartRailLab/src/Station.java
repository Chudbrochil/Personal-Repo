import javafx.scene.canvas.GraphicsContext;

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
        NAME = "Station" + stationIncrement;
        stationIncrement++;
    }
    public Station(String name)
    {
        NAME = name;
    }
    
    //TODO: Do we want a left/right neighbor? Does it matter?
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
          //If it's a train, send the message on.
          if(m.peekSenderList() instanceof Train)
          {
              m.pushSenderList(this);
              sendMessage(m,neighbor);
              //todo: How should we handle if a train requests a route to a station it's ON?
          }
          //If the first sender isn't a train, it must be a track and the message is coming in.
          else if(m.STATION.equals(this.NAME))
          {
              if(DEBUG) System.out.println("Route to Station "+NAME+" has been found!");
              m.type = MessageType.RESERVE_ROUTE;
              IMessagable mostRecentSender = m.popSenderList();
              if(mostRecentSender == neighbor) sendMessage(m,neighbor);
              else
              {
                  if(DEBUG) printNeighborDebug(mostRecentSender, m.type.toString());
                  printNeighborError(m.type.toString());
              }
          }
          //todo: else, send a negative response?
      }
      
      else if(m.type == MessageType.RESERVE_ROUTE)
      {
          //If this message is received and the final sender is the train, then this is an answer to a SEARCH_FOR_ROUTE
          //message that the train that is IN this station
          IMessagable nextSenderInList = m.popSenderList();
          if(nextSenderInList instanceof Train)
          {
              Message goMessage = new Message(((Train) nextSenderInList).NAME, this, MessageType.GO, m.STATION);
              sendMessage(goMessage, nextSenderInList);
          }
          else
          {
              if(DEBUG) System.out.println(this.toString()+" just got a message (type "+m.type.toString()+") whose next " +
                  "reference is "+nextSenderInList.toString()+", which is not a train. No message sent.");
              System.err.println("RESERVE_ROUTE Message arrived at Station but next sender is not a Train.");
              return;
          }
          //TODO: IMPLEMENT NEXT TRACK
      }
      else if(m.type == MessageType.REQUEST_NEXT_TRACK)
      {
          //Should be the first request a train makes.
          if(m.peekSenderList() instanceof Train)
          {
              Train train = (Train)m.popSenderList();
              m.pushSenderList(this);
              m.pushSenderList(neighbor);
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
            +"not a neighbor. No message sent.");
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
        gcDraw.fillText(this.toString(), x, y);
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
    
    @Override
    public String toString()
    {
        return NAME;
    }

}
