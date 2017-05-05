/**
 * Anthony Galczak - agalczak@unm.edu - WGalczak@gmail.com
 * CS 251 - Lab 10 - Columns Game
 * 
 * I like to think of this class as the "Master GUI". This class holds all
 * of the panels that will be used as well as the introductory dialog for
 * polling the user on what game type they would like.
 * 
 * The timer, logic for the pause button and its listener, and all of the
 * menu options and their dialogs live here.
 * 
 */

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class ColumnsFrame extends JFrame {
    
    private JButton pauseButton;
    
    private JLabel scoreLabel = new JLabel();
    private JLabel blocksLabel = new JLabel();
    
    private boolean isPaused = true;
    private Timer timer;
    private int timerIncrement = 500;
    
    private GameBoard gb;
    private static ColumnsFrame cf;

    public static void main(String[] args) {
        cf = new ColumnsFrame();
        cf.setTitle("Columns!");
        cf.setSize(COLUMNS_CONSTANTS.WINDOW_WIDTH, COLUMNS_CONSTANTS.WINDOW_HEIGHT);
        cf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        cf.setLocationRelativeTo(null);
        cf.setVisible(true);
        cf.setResizable(false);
    }
    
    public ColumnsFrame() {
        
        // Creating a board panel and game board based upon what they select for a game type
        int gameType = showGameTypeDialog(); 
        JPanel boardPanel = new JPanel();
        JPanel gamePanel = new GamePanel(COLUMNS_CONSTANTS.BOARD_WIDTH, COLUMNS_CONSTANTS.BOARD_HEIGHT);
        
        if(gameType == 0) {
            gb = new GameBoard(COLUMNS_CONSTANTS.SMALL_ROWS, COLUMNS_CONSTANTS.SMALL_COLS,
                    COLUMNS_CONSTANTS.SMALL_CELL_SIZE, COLUMNS_CONSTANTS.SMALL_BLOCK_TYPES, this);
        }
        else if(gameType == 2) {
            gb = new GameBoard(COLUMNS_CONSTANTS.LARGE_ROWS, COLUMNS_CONSTANTS.LARGE_COLS, 
                    COLUMNS_CONSTANTS.LARGE_CELL_SIZE, COLUMNS_CONSTANTS.LARGE_BLOCK_TYPES, this);
        }
        else {
            gb = new GameBoard(COLUMNS_CONSTANTS.DEFAULT_ROWS, COLUMNS_CONSTANTS.DEFAULT_COLS,
                    COLUMNS_CONSTANTS.DEFAULT_CELL_SIZE, COLUMNS_CONSTANTS.DEFAULT_BLOCK_TYPES, this);
        }

        boardPanel.add(gamePanel);
        
        timer = new Timer(timerIncrement, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                gb.moveBlockDown();
                gb.updateScoreLabels(scoreLabel, blocksLabel);
            }
        });
        
        gamePanel.add(gb);
        
        // Pause button listener
        pauseButton = new JButton("Start");
        pauseButton.addActionListener(e -> pause());
        
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(pauseButton);
        
        // PREVIEW PANEL - OPTIONAL
//        JPanel previewPanel = new JPanel();
//        previewPanel.setBorder(BorderFactory.createTitledBorder("Next Piece"));
//        previewPanel.setPreferredSize(new Dimension(150,300));
        
        // Score Panel
        JPanel scorePanel = new JPanel();
        scorePanel.setLayout(new BoxLayout(scorePanel, BoxLayout.PAGE_AXIS));
        scorePanel.setBorder(BorderFactory.createTitledBorder("Score"));
        scorePanel.add(scoreLabel);
        scorePanel.add(blocksLabel);
        scorePanel.setPreferredSize(new Dimension(100,75));
        
        // Help Menu Bar
        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);
        JMenu helpMenu = new JMenu("Help");
        JMenuItem scoringItem = new JMenuItem("Scoring");
        JMenuItem rulesItem = new JMenuItem("Rules of Columns");
        JMenuItem controlsItem = new JMenuItem("Game Controls");
        helpMenu.add(scoringItem);
        helpMenu.add(rulesItem);
        helpMenu.add(controlsItem);
        menuBar.add(helpMenu);
        
        // "Lambda" action listeners for menu items
        scoringItem.addActionListener(e -> JOptionPane.showMessageDialog(null, COLUMNS_CONSTANTS.SCORING_STRING, "Scoring", 
                JOptionPane.INFORMATION_MESSAGE));
        rulesItem.addActionListener(e -> JOptionPane.showMessageDialog(null, COLUMNS_CONSTANTS.RULES_STRING, "Rules",
                        JOptionPane.INFORMATION_MESSAGE));
        controlsItem.addActionListener(e -> JOptionPane.showMessageDialog(null, COLUMNS_CONSTANTS.CONTROLS_STRING, "Controls", 
                        JOptionPane.INFORMATION_MESSAGE));
        
        // Right Panel, holds score and preview panel
        JPanel rightPanel = new JPanel(new BorderLayout());
        //rightPanel.add(previewPanel, BorderLayout.PAGE_START);
        rightPanel.add(scorePanel, BorderLayout.PAGE_END);
        
        // Placing all the panels onto the JFrame
        getContentPane().add(boardPanel, BorderLayout.CENTER);
        getContentPane().add(buttonPanel, BorderLayout.PAGE_END);
        getContentPane().add(rightPanel, BorderLayout.LINE_END);
        
        initialize();
    }
    
    public void initialize() {
        scoreLabel.setText("Score: 0");
        blocksLabel.setText("Blocks: 0");
    }
    
    // Resetting the current game
    protected void gameOver() {
        pause();
        int response = JOptionPane.showConfirmDialog(null, "Play again?", "Game Over!",
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if(response == JOptionPane.NO_OPTION) System.exit(0);
        else initialize();
    }
    
    public void pause() {
        isPaused = !isPaused;
        pauseButton.setText(isPaused ? "Start" : "Pause");
        gb.setPlaying(!isPaused);
        
        if(isPaused) {
            timer.stop();
        }
        else{
            timer.start();
            gb.requestFocusInWindow();
        }
    }
    
    public int showGameTypeDialog() {
        Object[] options = { 
                "<html>" + "Small Game<br>Rows: " + COLUMNS_CONSTANTS.SMALL_ROWS + "<br>Columns: "
                + COLUMNS_CONSTANTS.SMALL_COLS + "<br>Block Types: " + COLUMNS_CONSTANTS.SMALL_BLOCK_TYPES
                + "</html>", 
                
                "<html>" + "Medium Game<br>Rows: " + COLUMNS_CONSTANTS.DEFAULT_ROWS + "<br>Columns: "
                + COLUMNS_CONSTANTS.DEFAULT_COLS + "<br>Block Types: " + COLUMNS_CONSTANTS.DEFAULT_BLOCK_TYPES
                + "</html>", 
                
                "<html>" + "Large Game<br>Rows: " + COLUMNS_CONSTANTS.LARGE_ROWS + "<br>Columns: "
                + COLUMNS_CONSTANTS.LARGE_COLS + "<br>Block Types: " + COLUMNS_CONSTANTS.LARGE_BLOCK_TYPES
                + "</html>"};
   
        return JOptionPane.showOptionDialog(null, "Select a Game Type", "Columns!", 
                JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, null);
    }

}
