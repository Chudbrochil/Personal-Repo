/**
 * IMessagable
 * Interface for any class that will be receiving messages. Any IMessagable will also have neighbors to send to.
 * There can be more or less neighbors than two, but the component must implement setNeighbors as shown.
 */
public interface IMessagable
{
    void recvMessage(Message message);
    void setNeighbors(IMessagable left, IMessagable right);
}
