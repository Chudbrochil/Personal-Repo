import java.util.Stack;

/**
 * created by Anna Carey 10/20/17
 * <p>
 * This is a data class that contains all the information all the Rail components need to communicate with each other.
 * (request routes, reserve routes, train to travel.) Trains make all new instances of messages (Station has one exception.)
 * Other components may change the type of messages and even clone them, but only Trains make them (with the exception of the
 * Station creating a message of type 'GO'.)
 * <p>
 * Contains the routeList, which is a Stack. Public methods give access to most Stack methods (pop, push, isEmpty.)
 * <p>
 * It is safe to pass the instances of this message around using sendMessage as long as the sender then drops all pointers
 * to the message. If two copies of the message are needed (i.e., a switch needs to send a message to two components),
 * use the clone() method. NEVER retain a copy of a Message after sending it to another component.
 * <p>
 * Sign a message with setMostRecentSender(this) before sending it on (unless you wrote the message. Then mostRecentSender
 *   already equals you.) **This is done in the sendMessage() method of all Rail components.**
 */
 
public class Message
{
    public final String TRAIN;    //Train who originated this message--identifier/name.
    private Stack<IMessagable> routeList;   //Keeps track of who has passed the message. Used to retrace steps. (often message type is changed.)
    private Stack<IMessagable> poppedRouteList; //stores the rest of the list in the order it was popped off. Can be used to 'reverse' the direction of the message.
    private IMessagable mostRecentSender;
    public MessageType type;     //Enum that tells the Rail pieces what they should do with this message.
    public final String STATION; //name of the station requested or found, depending on the message type.
    private Direction heading;


    /**
     * Message()
     * @param mType MessageType. This parameter is the most important, as all Rail components consider this first in deciding
     *              how to respond to the message. MessageTypes and the way Rail components react to them are detailed in the
     *              MessageType.java file.
     * @param trainSender Name of the Train on behalf of which this message was created. This MUST be included because
     *                    tracks save this and check against it when reserving routes.
     * @param firstSender A pointer to the creater of the message. This is the first thing pushed to the routeList stack
     *                    and is also assigned to mostRecentSender.
     * @param station The name of the station for which the Train is searching in a SEARCH_FOR_ROUTE message.
     *                This parameter is unimportant for any other message types and can be included for debugging but is not necessary.
     * @param direction The heading of the Train or message (whether it is headed LEFT, RIGHT, etc.) Utilized only in
     *                  messages of type REQUEST_NEXT_TRACK for the train to be able to draw its motion accordingly.
     *                  Unnecessary elsewhere, though can be used for debugging.
     */
    public Message(MessageType mType, String trainSender, IMessagable firstSender, String station, Direction direction)
    {
        TRAIN = trainSender;
        heading = direction;
        routeList = new Stack<>();
        routeList.push(firstSender);
        poppedRouteList = new Stack<>();
        mostRecentSender = firstSender;
        type = mType;
        STATION = station;
    }

    /**
     * Message()
     * Message clone constructor
     * For making clones of messages ONLY.
     */
    private Message(MessageType m, String trainSender, Stack<IMessagable> route, Stack<IMessagable> poppedList, IMessagable recentSender,
                    String station, Direction trainHeading)
    {
        TRAIN = trainSender;
        heading = trainHeading;
        routeList = new Stack<>();
        routeList.addAll(route);
        poppedRouteList = new Stack<>();
        poppedRouteList.addAll(poppedList);
        mostRecentSender = recentSender;
        type = m;
        STATION = station;
    }

    /**
     * toString()
     * To be used for debugging purposes.
     *
     * @return message data fields in an intelligible way.
     */
    @Override
    public String toString()
    {
        return "\n Train:" + TRAIN + "\t Sender List:" + routeList.toString() + "\t Message:" + type + "\t Station:" + STATION + "\t Heading:" + heading;
    }

    /**
     * popRouteList()
     * @return The most recently added value in the routeList is returned. (The most recently visited neighbor or the next
     *         neighbor to send the message to next, depending on the type of message.)
     */
    public IMessagable popRouteList()
    {
        IMessagable popped = routeList.pop();
        poppedRouteList.push(popped);
        return popped;
    }

    /**
     * pushRouteList()
     * @param s IMessagable sender to be added to the routeList.
     */
    public void pushRouteList(IMessagable s)
    {
        routeList.push(s);
    }

    /**
     * senderListIsEmpty()
     * @return true if the routeList has more members. False if it is empty.
     */
    public boolean senderListIsEmpty()
    {
        return routeList.isEmpty();
    }

    /**
     * peekRouteList()
     * @return the most recent sender (top of the stack) without removing it.
     */
    public IMessagable peekRouteList()
    {
        return routeList.peek();
    }
    
    /**
     * getMostRecentSender()
     * @return mostRecentSender Can be used to obtain the mostRecentSender of this message. Primarily used
     *         to varify where the message came from so that the next recipient of the message can be determined.
     */
    public IMessagable getMostRecentSender()
    {
        return mostRecentSender;
    }
    
    /**
     * setMostRecentSender()
     * @param sender Should always be called as (this) before sending the message.
     */
    public void setMostRecentSender(IMessagable sender)
    {
        mostRecentSender = sender;
    }
    
    /**
     * reverseRouteList()
     * Reverses the route list as though you're reversing the order of traversal on an array list.
     * This method should be called if an ABORT_RESERVE_ROUTE is called because there is a route conflict.
     */
    public void reverseRouteList()
    {
      Stack<IMessagable> temp = routeList;
      routeList = poppedRouteList;
      poppedRouteList = temp;
    }
    
    /**
     * setHeading()
     * @param direction Direction the train or message is heading.
     */
    public void setHeading(Direction direction)
    {
        heading = direction;
    }

    /**
     * getHeading()
     * @return Direction the train and/or message is heading.
     */
    public Direction getHeading()
    {
        return heading;
    }
    
    /**
     * clone()
     * Used by switches looking for a route and rail pieces when a reservation conflict is found (to make an ABORT_RESERVE_ROUTE
     * as well as a WAIT_FOR_CLEAR_ROUTE messages to send in different directions.)
     *
     * Retains the routeList information, so cloning, rather than making an new message, is important.
     * (Also retains the poppedRouteList information, so a clone can still be 'reversed' just as the original
     * message could.)
     */
    @Override
    public Message clone()
    {
        return new Message(this.type, this.TRAIN, this.routeList, this.poppedRouteList, this.mostRecentSender,
            this.STATION, this.heading);
    }
}
