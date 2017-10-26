public interface IMessagable
{
    void sendMessage(Message message, IMessagable neighbor);
    void recvMessage(Message message);
    //String getType(); //Returns the class name.
}
