/**
 * Anthony Galczak - agalczak@unm.edu - WGalczak@gmail.com
 * CS 251 - Lab 10 - Columns Game
 * 
 * Serves somewhat as a Controller between the "real GUI" of the frame
 * and the model class BlockManager. Reality is this class performs some
 * logic around the moving block and handling it.
 * 
 * This class handles all of the painting of the moving block as well as
 * the static blocks on the "ground" of the board.
 * 
 * Any sounds coming from the game come from this class.
 * 
 * The timer in the frame above the hierarchy runs this class' "timer" via
 * the setPlaying method. If isPlaying is set to true then all the listeners
 * are listening and we are set to play. Otherwise, everything is ignored.
 * 
 * GameBoard.java
 */

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.JLabel;
import javax.swing.Timer;

public class GameBoard extends GamePanel implements ActionListener {
    
    char[][] charGameBoard;
    private int numRows;
    private int numCols;
    private int cellSize;
    private int blockTypes;
    BlockManager bm;
    private int currentBlockRow;
    private int currentBlockCol;
    private Block dropBlock;
    private boolean isPlaying = false;
    private int globalScore;
    private int blockNum;
    private int globalBlocks;
    private static ColumnsFrame cf;
    private Timer shortTimer;
    Set<Coordinate> winningElements;
    
    public GameBoard(int numRows, int numCols, int cellSize, int blockTypes, ColumnsFrame cf) {
        this.numRows = numRows;
        this.numCols = numCols;
        this.cellSize = cellSize;
        this.blockTypes = blockTypes;
        GameBoard.cf = cf;
        bm = new BlockManager(numRows, numCols, blockTypes);
        charGameBoard = bm.getGameBoard();
        winningElements = new HashSet<Coordinate>();
        
        // A timer to tick on removing pieces from the board
        shortTimer = new Timer(200, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                
                // Finding out how many blocks we are getting off from the winners
                setBlockNum(blockNum + winningElements.size());
                bm.removeBlocks(winningElements);
                
                winningElements = bm.globalSearch();
                repaint();

                if(winningElements.size() == 0) {
                    shortTimer.stop();
                    addToScore(blockNum);
                    globalBlocks += blockNum;
                    setBlockNum(0);
                }
            }
        });

        addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent ev) {
                if(isPlaying){
                    switch(ev.getKeyCode()) {
                    case KeyEvent.VK_DOWN:
                        moveBlockDown();
                        break;
                    case KeyEvent.VK_UP:
                        rotateCurrentBlock();
                        break;
                    case KeyEvent.VK_SPACE:
                        dropPiece();
                        break;
                    case KeyEvent.VK_LEFT:
                        moveBlockLeft();
                        break;
                    case KeyEvent.VK_RIGHT:
                        moveBlockRight();
                        break;
                    }
                    
                }

            }
        });
        
        initialize();
        
    }
    
    public void setBlockNum(int blocks) {
        blockNum = blocks;
    }
    
    // Things that will be done in a brand new game OR re-starting a game
    public void initialize() {
        globalScore = 0;
        globalBlocks = 0;
        drawNewBlock();
    }
    
    public void rotateCurrentBlock() {
        dropBlock.rotate();
        repaint();
    }
    
    // https://www.freesound.org/people/fartheststar/sounds/201809/
    // http://stackoverflow.com/questions/15526255/best-way-to-get-sound-on-button-press-for-a-java-calculator
    public void playSound() throws Exception {
        String soundName = "chip.wav";
        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(soundName).getAbsoluteFile());  
        Clip clip = AudioSystem.getClip();
        clip.open(audioInputStream);
        clip.start();
    }
    
    // Actually dropping a piece in the BlockManager
    public void dropPiece() {
        Coordinate checkCoord = bm.dropBlock(dropBlock, currentBlockCol);
        repaint();
        
        // If we went over the top of the board, end the game
        if(checkCoord.getRow() == 0 || checkCoord.getRow() == 1 || checkCoord.getRow() == -1) {
            gameOver();
        }
        
        winningElements = bm.checkBoardAfterDrop(checkCoord);
        
        try {
            if(winningElements.size() != 0) playSound();
        }
        catch(Exception e) {
            System.out.println("Looks like we couldn't find the sound file.\n" + e.getMessage());
        }
        
        if(winningElements.size() >= 3) {
            shortTimer.start();
        }
        
        drawNewBlock();
    }
    
    public void gameOver() {
        // Clearing the model game board
        bm.clearBoard();

        // Initiating game over sequence
        isPlaying = false;
        globalScore = 0;
        globalBlocks = 0;
        cf.gameOver();
    }
    
    public void addToScore(int dropScore) {
        if(dropScore == 3) globalScore += 3;
        if(dropScore == 4) globalScore += 5;
        if(dropScore == 5) globalScore += 7;
        if(dropScore == 6) globalScore += 10;
        if(dropScore == 7) globalScore += 15;
        if(dropScore == 8) globalScore += 20;
        if(dropScore == 9) globalScore += 30;
        if(dropScore >= 10) globalScore += 50;
    }
    
    public void drawNewBlock() {
        dropBlock = new Block(new Random(System.nanoTime()), blockTypes);
        currentBlockRow = 0;
        currentBlockCol = numCols / 2;
        repaint();
    }
    
    public void moveBlockDown() {
        currentBlockRow++;
        placeIfTouchingBottom();
        repaint();
    }
    
    public void moveBlockLeft() {
        if(!isTouchingLeft()) {
            currentBlockCol--;
            repaint();
        }
    }
    
    public void moveBlockRight() {
        if(!isTouchingRight()) {
            currentBlockCol++;
            repaint();
        }
    }
    
    public void setPlaying(boolean isPlaying) {
        this.isPlaying = isPlaying;
    }
    
    // Return true if placed, false if not placed
    private boolean placeIfTouchingBottom() {
        if(isTouchingBottom()) {
            dropPiece();
            return true;
        }
        return false;
    }
    
    // Return true if we're touching a piece, false if not
    private boolean isTouchingBottom() {
        if(currentBlockRow + 2 == numRows - 1) {
            return true;
        }
        else {
            if(charGameBoard[currentBlockRow + 3][currentBlockCol] != '.') {
                return true;
            }
        }
        return false;
    }
    
    private boolean isTouchingLeft() {
        if(currentBlockCol == 0) return true;
        if(charGameBoard[currentBlockRow][currentBlockCol-1] != '.') return true;
        if(charGameBoard[currentBlockRow+1][currentBlockCol-1] != '.') return true;
        if(charGameBoard[currentBlockRow+2][currentBlockCol-1] != '.') return true;
        return false;
    }
    
    private boolean isTouchingRight() {
        if(currentBlockCol == numCols - 1) return true;
        if(charGameBoard[currentBlockRow][currentBlockCol+1] != '.') return true;
        if(charGameBoard[currentBlockRow+1][currentBlockCol+1] != '.') return true;
        if(charGameBoard[currentBlockRow+2][currentBlockCol+1] != '.') return true;
        return false;
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if(isPlaying){
            moveBlockDown();
        }
    }
    
    // Only painting the current piece, the super will paint the static pieces remaining
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        // Painting the static board via the BlockManager's game board
        for(int i = 0; i < numRows; ++i) {
            for(int j = 0; j < numCols; ++j) {
                drawABlock(g, i, j);
            }
        }
        
        // Passing in an int to highlight the column you have selected. Bonus game play feature.
        int rowsToHighlight = numRows - currentBlockRow;
        
        // A moving block knows how to paint itself, so let it
        dropBlock.paintBlocks(g, currentBlockCol*cellSize + 10 , 
                currentBlockRow*cellSize + 10, cellSize, rowsToHighlight);
        
    }
    
    // Draws a single block based on the internal game board
    public void drawABlock(Graphics g, int row, int col) {
        g.setColor(ColorMap.ColorMap.get(charGameBoard[row][col]));
        g.fillRoundRect(col*cellSize + 10, row*cellSize + 10, cellSize, cellSize, cellSize/4, cellSize/4);
        g.setColor(Color.WHITE);
        g.drawRoundRect(col*cellSize + 10, row*cellSize + 10, cellSize, cellSize, cellSize/4, cellSize/4);
    }
    
    // Updates the labels from the frame
    public void updateScoreLabels(JLabel scoreLabel, JLabel blockLabel) {
        scoreLabel.setText("Score: " + globalScore);
        blockLabel.setText("Blocks: " + globalBlocks);
    }

}
