/**
 * created by Anna Carey 10/20/17
 *
 * This is a data class that contains all the information all the Rail components need to communicate with each other.
 *   (request routes, reserve routes, train to travel.)
 */
public class Message
{
  public final String TRAIN;    //Train who originated this message--either pointer to or identifier/name.
  private IMessagable mostRecentSender;   //Most recent sender of the message (pointer to or name of left or right neighbor)
  //todo: will need to make mostRecentSender into a list if we want to retrace our steps.
  public final MessageType TYPE;  //String of message.
  
  
  public Message(String trainSender, IMessagable sender, MessageType m)
  {
    TRAIN = trainSender;
    mostRecentSender = sender;
    TYPE = m;
  }
  
  /**
   * To be used for debugging purposes.
   * @return message data fields in an intelligible way.
   */
  @Override
  public String toString()
  {
    return "Train:"+TRAIN+"\t Most recent sender:"+mostRecentSender+"\t Message:"+ TYPE;
  }
  
  public IMessagable getMostRecentSender()
  {
    return mostRecentSender;
  }
  
  public void setMostRecentSender(IMessagable s)
  {
    mostRecentSender = s;
  }
}
