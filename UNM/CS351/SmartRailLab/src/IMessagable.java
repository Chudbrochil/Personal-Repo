public interface IMessagable
{
    void sendMessage(Message message, IMessagable neighbor);
    void recvMessage(Message message);
}
