import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

/**
 * Anna Carey 10/18/17
 *
 * Track compoment. If not green in the train's current traveling direction, the train is expected to stop before crossing.
 */
public class RailLight implements IDrawable
{
    private boolean isGreenLeft; //Is the light shining green to the left? OR can make an emum left/right.
    private boolean reserved; //Has this track been reserved for a route?
    private String NAME;
    private static int lightIncrement = 1;
    private static Image greenLightImg, redLightImg;

    public RailLight()
    {
        NAME = "Light" + lightIncrement;
        lightIncrement++;
        if(greenLightImg == null) { greenLightImg = new Image("GreenLightNoPole.png"); }
        if(redLightImg == null) { redLightImg = new Image("RedLightNoPole.png"); }
    }

      public void reserve()
      {
        reserved = true;
      }

      public void unreserve() //I would call this 'free', but that sounds confusing.
      {
        reserved = false;
      }

      public String toString() { return NAME; }

      /**
       *
       * @param x x location to begin drawing on the canvas
       * @param y y location to begin drawing on the canvas
       * Draws the object on a canvas at location x,y according to its currrent state.
       */
      public void draw(int x, int y, GraphicsContext gcDraw)
      {
          //gcDraw.fillText(this.toString(), x, y - 30);
          gcDraw.drawImage(redLightImg, x + 30, y-50);
      }
}
