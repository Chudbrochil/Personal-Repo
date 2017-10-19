package track;

/**
 * Anna Carey 10/18/17
 *
 * Any class that implements this method has a draw() method, which
 *   draws itself on a canvas according to its current state.
 */
public interface Drawable
{
  /**
   * @param x x location to begin drawing on the canvas
   * @param y y location to begin drawing on the canvas
   * Draws the object on a canvas at location x,y according to its currrent state.
   */
  public void draw(int x, int y);
}
