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

    private boolean isMoving = false; //True the train has received a valid 'GO' message and is proceeding along the track.
    private String destination = null;  //Save the name of a Station. Used by train to double check GO signals to make sure
    //the route goes to the desired location.
    private Direction heading;       //which way is the train heading?
    private GraphicsContext gcDraw;

    private int canvasX;
    private int canvasY;

    // Details of the image gotten from the actual jpg image
    private final int knownTrainSizeX = 69;
    private final int knownTrainSizeY = 14;

    private Queue<String> destinations;

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
        destinations = new ConcurrentLinkedQueue<>();
    }

    /**
     * getisMoving()
     * This method is needed because trains are able to be re-selected after being given a destination.
     * A user could theoretically give orders to an already moving train. We don't want that.
     * @return True if train is moving/isMoving, false if in station/trainyard
     */
    public boolean getIsMoving() { return isMoving; }

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
     * This is where a the IMessagable receives a message and then is notified to process it.
     * @param message Message to be added to pendingMessages
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
        if(destination == null)
        {
            destination = station;
            Message message = new Message(MessageType.SEARCH_FOR_ROUTE, NAME, this, station, null);
            sendMessage(message, currentTrack);
        }
        else
        {
            destinations.add(station);
        }

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
     * Observes what type of message m is and calls the appropriate method to respond to it, if implementation
     *          for that type of message has been written.
     * Implementations written for: GO, WAIT_FOR_CLEAR_ROUTE, REQUEST_NEXT_TRACK.
     */
    private void readMessage(Message m)
    {
        switch(m.type)
        {
            case GO: readMessageGo(m);
                break;
            case WAIT_FOR_CLEAR_ROUTE: readMessageWaitForClearRoute(m);
                break;
            case REQUEST_NEXT_TRACK: readMessageRequestNextTrack(m);
                break;
            case NO_ROUTE_FOUND: readMessageNoRouteFound(m);
                break;
            default: if(Main.DEBUG) System.out.println(toString()+ "received a message of type "+m.type.toString()+
                " for which there is no implementation.");
                break;
        }
    }
    
    /**
     * readMessageGo(Message m)
     * @param m
     *      GO
     *          Receives this message fom a station when a route has been successfully found for this train.
     *          The Train then sends a REQUEST_NEXT_TRACK message to its currentTrack (the Station.)
     */
    private void readMessageGo(Message m)
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
                isMoving = true;
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
    
    /**
     * readMessageWaitForClearRoute(Message m)
     * @param m Message of MessageType.WAIT_FOR_CLEAR_ROUTE
     * WAIT_FOR_CLEAR_ROUTE
     *          Receiveing this message means that a route was found to the Station destination, but it is busy at this time.
     *          So, wait for a bit, and then request the route again.
     */
    private void readMessageWaitForClearRoute(Message m)
    {
        if(Main.DEBUG) System.out.println(toString()+" received a "+MessageType.WAIT_FOR_CLEAR_ROUTE.toString()+" message. ");
        Notifications.updateSimStatus(toString()+" has been told to wait for the route to clear to "+destination);
        try { Thread.sleep(5000); } catch (InterruptedException e){}
        //We have to clear the current destination so that this method won't just add it to the queue.
        String tempDest = destination;
        destination = null;
        requestRoute(tempDest);
    }
    
    /**
     * readMessageRequestNextTrack(Message m)
     * @param m Message of MessageType.REQUEST_NEXT_TRACK
     * REQUEST_NEXT_TRACK
     *          Pops from the route list the nextTrack, then makes sure the current track signed the message(next in
     *          the route list.) If it was the current track that sent the message, proceedTo(nextTrack) is called.
     *          Then the train gets a new heading.
     *          Finally, checks if it has arrived at 'destination' by comparing the toString() of the 'currentTrack'
     *          to 'destination.' If the strings are equal, 'isMoving' becomes false and the train has 'arrived.'
     *
     *          If the train has not 'arrived,' it must request the next track, so it pushes itself to the sender list
     *          and sends the message to the currentTrack.
     */
    private void readMessageRequestNextTrack(Message m)
    {
    
        //Train should only receive this message if it sent it.
        //Train pops the first reference off the sender list, which is the track it should be going to. Then
        //checks the next sender list reference, which is the actual sender. This reference STAYS. The train pushes
        //itself, then sends this message on to the new currentTrack. (The result is a sender list that contains
        //the [PreviousTrack, Train] going to currentTrack.
        if (isMoving)
        {
            IMessagable nextTrack = m.popRouteList();
            if (m.peekRouteList() == currentTrack)
            {
                //If it's in the station, get the reference to the next track so that you're at the "beginning" of the track before drawing yourself.
                heading = m.getHeading(); //get the heading so you know which way to draw yourself moving
                proceedTo(nextTrack); //may be a sleep in this method. currentTrack becomes nextTrack.
            
                //checks if it's arrived at the station name saved in destination.
                if (currentTrack.toString().equals(destination))
                {
                    currentTrack = nextTrack;
                    System.out.println(toString() + " has arrived at destination, " + destination + "!");
                    Notifications.updateSimStatus(toString() + " has arrived at destination, " + destination + "!");
                    Notifications.playSound("Train_Arriving.wav");
                    isMoving = false;

                    checkForNewDestination();
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
            System.err.println(toString() + " received a REQUEST_NEXT_TRACK message when 'isMoving' status is false.");
        }
    }

    /**
     * readMessagNoRouteFound()
     * A train needs to know if it didn't find a route to the station it asked for.
     * @param m The message that we received
     */
    private void readMessageNoRouteFound(Message m)
    {
        Notifications.updateSimStatus("No route found to: " + m.STATION + ".");
        if(m.STATION.equals(destination))
        {
            checkForNewDestination();
        }
        else System.err.println(toString() + " received a NO_ROUTE_FOUND message for a station that isn't it's current destination.");
    }

    /**
     * checkForNewDestination()
     * This method looks through the list of destinations to see if there is another station to go to.
     */
    private void checkForNewDestination()
    {
        // This is necessary in case the user has clicked on a moving train and wants to send it to another station
        destination = null;
        if(!destinations.isEmpty()) { requestRoute(destinations.poll()); }
    }
    
    /**
     * proceedTo()
     * @param nextTrack a reference to the next track the train will be on.
     *                  This method sets currentTrack to next track.
     *                  The draw method will use the current 'heading' and the current position to draw in the location.
     *                  When this method finishes executing, the train will be drawn in the center of the NEXT component
     *                  (nextTrack) and the currentTrack is sent an 'GOODBYE_UNRESERVE' message and currentTrack becomes
     *                  nextTrack.
     */
    private void proceedTo(IMessagable nextTrack)
    {
        // Each track section is 100 pixels long. This will move 100 pixels.
        // Sleeping for 40 milliseconds results in a 4 second traversal per piece of track.
        // This could be easily configurable, but where? The user may not want this granularity
        
        //This allows the train to move out of the station and onto the track before doing its movement 'on that track'
        //(thus it unreserves when it leaves it.) This also allows the train to move into the station, but no further, when it arrives.
        //one piece to another.) So this could be improved,but it is good enough for now.
        int loopIterations = 100;

        for (int i = 0; i < loopIterations; ++i)
        {
            if (heading == Direction.RIGHT)
            {
                canvasX += 1;
            }
            else if (heading == Direction.LEFT)
            {
                canvasX -= 1;
            }
            //(The trains now arrive on the stations, not the 100*switchesTraveled pixels away from the station. XD
            else if (heading == Direction.UPRIGHT || heading == Direction.UPLEFT)
            {
                canvasY -= 1;
            }
            else if (heading == Direction.DOWNLEFT || heading == Direction.DOWNRIGHT)
            {
                canvasY += 1;
            }


            try
            {
                Thread.sleep(40);
            }
            catch (InterruptedException e)
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
