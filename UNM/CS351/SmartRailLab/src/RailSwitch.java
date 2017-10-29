import javafx.scene.canvas.GraphicsContext;

public class RailSwitch implements IMessagable, IDrawable
{
    IMessagable leftNeighbor;
    IMessagable rightNeighbor;

    public void draw(int x, int y, GraphicsContext gc)
    {

    }
    
    public void sendMessage(Message message, IMessagable neighbor){}
    public void recvMessage(Message message){}
    public void setLeftNeighbor(IMessagable neighbor) { this.leftNeighbor = neighbor; }
    public void setRightNeighbor(IMessagable neighbor) { this.rightNeighbor = neighbor; }
}
