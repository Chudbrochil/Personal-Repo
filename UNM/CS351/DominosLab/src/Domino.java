// Anthony Galczak - agalczak@unm.edu - WGalczak@gmail.com
// Dominos Game - Lab 1 CS 351

public class Domino
{
    private int firstTile;
    private int secondTile;
    private String imgPath;
    private boolean isRotated;

    public Domino(int firstTile, int secondTile, String imgPath)
    {
        this.firstTile = firstTile;
        this.secondTile = secondTile;
        this.imgPath = imgPath;
        this.isRotated = false;
    }

    /**
     * getTileImage()
     * Gets the relative path to this domino's image.
     * @return a string corresponding to the domino's image.
     */
    public String getTileImage() { return imgPath; }

    /**
     * getIsRotated()
     * Useful for finding out how the domino's image should be displayed.
     * @return a boolean corresponding to whether or not the tile is in a rotated state, true if it's rotated
     */
    public boolean getIsRotated() { return isRotated; }

    /**
     * asciiString()
     * Used in ascii debugging to print a domino's ascii representation.
     * @return string in [0-0] form where 0 is either firstTile or secondTile
     */
    public String asciiString()
    {
        return "[" + firstTile + "-" + secondTile + "]";
    }

    /**
     * matchesLeft()
     * Checks if two dominos match with placing domino coming from left
     * @param placedDomino Domino already on the board we are comparing against
     * @return
     * True if the placedDomino matches the current domino
     * False if the placedDomino does not match the current domino
     */
    public boolean matchesLeft(Domino placedDomino)
    {
        if(this.secondTile == placedDomino.firstTile || this.secondTile == 0 || placedDomino.firstTile == 0)
        {
            return true;
        }
        return false;
    }

    /**
     * matchesRight()
     * Checks if two dominos match with placing domino coming from right
     * @param placedDomino Domino already on the board we are comparing against
     * @return
     * True if the placedDomino matches the current domino
     * False if the placedDomino does not match the current domino
     */
    public boolean matchesRight(Domino placedDomino)
    {
        if(placedDomino.secondTile == this.firstTile || placedDomino.secondTile == 0 || this.firstTile == 0)
        {
            return true;
        }
        return false;
    }

    /**
     * rotateTile()
     * Rotates a domino 180 degrees in case we want to use the domino in a different
     * configuration.
     */
    public void rotateTile()
    {
        int firstTileNum = this.firstTile;
        this.firstTile = secondTile;
        this.secondTile = firstTileNum;
        isRotated = !isRotated;
    }

}
