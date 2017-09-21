// Anthony Galczak - agalczak@unm.edu - WGalczak@gmail.com
// Dominos Game - Lab 1 CS 351

import java.util.ArrayList;

public class Player
{
    private static int playerIncrement = 1;
    private ArrayList<Domino> hand;
    private String playerName;
    private boolean isComputer = false;

    public Player()
    {
        hand = new ArrayList<Domino>();
        playerName = "Player" + playerIncrement;
        playerIncrement++;
    }

    public Player(String playerName)
    {
        hand = new ArrayList<Domino>();
        this.playerName = playerName;
    }

    public Player(boolean isComputer)
    {
        hand = new ArrayList<Domino>();
        this.isComputer = isComputer;
        this.playerName = "Computer";
    }

    /**
     * getName()
     * Gets a player's name.
     * @return player's name as a String.
     */
    public String getName() { return playerName; }

    /**
     * addToHand()
     * Adds the domino to the player's hand.
     * @param dominoToAdd Actual Domino to add to the player's hand.
     */
    public void addToHand(Domino dominoToAdd)
    {
        hand.add(dominoToAdd);
    }

    /**
     * removeFromHand()
     * Removes the domino from the player's hand based on index
     * @param dominoIndex Element domino to remove
     */
    public void removeFromHand(int dominoIndex)
    {
        hand.remove(dominoIndex);
    }

    /**
     * getDomino()
     * Returns a given domino from player's hand.
     * @param dominoIndex Integer index of the domino to return.
     * @return The domino that was requested
     */
    public Domino getDomino(int dominoIndex) { return hand.get(dominoIndex); }

    /**
     * initializeHand()
     * Populates a player's hand with a given number of dominos from the boneyard.
     * @param boneyard Bringing in boneyard so that I can draw pieces from it.
     * @param numOfStartingDoms Integer that comes in to specify how many dominos to draw to start a hand.
     */
    public void initializeHand(Boneyard boneyard, int numOfStartingDoms)
    {
        for(int i = 0; i < numOfStartingDoms; ++i)
        {
            this.addToHand(boneyard.drawPiece());
        }
    }

    /**
     * getSize()
     * @return Returns the size of the player's hand.
     */
    public int getSize()
    {
        return hand.size();
    }

    /**
     * isComputer()
     * @return Returns a boolean that is true if this player is a computer. False if it isnt a computer.
     */
    public boolean getIsComputer() { return isComputer; }

    /**
     * getPlayableIndex()
     * @param leftDomino Left domino of the current playing board
     * @param rightDomino Right domino of the current playing board
     * @return Returns the index of the playable domino. If no playable domino is found, returns -1.
     */
    public int getPlayableIndex(Domino leftDomino, Domino rightDomino)
    {
        for(int i = 0; i < hand.size(); ++i)
        {
            Domino dominoToCheck = hand.get(i);

            // Checking normal configuration matches
            if(dominoToCheck.matchesLeft(leftDomino) || dominoToCheck.matchesRight(rightDomino))
            {
                return i;
            }

            // Checking rotated configuration matches
            dominoToCheck.rotateTile();
            if(dominoToCheck.matchesLeft(leftDomino) || dominoToCheck.matchesRight(rightDomino))
            {
                dominoToCheck.rotateTile();
                return i;
            }
            dominoToCheck.rotateTile(); // Rotating the tile back if it isn't a match

        }
        // If no matching domino is found
        return -1;
    }

    /**
     * printHand()
     * ASCII printing of the player's hand.
     */
    public void printHand()
    {
        System.out.println(this.playerName + "\'s Hand");
        for(int i = 0; i < hand.size(); ++i)
        {
            System.out.print(hand.get(i).asciiString() + " " );
        }
        System.out.println();
    }

}
