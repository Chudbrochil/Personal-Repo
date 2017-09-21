// Anthony Galczak - agalczak@unm.edu - WGalczak@gmail.com
// Dominos Game - Lab 1 CS 351

import javafx.application.Platform;
import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.event.EventHandler;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import java.io.File;
import java.util.ArrayList;

public class GameManager
{
    //region Initialization
    private Boneyard boneyard;
    private Player p1;
    private Player p2;
    private Board board;
    private Player currentPlayer;
    private ArrayList<ImageView> imageStack;
    private int currentSelectedDomino;
    private ImageView imgSelectedDomino;
    private Button btnDraw;
    private Player previousPlayer;
    private boolean asciiOutput;

    public GameManager(ImageView imgSelectedDomino, Button btnDraw, boolean asciiOutput)
    {
        int numOfStartingDoms = 7;
        this.imgSelectedDomino = imgSelectedDomino;
        this.btnDraw = btnDraw;
        this.asciiOutput = asciiOutput;
        boneyard = new Boneyard();
        board = new Board();
        p1 = new Player();
        p2 = new Player(true);

        // Validation for making sure that the boneyard has enough dominos
        if(boneyard.getSize() >= numOfStartingDoms*2)
        {
            p1.initializeHand(boneyard, numOfStartingDoms);
            p2.initializeHand(boneyard, numOfStartingDoms);
        }

        currentPlayer = p1;
        previousPlayer = new Player();
        imageStack = new ArrayList<ImageView>();

        // Initialization for selected domino imageview
        imgSelectedDomino.setImage(new Image(p1.getDomino(0).getTileImage(), 150, 75, true, true));
        imgSelectedDomino.setRotate(270);

        // Draw button starts disabled as you always have a first move available
        btnDraw.setDisable(true);
    }
    //endregion

    //region UI Methods
    /**
     * placePiece()
     * This is the method for adding a piece to the board. The main logic will check
     * the selected domino and whether it matches on the left or right and will place your
     * domino according to where it matches and where you specified to place it.
     * @param isLeft Boolean specifying whether you are placing a domino on left or right
     * @return True if placing a piece was successful. False if the piece placement failed.
     */
    public boolean placePiece(boolean isLeft)
    {
        Domino dominoToCheck = currentPlayer.getDomino(currentSelectedDomino);
        if((isLeft == true && board.placePiece(dominoToCheck, true) == true) ||
                (isLeft != true && board.placePiece(dominoToCheck, false) == true))
        {
            currentPlayer.removeFromHand(currentSelectedDomino);
            switchTurn();
            return true;
        }
        else if(currentPlayer.equals(p1)) { playBuzzer(); }

        return false;
    }

    /**
     * rotatePiece()
     * Rotates the currently selected domino in the player's hand.
     */
    public void rotatePiece()
    {
        currentPlayer.getDomino(currentSelectedDomino).rotateTile();
        imgSelectedDomino.setRotate(imgSelectedDomino.getRotate() + 180);
    }

    /**
     * drawFromBoneyard()
     * Draws from the boneyard. This method calls updateDrawButton which will be
     * available when the player has no plays and the boneyard size is greater than 0.
     */
    public void drawFromBoneyard()
    {
        currentPlayer.addToHand(boneyard.drawPiece());

        // If the player draws a piece that plays, then grey out the draw button
        updateDrawButton();
    }

    /**
     * updateComponents()
     * This method updates the GUI components that are siphoned down from
     * the controller.
     * @param lblBoneyard Label for displaying how many boneyard dominos are left.
     * @param lblComputerDominos Label for displaying how many dominos are left for the computer player.
     * @param gridPlayer UI component holding all of the player's domino's. The dominos are held by ImageView's.
     * @param gcCanvas Graphic component of the board's canvas
     */
    public void updateComponents(Label lblBoneyard, Label lblComputerDominos, GridPane gridPlayer, GraphicsContext gcCanvas)
    {
        // ASCII output will only be printed if it's asked for
        if(asciiOutput == true) { printAscii(); }

        // Setting the labels for computer's dominos remaining and boneyard dominos remaining
        lblComputerDominos.setText(p2.getName() + " Dominos: " + p2.getSize());
        lblBoneyard.setText("Boneyard: " + boneyard.getSize());

        // Updating the selected domino image view
        updatePreviewImage();

        // Updating player hand on the UI
        updatePlayerGrid(gridPlayer);

        // Updating board
        updateBoardGrid(gcCanvas);
    }
    //endregion

    //region Private helper/game-flow methods
    /**
     * switchTurn()
     * This method has a lot of game flow in it. It first tries to find out if the current player
     * that just ended their turn has won. If they've won then we want to end the game and display
     * a winner dialog.
     * If no winner has been found, we switch then turn. After switching the turn we check if the new
     * player is a computer. If they are then begin making a computer move.
     */
    private void switchTurn()
    {
        currentSelectedDomino = 0;
        boolean winnerFound = false; // Prevents computer move from executing if someone has already won

        // If the player has run out of dominos, then they win!
        if(currentPlayer.getSize() == 0)
        {
            declareWinner(currentPlayer);
            winnerFound = true;
        }

        // If the player has no moves and cannot draw, then they lose!
        if(returnPlayableIndex() == -1 && boneyard.getSize() == 0)
        {
            declareWinner(previousPlayer);
            winnerFound = true;
        }

        // Switching turns to next player if the current player hasn't won.
        if(currentPlayer.equals(p1)) { currentPlayer = p2; }
        else { currentPlayer = p1; }

        updateDrawButton();

        if(currentPlayer.getIsComputer() && winnerFound == false)
        {
            computerMove();
        }

        // Saving the previous player in case the next player loses I will know who to declare the winner
        previousPlayer = currentPlayer;
    }

    /**
     * returnPlayableIndex()
     * Finds out if the current player has a play. If there is a play, the method returns the element of the first
     * play. If no plays are found in the entire hand, then the method returns -1.
     * @return Index of playable domino. If no playable domino is found, returns -1.
     */
    private int returnPlayableIndex()
    {
        return currentPlayer.getPlayableIndex(board.getDomino(0), board.getDomino(board.getSize() - 1));
    }

    /**
     * declareWinner()
     * If a winner is found in the game logic then show an alert declaring the winner and exit the game.
     * @param winningPlayer The player that has won.
     */
    private void declareWinner(Player winningPlayer)
    {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Game Over");
        alert.setHeaderText(null);
        alert.setContentText(winningPlayer.getName() + " has won!");
        alert.showAndWait();
        Platform.exit();
    }

    /**
     * playBuzzer()
     * Plays a sound when player attempts to make an invalid move.
     */
    private void playBuzzer()
    {
        File buzzerFile = new File("src/Dominos/buzzer.wav");
        if(buzzerFile.isFile())
        {
            Media mediaBuzzer = new Media(buzzerFile.toURI().toString());
            MediaPlayer player = new MediaPlayer(mediaBuzzer);
            player.play();
        }

    }

    /**
     * computerMove()
     * Executes a computer move. This is a very simple AI. It will place the piece found from returnPlayableIndex()
     * If no playable domino was found, the computer will draw a piece and attempt again.
     */
    private void computerMove()
    {
        // Getting the element from the computer player that is placeable
        int indexToPlace = returnPlayableIndex();

        // If the index isn't -1, then we have a match
        if(indexToPlace != -1)
        {
            currentSelectedDomino = indexToPlace;
            if(placePiece(true) == false && placePiece(false) == false)
            {
                // If the piece can't be placed normally, then rotate it and try again
                currentPlayer.getDomino(indexToPlace).rotateTile();

                // If it can't be placed on the left side, then place it on the right
                if(placePiece(true) == false) { placePiece(false); }
            }
        }
        else
        {
            drawFromBoneyard();
            computerMove();
        }
    }

    /**
     * updatePreviewImage()
     * This will update the ImageView that holds the currently selected domino.
     */
    private void updatePreviewImage()
    {
        // If the player's hand has been exhausted then this could throw an out of bounds exception
        if(p1.getSize() > 0)
        {
            imgSelectedDomino.setImage(new Image(p1.getDomino(currentSelectedDomino).getTileImage()));
            if(p1.getDomino(currentSelectedDomino).getIsRotated() == true) { imgSelectedDomino.setRotate(90); }
            else { imgSelectedDomino.setRotate(270); }
        }
    }

    /**
     * updateDrawButton()
     * Updates the draw button. Will be enabled if player has no plays and boneyard isn't 0 size.
     */
    private void updateDrawButton()
    {
        // If the player has no moves and the boneyard has dominos; light up the draw button
        if(returnPlayableIndex() == -1 && boneyard.getSize() > 0) { btnDraw.setDisable(false); }
        else { btnDraw.setDisable(true); }
    }

    /**
     * updatePlayerGrid()
     * Updates the dominos on the player's GridPane.
     * @param gridPlayer GridPane holding player's dominos.
     */
    private void updatePlayerGrid(GridPane gridPlayer)
    {
        gridPlayer.getChildren().clear();
        imageStack.clear();
        for(int i = 0; i < p1.getSize(); ++i)
        {
            ImageView currentImageView = new ImageView();
            Image currentDominoImage = new Image(p1.getDomino(i).getTileImage(), 200, 100, true, true);
            currentImageView.setImage(currentDominoImage);
            if(p1.getDomino(i).getIsRotated() == true) { currentImageView.setRotate(90); }
            else { currentImageView.setRotate(270); }

            currentImageView.setOnMouseClicked(new MouseListener());
            imageStack.add(currentImageView);

            // Using integer division for row index
            gridPlayer.add(imageStack.get(i), i % 7, i/7);
        }
    }

    /**
     * Updates the dominos on the board's canvas.
     * @param gcCanvas Updates the domino's on the board's canvas
     */
    private void updateBoardGrid(GraphicsContext gcCanvas)
    {
        if(board.getSize() > 0)
        {
            for(int i = 0; i < board.getSize(); ++i)
            {
                Image currentDominoImage = new Image(board.getDomino(i).getTileImage(), 150, 75, true, true);

                // This is a bit of a hack to get rotation, but the alternatives are non-trivial transformations with BufferedImage
                ImageView ivHolder = new ImageView(currentDominoImage);
                if(board.getDomino(i).getIsRotated() == true) { ivHolder.setRotate(90); }
                else { ivHolder.setRotate(270); }
                Image possiblyRotatedImage = ivHolder.snapshot(new SnapshotParameters(), null);

                // Staggering the images based on where they are in the list, evens on top, odds on bottom
                if(i % 2 == 0) { gcCanvas.drawImage(possiblyRotatedImage, 37*i, 0); }
                else { gcCanvas.drawImage(possiblyRotatedImage, 37*i, 37); }
            }

        }

    }
    //endregion

    /**
     * MouseListener inner class
     * This class is a listener for changing the currently selected domino.
     * Changing the currently selected domino will update the domino preview ImageView
     * and the internal current domino.
     */
    private class MouseListener implements EventHandler<MouseEvent>
    {
        @Override
        public void handle(MouseEvent event)
        {
            for(int i = 0; i < imageStack.size(); ++i)
            {
                // The source is the clicked component. In this case it should be an imageView
                if(event.getSource().equals(imageStack.get(i)))
                {
                    currentSelectedDomino = i;
                    updatePreviewImage();
                    break;
                }
            }
        }
    }

    /**
     * printAscii()
     * Prints the current state of the game.
     */
    private void printAscii()
    {
        System.out.println("Player's turn:");
        System.out.println(currentPlayer.getName());
        p1.printHand();
        boneyard.printBoneyard();
        board.printBoard();
        p2.printHand();
        System.out.println("----------------------------------------------------------------");
    }

}
