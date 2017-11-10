import java.util.Stack;

/**
 * created by Anna Carey 10/20/17
 *
 * This is a data class that contains all the information all the Rail components need to communicate with each other.
 *   (request routes, reserve routes, train to travel.)
 *
 * Contains the senderList, which is a Stack. Public methods give access to most Stack methods (pop, push, isEmpty.)
 */
public class Message
{
    public final String TRAIN;    //Train who originated this message--identifier/name.
    private Stack<IMessagable> senderList;   //Keeps track of who has passed the message. Used to retrace steps. (often message type is changed.)
    public MessageType type;     //Enum that tells the Rail pieces what they should do with this message.
    public final String STATION; //name of the station requested or found, depending on the message type.
    private Direction heading;
  
  
    public Message(String trainSender, IMessagable firstSender, MessageType m, String station, Direction direction)
    {
        TRAIN = trainSender;
        heading = direction;
        senderList = new Stack<>();
        senderList.push(firstSender);
        type = m;
        STATION = station;
    }
    
    /**
     * For making clones of messages ONLY.
     */
    private Message(String trainSender, Stack<IMessagable> senders, MessageType m, String station, Direction trainHeading)
    {
        TRAIN = trainSender;
        heading = trainHeading;
        senderList = new Stack<>();
        senderList.addAll(senders);
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
         return "\n Train:"+TRAIN+"\t Sender List:"+ senderList.toString() +"\t Message:"+ type +"\t Station:"+STATION+"\t Heading:"+heading;
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
    
    /**
     *
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
        return new Message(this.TRAIN, this.senderList, this.type, this.STATION, this.heading);
    }
}
