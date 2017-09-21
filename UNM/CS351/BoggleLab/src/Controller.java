import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import java.io.*;
import java.net.URISyntaxException;
import java.util.LinkedHashSet;

public class Controller
{

    BufferedReader reader;
    LinkedHashSet<String> words;

    @FXML
    private Label lblWordValid;

    @FXML
    private TextField tfWord;

    @FXML
    private Canvas canvasBoard;

    private GraphicsContext gcCanvas;

    public Controller() {
    }

    @FXML
    private void initialize()
    {
        gcCanvas = canvasBoard.getGraphicsContext2D();
        words = new LinkedHashSet<String>();
        openFile();
    }

    private void openFile()
    {
        try
        {
            File file = new File(getClass().getResource("dictionary.txt").toURI());
            reader = new BufferedReader(new FileReader(file));
            String line;
            while((line = reader.readLine()) != null) { words.add(line); }
        }
        catch (IOException e) {}
        catch(URISyntaxException e) {}
    }

    @FXML
    private void checkWord()
    {
        boolean containsWord = false;
        String possibleWord = tfWord.getText();
        if(words.contains(possibleWord)) { containsWord = true; }
        lblWordValid.setText(possibleWord + " - in dict: " + containsWord);
    }

    //TODO: Add capability for it to do 4x4 or 5x5...
    @FXML
    private void generateBoard()
    {

    }


}
