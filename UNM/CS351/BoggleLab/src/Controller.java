import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.StageStyle;
import javafx.util.Pair;

import java.util.*;

public class Controller
{

    @FXML
    private Label lblWordValid;

    @FXML
    private Label lblCurrentWord;

    @FXML
    private Label lblGoodWords;

    @FXML
    private Label lblBadWords;

    @FXML
    private Label lblScore;

    @FXML
    private Label lblTime;

    @FXML
    private Canvas canvasBoard;

    private GameManager gm;

    private GraphicsContext gcCanvas;

    public Controller() { }

    @FXML
    private void initialize()
    {

        gcCanvas = canvasBoard.getGraphicsContext2D();
        int gridSize = askGameType();
        boolean realDice = askLetterType();
        gm = new GameManager(gcCanvas, gridSize, realDice);
        gm.startTimer(lblTime);
    }

    // Showing a dialog to ask user what game size they want to play

    /**
     * askGameType()
     * Opens a dialog asking what version of Boggle the player would like to play.
     * @return An int representing the size of the grid. 4 for Boggle, 5 for Big Boggle, 6 for Super Big Boggle
     */
    private int askGameType()
    {
        ArrayList<String> gameTypes = new ArrayList<>();
        gameTypes.add("Boggle (4x4)");
        gameTypes.add("Big Boggle (5x5)");
        gameTypes.add("Super Big Boggle (6x6)");


        // TODO: Not sure how to create a dialog with 2 choicedialog's in it
//        Dialog<Pair<ChoiceDialog, ChoiceDialog>> parentDialog = new Dialog<>();
//        DialogPane


        Dialog<Pair<ChoiceDialog, ChoiceDialog>> myBigDialog = new Dialog<>();

        ChoiceDialog dialog = new ChoiceDialog(gameTypes.get(0), gameTypes);
        dialog.setTitle("Boggle!");
        dialog.setHeaderText("Pick your game type.");
        Optional<String> result = dialog.showAndWait();

        if(result.isPresent())
        {
            if(result.get() == gameTypes.get(2)) { return 6; }
            else if(result.get() == gameTypes.get(1)) { return 5; }
        }

        return 4;
    }

    /**
     * askLetterType()
     * Opens a dialog asking if the user wants to play with "random dice" or "real Boggle dice"
     * @return A boolean, true if we're using real dice, false if using random dice.
     */
    private boolean askLetterType()
    {
        ArrayList<String> letterTypes = new ArrayList<>();
        letterTypes.add("Spec letters (totally random)");
        letterTypes.add("Real world letters (pre-configured dice)");

        ChoiceDialog dialog = new ChoiceDialog(letterTypes.get(0), letterTypes);
        dialog.setTitle("Boggle!");
        dialog.setHeaderText("Pick your letter type.");
        Optional<String> result = dialog.showAndWait();

        if(result.isPresent())
        {
            if(result.get() == letterTypes.get(1)) { return true; }
        }
        return false;

    }

    /**
     * canvasClicked()
     * When a user clicks on the canvas, this method will parse whether it was a left click or a right click.
     * If it's a left click then we attempt to select the piece (if it's a valid selection) and continue building
     * a word.
     *
     * If it's a right click then we check the word that was built and see if it's valid. From there we update
     * the UI showing the good or bad word the user checked.
     * @param me This is the event that holds the information about where the user clicked.
     */
    @FXML
    private void canvasClicked(MouseEvent me)
    {
        // LEFT CLICK
        if(me.getButton().name() == "PRIMARY")
        {
            gm.attemptSelectPiece((int)me.getX(), (int)me.getY(), lblCurrentWord);
        }
        // RIGHT CLICK
        else if(me.getButton().name() == "SECONDARY")
        {
            gm.checkWord(lblWordValid, lblGoodWords, lblBadWords, lblScore);
        }
    }

}
