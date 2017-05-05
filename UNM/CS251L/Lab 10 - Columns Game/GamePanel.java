/**
 * Anthony Galczak - agalczak@unm.edu - WGalczak@gmail.com
 * CS 251 - Lab 10 - Columns Game
 * 
 * A wrapper panel for the GameBoard. Holds the overall
 * dimensions of the panel and serves as a parent to GameBoard.
 * 
 * GamePanel.java
 */

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

public class GamePanel extends JPanel {
    
    private int panelHeight;
    private int panelWidth;
    
    public GamePanel() {
        this(COLUMNS_CONSTANTS.BOARD_WIDTH, COLUMNS_CONSTANTS.BOARD_HEIGHT);
    }
    
    public GamePanel(int panelWidth, int panelHeight) {
        this.panelWidth = panelWidth;
        this.panelHeight = panelHeight;
        setBackground(Color.BLACK);
    }
    
    public Dimension getPreferredSize() { return new Dimension(panelWidth, panelHeight); }
    
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
    }
    
    
}
