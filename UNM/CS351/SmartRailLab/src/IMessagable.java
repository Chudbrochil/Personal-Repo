/**
 * IMessagable
 * Interface for any class that will be receiving messages. Any IMessagable will also have neighbors to send to.
 */
public interface IMessagable
{
    void recvMessage(Message message);
    void setNeighbors(IMessagable left, IMessagable right);
}
