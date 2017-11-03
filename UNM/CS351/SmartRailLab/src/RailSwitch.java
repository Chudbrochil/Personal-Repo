import javafx.scene.canvas.GraphicsContext;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class RailSwitch extends Thread implements IMessagable, IDrawable
{
    //public final String NAME;
    private static int switchIncrement = 1;
    private Queue<Message> pendingMessages = new ConcurrentLinkedQueue<>(); //list of all messages, held in order of receiving them, to be acknowledged.
    private boolean DEBUG = true;
    IMessagable perminateNeighbor;
    IMessagable rightNeighbor;
    IMessagable downNeighbor;
    

    public void draw(int x, int y, GraphicsContext gc)
    {

    }
    
    private synchronized void sendMessage(Message message, IMessagable neighbor){}
    public synchronized void recvMessage(Message message)
    {
        if(DEBUG) System.out.println(this.toString()+" received a message. Message is: "+message.toString());
        pendingMessages.add(message);
        this.notify();
    }
    
    public void setNeighbors(IMessagable left, IMessagable right)
    {
        perminateNeighbor = left;
        rightNeighbor = right;
    }
    
    public void setNeighbors(IMessagable perminate, IMessagable up, IMessagable down)
    {
        
    }
}
