import java.util.Stack;

/**
 * created by Anna Carey 10/20/17
 *
 * This is a data class that contains all the information all the Rail components need to communicate with each other.
 *   (request routes, reserve routes, train to travel.)
 *
 * Contains the senderList, which is a Stack. Public methods give access to most Stack methods (pop, push, isEmpty,
 */
public class Message
{
    public final String TRAIN;    //Train who originated this message--either pointer to or identifier/name.
    private Stack<IMessagable> senderList;   //Keeps track of who has passed the message. Used to retrace steps. (often message type is changed.)
    //todo: will need to make senderList into a list if we want to retrace our steps.
    public MessageType type;     //Enum that tells the Rail pieces what they should do with this message.
    public final String STATION; //name of the station requested or found, depending on the message type.
  
  
    public Message(String trainSender, IMessagable firstSender, MessageType m, String station)
    {
        TRAIN = trainSender;
        senderList = new Stack<>();
        senderList.push(firstSender);
        type = m;
        STATION = station;
    }
  
    /**
     * To be used for debugging purposes.
     * @return message data fields in an intelligible way.
    */
     @Override
    public String toString()
  {
    return "Train:"+TRAIN+"\t Most recent sender:"+ senderList.peek() +"\t Message:"+ type;
  }
    
    /**
     * @return The most recently added value in the senderList is returned. (The most recently visited neighbor.)
     */
    public IMessagable popSenderList()
  {
    return senderList.pop();
  }
    
    /**
     * @param s IMessagable sender to be added to the senderList.
     */
    public void pushSenderList(IMessagable s)
  {
    senderList.push(s);
  }
    
    /**
     * @return true if the senderList has more members. False if it is empty.
     */
    public boolean senderListIsEmpty()
    {
       return senderList.isEmpty();
    }
    
    /**
     * @return the most recent sender (top of the stack) without removing it.
     */
    public IMessagable peekSenderList()
    {
        return senderList.peek();
    }
}
