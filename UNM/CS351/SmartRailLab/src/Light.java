/**
 * Anna Carey 10/18/17
 *
 * Track compoment. If not green in the train's current traveling direction, the train is expected to stop before crossing.
 */
public class Light //implements IMessagable
{
  private boolean isGreenLeft; //Is the light shining green to the left? OR can make an emum left/right.
  private boolean reserved; //Has this track been reserved for a route?
  
  public void reserve()
  {
    reserved = true;
  }
  
  public void unreserve() //I would call this 'free', but that sounds confusing.
  {
    reserved = false;
  }
  
  /**
   *
   * @param x x location to begin drawing on the canvas
   * @param y y location to begin drawing on the canvas
   * Draws the object on a canvas at location x,y according to its currrent state.
   */
  public void draw(int x, int y)
  {
  
  }
}
