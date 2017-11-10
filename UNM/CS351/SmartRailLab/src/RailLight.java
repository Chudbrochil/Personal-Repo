import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

/**
 * Anna Carey 10/18/17
 *
 * Track compoment. If not green in the train's current traveling direction, the train is expected to stop before crossing.
 */
public class RailLight implements IDrawable
{
    private Direction greenDirection = null; //just a default direction.
    private boolean reserved; //Has this track been reserved for a route?
    private String NAME;
    private static int lightIncrement = 1;
    private static Image greenLightImg, redLightImg;

    private GraphicsContext gcDraw;
    private int canvasX;
    private int canvasY;



    public RailLight()
    {
        NAME = "Light" + lightIncrement;
        lightIncrement++;
        if(greenLightImg == null) { greenLightImg = new Image("GreenLightNoPole.png"); }
        if(redLightImg == null) { redLightImg = new Image("RedLightNoPole.png"); }
    }

    public RailLight(GraphicsContext gcDraw, int x, int y)
    {
        this();
        this.gcDraw = gcDraw;
        canvasX = x;
        canvasY = y;
    }

      public void reserve(Direction trainComingFrom)
      {
          greenDirection = trainComingFrom;
          reserved = true;
      }

      public void unreserve() //I would call this 'free', but that sounds confusing.
      {
          //Don't need to 'turn off' light.
          reserved = false;
          greenDirection = null;
      }
    
    /**
     *
     * @return the Direction in which the 'green' light is shining (greenDirection)
     */
    public Direction getGreenDirection()
      {
          return greenDirection;
      }

      public String toString() { return NAME; }

      /**
       * Draws the object on a canvas at location x,y according to its currrent state.
       */
      public void draw()
      {
          //gcDraw.fillText(this.toString(), x, y - 30);
          if(reserved == true) { gcDraw.drawImage(greenLightImg, canvasX + 30, canvasY - 50); }
          else { gcDraw.drawImage(redLightImg, canvasX + 30, canvasY - 50); }
      }
}
