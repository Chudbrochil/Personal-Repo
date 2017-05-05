/**
 * Anthony Galczak - agalczak@unm.edu - WGalczak@gmail.com
 * CS 251 - Lab 10 - Columns Game
 * 
 * Block object will contain 3 elements among a variable amount of block types.
 * Rotate method allows the user to rotate the block to place the block in a 
 * different configuration. Includes a method for drawing a whole block of
 * 3 elements on a Swing GUI given a Graphics object.
 * 
 * Block.java
 */

import java.awt.Color;
import java.awt.Graphics;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Block {
    
    char[] elements = new char[3];
    char[] availableChars;
    
    public Block(Random rand, int blockTypes) {
        availableChars = new char[blockTypes];
        
        // Adding elements to our character array to be used in the blocks
        // Starting at 97 for 'a' and going up from there.
        for(int i = 0; i < blockTypes; ++i) {
            availableChars[i] = (char)(97 + i);
        }
        
        int uniqueCharsIndex = availableChars.length;
        
        elements[0] = availableChars[rand.nextInt(uniqueCharsIndex)];
        elements[1] = availableChars[rand.nextInt(uniqueCharsIndex)];
        elements[2] = availableChars[rand.nextInt(uniqueCharsIndex)];
    }
    
    /**
     * Rotate method, will wrap an element from the bottom of the block to the top, 
     * top to middle and middle to bottom.
     */
    public void rotate() {
        char tmp = elements[2];
        
        elements[2] = elements[1];
        elements[1] = elements[0];
        elements[0] = tmp;      
    }
    
    /**
     * Gives the whole list of elements or chars inside the block
     * @return Character array containing elements
     */
    public char[] getElements() {
        return elements;
    }
    
    /**
     * String representation of the character array of elements in the Block.
     */
    @Override
    public String toString() {
        return(elements[0] + "\n" + elements[1] + "\n" + elements[2]);
    }
    
    /**
     * This is so a block can "draw itself", this is independent of the paint of each static
     * block that is already placed on the game board.
     * @param g Graphics object to use for drawing
     * @param x Row
     * @param y Column
     * @param cellSize How big to make the cell
     * @param rowsToHighlight How many items to highlight below the block we are painting
     */
    public void paintBlocks(Graphics g, int x, int y, int cellSize, int rowsToHighlight) {
        // Draw all 3 elements in a row
        for(int i = 0; i < 3; ++i) {
            g.setColor(ColorMap.ColorMap.get(elements[i]));
            g.fillRoundRect(x, y + i*cellSize, cellSize, cellSize, cellSize/4, cellSize/4);
        }
        
        for(int i = 0; i < rowsToHighlight; ++i) {
            g.setColor(Color.MAGENTA); // Line around the element
            g.drawRoundRect(x, y + i*cellSize, cellSize, cellSize, cellSize/4, cellSize/4);
        }
        
    }
    
}
