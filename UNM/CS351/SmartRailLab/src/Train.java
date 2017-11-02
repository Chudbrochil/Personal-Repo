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
public class Train extends Thread implements IMessagable, IDrawable
{
    public final String NAME;
    private static int trainIncrement = 1;
    private Queue<Message> pendingMessages = new ConcurrentLinkedQueue<>(); //list of all messages, held in order of receiving them, to be acknowledged.
    private IMessagable currentTrack;
    private boolean DEBUG = true;
    private Image redTrainImg;
    private boolean going = false; //True the train has received a valid 'GO' message and is proceeding along the track.
    private String destination = "";  //Save the name of a Station. Used by train to double check GO signals to make sure
                                      //the route goes to the desired location.
    
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
    //private void setCurrentTrack(IMessagable currentTrack)
//    {
//        this.currentTrack = currentTrack;
//    }

    /**
     * setNeighbors()
     * Will set it's currentTrack based upon which one isn't null.
     */
    public void setNeighbors(IMessagable left, IMessagable right)
    {
        if(left != null) { currentTrack = left; }
        else { currentTrack = right; }
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
        while(true)
        {
            while(!pendingMessages.isEmpty())
            {
                readMessage(pendingMessages.poll());
            }
            else
            {
                try
                {
                    wait();
                }
                catch(Exception e) {}
            }
        }
    }
    
    private void readMessage(Message m)
    {
        if(m.type == MessageType.GO)
        {
            if(m.peekSenderList()==currentTrack)
            {
                if(m.STATION == destination)
                {
                    if(DEBUG) System.out.println(toString()+" has received a message from "+m.peekSenderList().toString()+
                        " to proceed to"+ m.STATION.toString());
                    going = true;
                    sendMessage(new Message(NAME, this, MessageType.REQUEST_NEXT_TRACK, destination), currentTrack);
                }
                else
                {
                    System.err.println(toString()+" received 'GO' message from currentTrack "+m.peekSenderList()+" to " +
                        "proceed to"+m.STATION+", which is not this train's destination, "+destination);
                }
            }
            else
            {
                if(DEBUG) System.out.println(toString()+" received a 'GO' message from "+m.peekSenderList()+", which is " +
                    "not a neighbor. Train remains stationary.");
                System.err.println(toString()+" received a go signal from "+m.peekSenderList()+", which is not a neighbor.");
            }
        }
        //Train should only receive this message if it sent it.
        //Train pops the first reference off the sender list, which is the track it should be going to. Then
        //checks the next sender list reference, which is the actual sender. This reference STAYS. The train pushes
        //itself, then sends this message on to the new currentTrack. (The result is a sender list that contains
        //the [PreviousTrack, Train] going to currentTrack.
        else if(m.type == MessageType.REQUEST_NEXT_TRACK)
        {
            if(going)
            {
                IMessagable nextTrack = m.popSenderList();
                if(m.peekSenderList() == currentTrack)
                {
                    proceedTo(nextTrack); //may be a sleep in this method. currentTrack becomes nextTrack.
                    
                    //checks if it's arrived at the station
                    if(currentTrack instanceof Station && ((Station) currentTrack).NAME.equals(destination))
                    {
                        System.out.println(toString()+" has arrived at destination, "+destination+"!");
                        going = false;
                    }
                    //send another message to request the NEXT track.
                    else
                    {
                        m.pushSenderList(this);
                        sendMessage(m, currentTrack);
                    }
                }
                else
                {
                    System.err.println(toString()+" received a REQUEST_NEXT_TRACK message from "+m.peekSenderList()+", " +
                        "which is not a neighbor. Train is remaining stationary.");
                }
            }
            else
            {
                System.err.println(toString()+" received a REQUEST_NEXT_TRACK message when 'going' status is false.");
            }
        }
    }
    
    /**
     * @param nextTrack a reference to the next track the train will be on.
     * This method sets currentTrack to next track.
     * //todo: I "encapsulated" this to make it easier for the graphics to deal with this phase however they will.
     *                  //maybe there's a 'wait' in here so the train can inch across the screen.
     */
    private void proceedTo(IMessagable nextTrack)
    {
        currentTrack = nextTrack;
    }

    //todo: I feel like this should maybe be a private method.
    //todo: I'm literally copy and pasting both these messages... THat makes me think maybe we should make a rail class of some kind. Abstract, even.
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
        destination = station;
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
