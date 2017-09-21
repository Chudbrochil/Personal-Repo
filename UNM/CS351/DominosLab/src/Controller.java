// Anthony Galczak - agalczak@unm.edu - WGalczak@gmail.com
// Dominos Game - Lab 1 CS 351

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

public class Controller
{
    @FXML
    private GridPane gridPlayer;

    @FXML
    private Label lblComputerDominos, lblBoneyard;

    @FXML
    private ImageView imgSelectedDomino;

    @FXML
    private Button btnDraw;

    private GameManager gm;

    @FXML
    private Canvas canvasBoard;

    private GraphicsContext gcCanvas;

    @FXML
    private void initialize()
    {
        gcCanvas = canvasBoard.getGraphicsContext2D();
        gm = new GameManager(imgSelectedDomino, btnDraw, false);
        gm.updateComponents(lblBoneyard, lblComputerDominos, gridPlayer, gcCanvas);
    }

    /**
     * btnPlaceLeft()
     * Handler for btnPlaceLeft. Attempts to place your selected domino on the left side of the board.
     */
    @FXML
    private void btnPlaceLeft()
    {
        gm.placePiece(true);
        gm.updateComponents(lblBoneyard, lblComputerDominos, gridPlayer, gcCanvas);
    }

    /**
     * btnPlaceRight()
     * Handler for btnPlaceRight. Attempts to place your selected domino on the right side of the board.
     */
    @FXML
    private void btnPlaceRight()
    {
        gm.placePiece(false);
        gm.updateComponents(lblBoneyard, lblComputerDominos, gridPlayer, gcCanvas);
    }

    /**
     * btnDraw()
     * Handler for btnDraw. Draws a piece into your hand if you have no plays available.
     */
    @FXML
    private void btnDraw()
    {
        gm.drawFromBoneyard();
        gm.updateComponents(lblBoneyard, lblComputerDominos, gridPlayer, gcCanvas);
    }

    /**
     * btnRotate()
     * Handler for btnRotate. Rotates your currently selected domino.
     */
    @FXML
    private void btnRotate()
    {
        gm.rotatePiece();
        gm.updateComponents(lblBoneyard, lblComputerDominos, gridPlayer, gcCanvas);
    }

    public Controller() { }

}
