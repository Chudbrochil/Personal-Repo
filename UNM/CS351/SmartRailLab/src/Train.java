import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Queue;
import java.util.Random;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Class that represents a 'train' in the SmartRail simulation. Trains can request routes and receive routes back,
 *   then travel along routes.
 *
 * To use a train, you MUST call the setNeighbors method.
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
    private static Image redTrainImg;
    private static Image blueTrainImg;
    private static Image brownTrainImg;
    private static Image greenTrainImg;
    private Image randomTrainImg;
    private static ArrayList<Image> trainImgs;

    private boolean going = false; //True the train has received a valid 'GO' message and is proceeding along the track.
    private String destination = "";  //Save the name of a Station. Used by train to double check GO signals to make sure
                                      //the route goes to the desired location.
    private Direction heading;       //which way is the train heading?
    private GraphicsContext gcDraw;

    private int canvasX;
    private int canvasY;

    // Details of the image gotten from the actual jpg image
    private int knownTrainSizeX = 69;
    private int knownTrainSizeY = 14;
    
    //todo: list of stations you can visit?
    public Train()
    {
        NAME = "Train" + trainIncrement;
        trainIncrement++;
        trainImgInit();
        heading = Direction.RIGHT;
    }

    public Train(GraphicsContext gcDraw, int x, int y)
    {
        this();
        this.gcDraw = gcDraw;
        canvasX = x;
        canvasY = y;
    }

    private void trainImgInit()
    {
        if(redTrainImg == null) { redTrainImg = new Image("RedTrain.jpg"); }
        if(blueTrainImg == null) { blueTrainImg = new Image("BlueTrain.jpg"); }
        if(brownTrainImg == null) { brownTrainImg = new Image("BrownTrain.jpg"); }
        if(greenTrainImg == null) { greenTrainImg = new Image("GreenTrain.jpg"); }

        trainImgs = new ArrayList<>();
        trainImgs.add(redTrainImg);
        trainImgs.add(blueTrainImg);
        trainImgs.add(brownTrainImg);
        trainImgs.add(greenTrainImg);

        Random rand = new Random();
        randomTrainImg = trainImgs.get(rand.nextInt(4));
    }

    public boolean hasAStation()
    {
        if(currentTrack != null) { return true; }
        else { return false; }
    }

    public void setCoords(int x, int y)
    {
        canvasX = x;
        canvasY = y;
    }


    /**
     * setNeighbors()
     * Will set it's currentTrack based upon which one isn't null.
     */
    public void setNeighbors(IMessagable left, IMessagable right)
    {
        if(left != null) { currentTrack = left; }
        else { currentTrack = right; }
        //sendMessage(new Message(NAME, this, MessageType.REQUEST_HEADING, null, null),currentTrack);
    }


    public void draw()
    {
        gcDraw.drawImage(randomTrainImg, canvasX, canvasY);
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
            try
            {
                wait();
            }
            catch(Exception e) {}
        }
    }
    
    /**
     * @param m message sent to this train from any other IMessagable object.
     *   REQUEST_HEADING
     *          Receives this message from a station to request which Direction, LEFT or RIGHT, the train is 'facing.'
     *   GO
     *          Receives this message fom a station when a route has been successfully found for this train.
     *
     */
    private void readMessage(Message m)
    {
        if(m.type == MessageType.GO)
        {
            if(m.peekSenderList()==currentTrack)
            {
                heading = m.getHeading();
                if(m.STATION == destination)
                {
                    if(DEBUG) System.out.println(toString()+" has received a message from "+m.peekSenderList().toString()+
                        " to proceed to"+ m.STATION.toString());
                    going = true;
                    sendMessage(new Message(NAME, this, MessageType.REQUEST_NEXT_TRACK, destination, heading), currentTrack);
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
                    heading = m.getHeading();
                    
                    //checks if it's arrived at the station
                    if(currentTrack instanceof Station && ((Station) currentTrack).NAME.equals(destination))
                    {
                        System.out.println(toString()+" has arrived at destination, "+destination+"!");
                        going = false;
                        //reset heading to current station.
                        //sendMessage(new Message(NAME, this, MessageType.REQUEST_HEADING, null, null),currentTrack);
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
     * This is also the method where train learns where it should go on the canvas. The draw method will use this
     * position to draw in the location.
     */
    private void proceedTo(IMessagable nextTrack)
    {
        for(int i = 0; i < 100; ++i)
        {
            // Each track section is 100 pixels long. This will move 100 pixels.
            // Sleeping for 40 milliseconds results in a 4 second traversal per piece of track.
            // This could be easily configurable, but where? The user may not want this granularity
            if(heading == Direction.RIGHT) { canvasX += 1; }
            else if(heading == Direction.LEFT) { canvasX -= 1; }
            try{ Thread.sleep(40); }
            catch(InterruptedException e) { System.out.println(e.getMessage()); }
        }


        currentTrack = nextTrack;
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
        Message message = new Message(NAME, this, MessageType.SEARCH_FOR_ROUTE, station, null);
        sendMessage(message, currentTrack);
    }
    
    @Override
    public String toString()
    {
        return NAME;
    }

    public boolean isInClickedArea(int x, int y)
    {
        if(x >= canvasX && x <= canvasX + knownTrainSizeX &&
                y >= canvasY && y <= canvasY + knownTrainSizeY)
        {
            return true;
        }
        else { return false; }
    }


    
    //generate random destination, could call that method for 'computer' trains and use user input still via requestRoute()
}
