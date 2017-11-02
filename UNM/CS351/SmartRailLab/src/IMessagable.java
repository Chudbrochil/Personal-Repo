public interface IMessagable
{
    void recvMessage(Message message);
    void setNeighbors(IMessagable left, IMessagable right);
    //String getType(); //Returns the class name.
}
