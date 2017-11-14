import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.Queue;
import java.util.Random;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Class that represents a 'train' in the SmartRail simulation. Trains can request routes and receive routes back,
 * then travel along routes.
 * <p>
 * To use a train, you MUST call the setNeighbors method.
 * <p>
 * To make a Train, you must give it a name and set the current track via the setCurrentTrack() method.
 * Then, you can have it request routes. This is currently a public method (10/26/17) but may be internal later?
 */
public class Train extends Thread implements IMessagable, IDrawable
{
    public final String NAME;
    private static int trainIncrement = 1;
    private Queue<Message> pendingMessages = new ConcurrentLinkedQueue<>(); //list of all messages, held in order of receiving them, to be acknowledged.
    private IMessagable currentTrack;
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
    private final int knownTrainSizeX = 69;
    private final int knownTrainSizeY = 14;

    /**
     * Train()
     * Train's constructor. This will create a train for us to place in a station and navigate routes.
     * @param gcDraw Graphics context for drawing on canvas.
     * @param x x-coord to draw train on
     * @param y y-coord to draw train on
     */
    public Train(GraphicsContext gcDraw, int x, int y)
    {
        NAME = "Train" + trainIncrement;
        trainIncrement++;
        trainImgInit();
        heading = Direction.RIGHT;
        this.gcDraw = gcDraw;
        canvasX = x;
        canvasY = y;
    }

    /**
     * hasAStation()
     * @return True if a train is in a station, false if train isn't in a station.
     */
    public boolean hasAStation()
    {
        if (currentTrack != null)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    /**
     * setCoords()
     * This sets where the train should be drawn. Most components don't move, but trains do.
     * @param x x-coord to set
     * @param y y-coord to set
     */
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
        if (left != null)
        {
            currentTrack = left;
        }
        else
        {
            currentTrack = right;
        }
        //sendMessage(new Message(NAME, this, MessageType.REQUEST_HEADING, null, null),currentTrack);
    }


    /**
     * draw()
     * Draws the train on the canvas. proceedTo() is what figures out it's actual position
     */
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
        while (true)
        {
            while (!pendingMessages.isEmpty())
            {
                readMessage(pendingMessages.poll());
            }
            try
            {
                wait();
            }
            catch (Exception e)
            {
            }
        }
    }

    /**
     * recvMessage()
     * This is where a Train receives a message and then notifies to process it.
     * @param message Message that was sent to railswitch
     */
    public synchronized void recvMessage(Message message)
    {
        if (Main.DEBUG) System.out.println(this.toString() + " received a message. Message is: " + message.toString());
        pendingMessages.add(message);
        this.notify();
    }

    /**
     * requestRoute()
     * @param station: NAME of the station to which the Train wishes to travel.
     *                 input
     *                 Any String. For a station to be found, it must match a Station's NAME field.
     *                 Messages will be passed to search for a route.
     *                 Searches for a route. If a route is found, a message indicating this will be received.
     */
    public void requestRoute(String station)
    {
        destination = station;
        Message message = new Message(MessageType.SEARCH_FOR_ROUTE, NAME, this, station, null);
        sendMessage(message, currentTrack);
    }

    /**
     * toString()
     * @return String representing the name of the train.
     */
    @Override
    public String toString()
    {
        return NAME;
    }

    /**
     * isInClickedArea()
     * @param x x-coord we are checking
     * @param y y-coord we are checking
     * @return True if the clicked spot is in the trains coordinates. False if not.
     */
    public boolean isInClickedArea(int x, int y)
    {
        if (x >= canvasX && x <= canvasX + knownTrainSizeX &&
                y >= canvasY && y <= canvasY + knownTrainSizeY)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    /**
     * trainImgInit()
     * Initializes all of the train images. Then selects a random image for this train object.
     */
    private void trainImgInit()
    {
        if (redTrainImg == null)
        {
            redTrainImg = new Image("RedTrain.jpg");
        }
        if (blueTrainImg == null)
        {
            blueTrainImg = new Image("BlueTrain.jpg");
        }
        if (brownTrainImg == null)
        {
            brownTrainImg = new Image("BrownTrain.jpg");
        }
        if (greenTrainImg == null)
        {
            greenTrainImg = new Image("GreenTrain.jpg");
        }

        trainImgs = new ArrayList<>();
        trainImgs.add(redTrainImg);
        trainImgs.add(blueTrainImg);
        trainImgs.add(brownTrainImg);
        trainImgs.add(greenTrainImg);

        Random rand = new Random();
        randomTrainImg = trainImgs.get(rand.nextInt(4));
    }

    /**
     * readMessage()
     * @param m message sent to this train from any other IMessagable object.
     *          REQUEST_HEADING
     *          Receives this message from a station to request which Direction, LEFT or RIGHT, the train is 'facing.'
     *          GO
     *          Receives this message fom a station when a route has been successfully found for this train.
     *          WAIT_FOR_CLEAR_ROUTE
     *          Receiveing this message means that a route was found to the Station destination, but it is busy at this time.
     *          So, wait for a bit, and then request the route again.
     */
    private void readMessage(Message m)
    {
        if (m.type == MessageType.GO)
        {
            if (m.peekRouteList() == currentTrack)
            {
                heading = m.getHeading();
                if (m.STATION == destination)
                {
                    if (Main.DEBUG)
                        System.out.println(toString() + " has received a message from " + m.peekRouteList().toString() +
                                " to proceed to " + m.STATION.toString());
                    Notifications.updateSimStatus(toString() + " has received a message from " + m.peekRouteList().toString() +
                            " to proceed to " + m.STATION.toString());
                    Notifications.playSound("Train_Whistle.wav");
                    going = true;
                    sendMessage(new Message(MessageType.REQUEST_NEXT_TRACK, NAME, this, destination, heading), currentTrack);
                }
                else
                {
                    System.err.println(toString() + " received 'GO' message from currentTrack " + m.peekRouteList() + " to " +
                            "proceed to" + m.STATION + ", which is not this train's destination, " + destination);
                }
            }
            else
            {
                if (Main.DEBUG)
                    System.out.println(toString() + " received a 'GO' message from " + m.peekRouteList() + ", which is " +
                            "not a neighbor. Train remains stationary.");
                System.err.println(toString() + " received a go signal from " + m.peekRouteList() + ", which is not a neighbor.");
            }
        }
        
        else if (m.type == MessageType.WAIT_FOR_CLEAR_ROUTE)
        {
            if(Main.DEBUG) System.out.println(toString()+" received a "+MessageType.WAIT_FOR_CLEAR_ROUTE.toString()+" message. ");
            try { Thread.sleep(5000); } catch (InterruptedException e){}
            requestRoute(destination);
        }
        
        //Train should only receive this message if it sent it.
        //Train pops the first reference off the sender list, which is the track it should be going to. Then
        //checks the next sender list reference, which is the actual sender. This reference STAYS. The train pushes
        //itself, then sends this message on to the new currentTrack. (The result is a sender list that contains
        //the [PreviousTrack, Train] going to currentTrack.
        else if (m.type == MessageType.REQUEST_NEXT_TRACK)
        {
            if (going)
            {
                IMessagable nextTrack = m.popRouteList();
                if (m.peekRouteList() == currentTrack)
                {
                    //If it's in the station, get the reference to the next track so that you're at the "beginning" of the track before drawing yourself.
                    proceedTo(nextTrack); //may be a sleep in this method. currentTrack becomes nextTrack.
                    heading = m.getHeading();

                    //checks if it's arrived at the station
                    if (currentTrack instanceof Station && ((Station) currentTrack).toString().equals(destination))
                    {
                        //todo: making this into a currentTrack = nextTrack makes the 'unreserves' look right, but the switches are way wrong then.
                        proceedTo(nextTrack);
                        System.out.println(toString() + " has arrived at destination, " + destination + "!");
                        Notifications.updateSimStatus(toString() + " has arrived at destination, " + destination + "!");
                        Notifications.playSound("Train_Arriving.wav");
                        going = false;
                        //reset heading to current station.
                        //sendMessage(new Message(NAME, this, MessageType.REQUEST_HEADING, null, null),currentTrack);
                    }
                    //send another message to request the NEXT track.
                    else
                    {
                        m.pushRouteList(this);
                        sendMessage(m, currentTrack);
                    }
                }
                else
                {
                    System.err.println(toString() + " received a REQUEST_NEXT_TRACK message from " + m.peekRouteList() + ", " +
                            "which is not a neighbor. Train is remaining stationary.");
                }
            }
            else
            {
                System.err.println(toString() + " received a REQUEST_NEXT_TRACK message when 'going' status is false.");
            }
        }
    }

    /**
     * proceedTo()
     * @param nextTrack a reference to the next track the train will be on.
     *                  This method sets currentTrack to next track.
     *                  This is also the method where train learns where it should go on the canvas. The draw method will use this
     *                  position to draw in the location.
     */
    private void proceedTo(IMessagable nextTrack)
    {
        // Each track section is 100 pixels long. This will move 100 pixels.
        // Sleeping for 40 milliseconds results in a 4 second traversal per piece of track.
        // This could be easily configurable, but where? The user may not want this granularity
        
        //This allows the train to move out of the station and onto the track before doing its movement 'on that track'
        //(thus it unreserves when it leaves it.) This also allows the train to move into the station, but no further, when it arrives.
        //todo: the 'unreserve' still looks like it happens before the train's off the track (it's when the center of the train moves from
        //one piece to another.) So this could be improved,but it is good enough for now.
        int loopIterations = 100;
        if(currentTrack instanceof Station) loopIterations = 50;
        
        for (int i = 0; i < loopIterations; ++i)
        {
            if (heading == Direction.RIGHT)
            {
                canvasX += 1;
            } else if (heading == Direction.LEFT)
            {
                canvasX -= 1;
            }
            //TODO: Note: the trains move too much if they move in the x for the switches also. For now, this is a bad but functional fix. --Anna
            //(The trains now arrive on the stations, not the 100*switchesTraveled pixels away from the station. XD
            else if (heading == Direction.UPRIGHT)
            {
                //canvasX += 1;
                canvasY -= 1;
            } else if (heading == Direction.DOWNLEFT)
            {
                //canvasX -= 1;
                canvasY += 1;
            }
            try
            {
                Thread.sleep(40);
            } catch (InterruptedException e)
            {
                System.out.println(e.getMessage());
            }
        }
        
        sendMessage(new Message(MessageType.TRAIN_GOODBYE_UNRESERVE, NAME, this, null, null), currentTrack);
        currentTrack = nextTrack;

    }
    
    /**
     * sendMessage()
     * @param message The Message to send
     * @param neighbor IMessagable to which to send the message.
     *
     * sets the mostRecentSender in message to this and then calls recvMessage(message) on neighbor.
     */
    private synchronized void sendMessage(Message message, IMessagable neighbor)
    {
        message.setMostRecentSender(this);
        if (Main.DEBUG)
            System.out.println(this.toString() + " sending message to " + neighbor.toString() + ". Message is: " + message.toString());
        neighbor.recvMessage(message);
    }

}
