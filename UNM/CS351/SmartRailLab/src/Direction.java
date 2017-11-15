/**
 * Right, Left directions.
 * Used for Train facing, light facing, etc.
 */
public enum Direction
{
    /**
     * LEFT and RIGHT
     * Used arbitrarily to distinguish directions in all sorts of ways, including neighbor sides, light
     * sides, and Train headings.
     * UPRIGHT, DOWNLEFT, UPLEFT, and DOWNRIGHT
     * Used exclusively by switches to indicate the train's heading.
     */
    LEFT, RIGHT, UPRIGHT, DOWNLEFT, UPLEFT, DOWNRIGHT
}
