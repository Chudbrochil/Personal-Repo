import java.util.Stack;

/**
 * created by Anna Carey 10/20/17
 * <p>
 * This is a data class that contains all the information all the Rail components need to communicate with each other.
 * (request routes, reserve routes, train to travel.)
 * <p>
 * Contains the routeList, which is a Stack. Public methods give access to most Stack methods (pop, push, isEmpty.)
 *
 * Sign a message with setMostRecentSender(this) before sending it on (unless you wrote the message. Then mostRecentSender
 *   already equals you.) **This is done in the sendMessage() method of all Rail components.***
 */
 
public class Message
{
    public final String TRAIN;    //Train who originated this message--identifier/name.
    private Stack<IMessagable> routeList;   //Keeps track of who has passed the message. Used to retrace steps. (often message type is changed.)
    private Stack<IMessagable> poppedRouteList;
    private IMessagable mostRecentSender;
    public MessageType type;     //Enum that tells the Rail pieces what they should do with this message.
    public final String STATION; //name of the station requested or found, depending on the message type.
    private Direction heading;


    public Message(MessageType m, String trainSender, IMessagable firstSender, String station, Direction direction)
    {
        TRAIN = trainSender;
        heading = direction;
        routeList = new Stack<>();
        routeList.push(firstSender);
        poppedRouteList = new Stack<>();
        mostRecentSender = firstSender;
        type = m;
        STATION = station;
    }

    /**
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
     * @param s IMessagable sender to be added to the routeList.
     */
    public void pushRouteList(IMessagable s)
    {
        routeList.push(s);
    }

    /**
     * @return true if the routeList has more members. False if it is empty.
     */
    public boolean senderListIsEmpty()
    {
        return routeList.isEmpty();
    }

    /**
     * @return the most recent sender (top of the stack) without removing it.
     */
    public IMessagable peekRouteList()
    {
        return routeList.peek();
    }
    
    /**
     * @return mostRecentSender
     */
    public IMessagable getMostRecentSender()
    {
        return mostRecentSender;
    }
    
    /**
     * @param sender Should always be called as (this) before sending the message.
     */
    public void setMostRecentSender(IMessagable sender)
    {
        mostRecentSender = sender;
    }
    
    
    /**
     * @param direction Direction the train or message is heading.
     */
    public void setHeading(Direction direction)
    {
        heading = direction;
    }

    /**
     * @return Direction the train and/or message is heading.
     */
    public Direction getHeading()
    {
        return heading;
    }

    @Override
    public Message clone()
    {
        return new Message(this.type, this.TRAIN, this.routeList, this.poppedRouteList, this.mostRecentSender,
            this.STATION, this.heading);
    }
}
