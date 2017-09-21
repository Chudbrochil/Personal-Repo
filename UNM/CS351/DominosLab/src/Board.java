// Anthony Galczak - agalczak@unm.edu - WGalczak@gmail.com
// Dominos Game - Lab 1 CS 351

import java.util.ArrayList;

public class Board
{
    private ArrayList<Domino> board;

    public Board()
    {
        board = new ArrayList<Domino>();
    }

    /**
     * placePiece()
     * Checks if a given domino matches the board on the left or right.
     * Knows to check if we're checking left or right based on boolean parameter.
     * @param placingDomino Domino we are trying to place
     * @param isLeft If true, we're comparing on left side. False, comparing right side.
     * @return True if the placement is valid, false if that placement is invalid.
     */
    public boolean placePiece(Domino placingDomino, boolean isLeft)
    {
        // If the board is of size 0, all moves are valid
        if(board.size() == 0 ||
                (isLeft == true && placingDomino.matchesLeft(board.get(0))) ||
                (isLeft != true && placingDomino.matchesRight(board.get(board.size() - 1))))
        {
            addToBoard(placingDomino, isLeft);
            return true;
        }
        else { return false; }
    }

    /**
     * getSize()
     * @return the size of the board as an int.
     */
    public int getSize() { return board.size(); }

    /**
     * getDomino()
     * @param dominoIndex Integer index of the domino to return.
     * @return The domino that was requested
     */
    public Domino getDomino(int dominoIndex) { return board.get(dominoIndex); }

    /**
     * printBoard()
     * ASCII printing of the game board.
     */
    public void printBoard()
    {
        System.out.println("Game Board:");
        for(int i = 0; i < board.size(); ++i)
        {
            System.out.print(board.get(i).asciiString() + " " );
        }
        System.out.println();
    }

    /**
     * addToBoard()
     * Adds a domino to the board after it has been validated.
     * @param placingDomino The domino to place.
     * @param isLeft Boolean specifying whether to place on left or right.
     */
    private void addToBoard(Domino placingDomino, boolean isLeft)
    {
        if(isLeft) { board.add(0, placingDomino); }
        else { board.add(placingDomino); }
    }

}
