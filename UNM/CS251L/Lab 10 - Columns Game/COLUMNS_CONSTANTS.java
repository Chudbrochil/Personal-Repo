/**
 * Anthony Galczak - agalczak@unm.edu - WGalczak@gmail.com
 * CS 251 - Lab 10 - Columns Game
 * 
 * Contains constants for use throughout the program.
 * Primarily this contains sizing for the various game
 * types and any long strings used in help popups.
 *
 * COLUMNS_CONSTANTS.java
 */
public class COLUMNS_CONSTANTS {
    public static final int WINDOW_WIDTH = 600;
    public static final int WINDOW_HEIGHT = 760;
    public static final int BOARD_WIDTH = 420;
    public static final int BOARD_HEIGHT = 620;
    public static final int DEFAULT_ROWS = 24;
    public static final int DEFAULT_COLS = 16;
    public static final int DEFAULT_BLOCK_TYPES = 5;
    public static final int DEFAULT_CELL_SIZE = 25;
    public static final String SCORING_STRING = 
            "When making block matches your score will be calculated based on how many blocks\n"
            + "you've eliminated in a single drop. This includes a block \"combo\".\n"
            + "Removing 3 blocks - 3 points\n" + "Removing 4 blocks - 5 points\n"
            + "Removing 5 blocks - 7 points\n" + "Removing 6 blocks - 10 points\n"
            + "Removing 7 blocks - 15 points\n" + "Removing 8 blocks - 20 points\n"
            + "Removing 9 blocks - 30 points\n" + "Removing 10 or more blocks - 50 points";
    public static final String RULES_STRING = 
            "Columns is a block moving game similar to Tetris or Bejeweled.\n" 
            + "You are looking to make combinations of 3 blocks or more of the same color.\n"
            + "Matches are effective vertically, diagonally or horizontally.\n"
            + "If your blocks removed cause more matches of 3 to happen then those blocks removed\n"
            + "will count towards your score also. If you match more than one set of 3 then you\n"
            + "will score bonus points based on how many total blocks you removed.\n"
            + "See the scoring section for more details on points.";
    public static final String CONTROLS_STRING = 
            "Game controls:\n"
            + "Left arrow key: Move piece left\n"
            + "Right arrow key: Move piece right\n" 
            + "Down arrow key: Move piece down\n"
            + "Up arrow key: Rotate piece\n"
            + "Space bar: Drop piece in place";
    
    
    public static final int SMALL_ROWS = 12;
    public static final int SMALL_COLS = 8;
    public static final int SMALL_BLOCK_TYPES = 4;
    public static final int SMALL_CELL_SIZE = 50;
    
    public static final int LARGE_ROWS = 36;
    public static final int LARGE_COLS = 24;
    public static final int LARGE_BLOCK_TYPES = 6;
    public static final int LARGE_CELL_SIZE = 16;
            
}
