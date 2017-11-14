import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

/**
 * Anna Carey 10/18/17
 * <p>
 * Track compoment. If not green in the train's current traveling direction, the train is expected to stop before crossing.
 */
public class RailLight implements IDrawable
{
    private Direction greenDirection = null; //LEFT, RIGHT, Or NULL. Null means both directions are red.
    private boolean reserved; //Has this track been reserved for a route?
    private String NAME;
    private static int lightIncrement = 1;
    private static Image greenLightImg, redLightImg;

    private GraphicsContext gcDraw;
    private int canvasX;
    private int canvasY;

    /**
     * RailLight()
     * RailLight's constructor. This is a light that will be displayed on the UI. It isn't able to be messaged to as
     * it is a fairly simple object. It will be attached to either a RailTrack or a RailSwitch.
     * This is the constructor used if it's being put in the "mid" position or it's on a track.
     * @param gcDraw Graphics context given to draw on canvas
     * @param x x-coord to draw light on
     * @param y y-coord to draw light on
     */
    public RailLight(GraphicsContext gcDraw, int x, int y)
    {
        NAME = "Light" + lightIncrement;
        lightIncrement++;
        if (greenLightImg == null)
        {
            greenLightImg = new Image("GreenLightNoPole.png");
        }
        if (redLightImg == null)
        {
            redLightImg = new Image("RedLightNoPole.png");
        }
        this.gcDraw = gcDraw;
        canvasX = x + 45;
        canvasY = y - 26; // Light height is 26
    }

    /**
     * RailLight()
     * RailLight's constructor with a lightPosition. This is used if we're sticking the light on a RailSwitch
     */
    public RailLight(GraphicsContext gcDraw, int x, int y, Direction lightPosition)
    {
        this(gcDraw, x, y);
        if (lightPosition == Direction.LEFT)
        {
            canvasX = x;
        }
        else if (lightPosition == Direction.RIGHT)
        {
            canvasX = x + 90;
        }
    }
    
    /**
     * reserve()
     * @param switchEngaged boolean of whether the switch is engaged. If it is, light needs to be red. Otherwise, green.
     * To be used by RailTracks with lights on them to permit trains to pass over them.
     * @param trainComingFrom Direction the train will come from. I.e, direction light should shine green.
     *                        if switchEngaged, this parameter will not be looked at--the light will be red.
     *
     * To be used by RailSwitches to reserve their lights appropriately. If switchEngaged, light is red and reserved.
     *   Otherwise, light is green in the same way it is for tracks.
     */
    public void reserve(boolean switchEngaged, Direction trainComingFrom)
    {
        if(switchEngaged)
        {
            greenDirection = null; //Light is red.
        }
        else
        {
            greenDirection = trainComingFrom;
        }
        reserved = true;
    }
    
    
    /**
     * reserve()
     * @param trainComingFrom The direction the train is coming from.
     * To be used by RailTracks with lights on them to permit trains to pass over them.
     */
    public void reserve(Direction trainComingFrom)
    {
        greenDirection = trainComingFrom;
        reserved = true;
    }

    /**
     * unreserve()
     * Unreserving this light, it's available for use for future routes now.
     */
    public void unreserve()
    {
        reserved = false;
        greenDirection = null;
    }

    /**
     * getGreenDirection()
     * @return the Direction in which the 'green' light is shining (greenDirection)
     */
    public Direction getGreenDirection()
    {
        return greenDirection;
    }

    public String toString()
    {
        return NAME;
    }

    /**
     * draw()
     * Draws the object on a canvas at location x,y according to its currrent state.
     */
    public void draw()
    {
        //todo: Can make light bidirectional later. Functionality here. Just not displayed.
        if (greenDirection != null)
        {
            gcDraw.drawImage(greenLightImg, canvasX, canvasY);
        }
        else
        {
            gcDraw.drawImage(redLightImg, canvasX, canvasY);
        }
    }
}
