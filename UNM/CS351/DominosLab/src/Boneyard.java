// Anthony Galczak - agalczak@unm.edu - WGalczak@gmail.com
// Dominos Game - Lab 1 CS 351

import java.util.ArrayList;
import java.util.Collections;

public class Boneyard
{
    ArrayList<Domino> boneyard;

    public Boneyard()
    {
        boneyard = new ArrayList<Domino>();
        startBoneyard();
    }

    /**
     * startBoneyard()
     * Private method for initializing the boneyard.
     */
    private void startBoneyard()
    {
        // Instantiating all 28 dominos
        for (int i = 0; i <= 6; ++i) {
            int j = i;
            while (j >= 0) {
                String imgPath = "Dominos/" + String.valueOf(j) + "-" + String.valueOf(i) + ".png";
                boneyard.add(new Domino(j, i, imgPath));
                j--;
            }
        }

        // Want to make sure that the boneyard pieces are random so that the very first piece isn't 0-0, etc.
        Collections.shuffle(boneyard);
    }

    /**
     * printBoneyard()
     * Used in ascii debugging to print the status of the boneyard.
     * Output: Prints dominos in ascii
     */
    public void printBoneyard()
    {
        System.out.println("Boneyard: " + boneyard.size() + " tiles remaining");
        for(int i = 0; i < boneyard.size(); ++i)
        {
            System.out.print(boneyard.get(i).asciiString() + " " );
        }
        System.out.println();
    }

    /**
     * drawPiece()
     * Draws a domino off from the boneyard. This domino will be removed from the
     * data structure holding the dominos.
     * @returns the domino that was removed from the boneyard.
     */
    public Domino drawPiece()
    {
        if(boneyard.size() > 0)
        {
            // Removing the first element of the boneyard
            Domino poppedDomino = boneyard.get(0);
            boneyard.remove(0);
            return poppedDomino;
        }
        else { return null; }

    }

    /**
     * getSize()
     * Useful for finding out how many pieces are left in the boneyard and if it can no longer be drawn from.
     * @return the size of the boneyard as an int.
     */
    public int getSize() { return boneyard.size(); }
}
