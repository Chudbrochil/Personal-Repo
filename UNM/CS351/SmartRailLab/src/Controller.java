import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import java.util.ArrayList;
import java.util.Optional;

public class Controller
{

    @FXML
    private Canvas canvasRail;

    @FXML
    private Label lblUserAlert;

    @FXML
    private Label lblSimStatus;

    private GraphicsContext gcDraw;

    private Conductor conductor;

    public Controller()
    {
    }

    /**
     * initialize()
     * This is the initialization of the UI.
     */
    @FXML
    private void initialize()
    {
        gcDraw = canvasRail.getGraphicsContext2D();
        Notifications notifications = new Notifications(lblUserAlert, lblSimStatus);
        conductor = new Conductor(gcDraw);
        userSelectConfig();
    }

    /**
     * userSelectConfig()
     * This gives the user a drop-down for selecting which simuation the user wants to see.
     */
    private void userSelectConfig()
    {
        ArrayList<String> configs = new ArrayList<>();

        // Apparently reading a directory's filenames from a jar is non-trivial. I found something, but this
        // problem cost me a few hours already... Hard coding the configuration file names
        // https://stackoverflow.com/questions/28985379/java-how-to-read-folder-and-list-files-in-that-folder-in-jar-environment-instead
        configs.add("1-Simple.txt");
        configs.add("2-Simple.txt");
        configs.add("2-Switch.txt");
        configs.add("3-Lights.txt");
        configs.add("4-Lights.txt");
        configs.add("4-Simple.txt");
        configs.add("4-Switches.txt");
        configs.add("5-MoreLeft.txt");
        configs.add("5-MoreLeft2.txt");
        configs.add("6-Switches.txt");
        configs.add("8-BothSwitches.txt");
        configs.add("8-MoreRight.txt");
        configs.add("8-MoreLeft.txt");
        configs.add("BadInputSample.txt");

        ChoiceDialog dialog = new ChoiceDialog(configs.get(0), configs);
        dialog.setTitle("SmartRail!");
        dialog.setHeaderText("Choose your simulation");
        Optional<String> result = dialog.showAndWait();

        if (result.isPresent())
        {
            conductor.launchNewConfiguration(result.get());
        }
        else
        {
            conductor.launchNewConfiguration(configs.get(0));
        }

    }

    /**
     * canvasClicked()
     *
     * @param me This is a click event that I can gather the left-click's, right-click's from, etc.
     */
    @FXML
    private void canvasClicked(MouseEvent me)
    {
        // LEFT CLICK
        if (me.getButton().name() == "PRIMARY")
        {
            // If clicking the canvas doesn't give you a train, look for a station.
            if(!conductor.attemptTrainSelect((int) me.getX(), (int) me.getY()))
            {
                conductor.attemptStationSelect((int) me.getX(), (int) me.getY());
            }


        }
    }

    /**
     * makeTrain()
     *
     * Makes a new train in the conductor. This will make a train that a user can place in a station.
     */
    @FXML
    private void makeTrain()
    {
        conductor.makeTrain();
    }

}
