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
        configs.add("SimpleOneTrack.txt");
        configs.add("SimpleTwoTrack.txt");
        configs.add("SwitchTwoTrack.txt");
        configs.add("LightThreeTrack.txt");
        configs.add("SimpleFourTrack.txt");
        configs.add("LightFourTrack.txt");
        configs.add("SwitchFourTrack.txt");
        configs.add("FancyFiveTrack.txt");
        configs.add("FancyFiveTrack2.txt");
        configs.add("SwitchSixTrack.txt");

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
            conductor.attemptTrainSelect((int) me.getX(), (int) me.getY());
            conductor.attemptStationSelect((int) me.getX(), (int) me.getY());
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
