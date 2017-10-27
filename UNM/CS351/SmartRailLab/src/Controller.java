import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

public class Controller
{

    @FXML
    private Canvas canvasRail;



    @FXML
    private void initialize()
    {
        GraphicsContext gcDraw = canvasRail.getGraphicsContext2D();
        //gcDraw.setFill(Color.BLACK);
        //gcDraw.fillRect(50, 50, 100, 100); // Drawing a rectangle for effect

        // TODO: Running tests
        RailConfiguration rc = new RailConfiguration(gcDraw);


        RailConfigurationLoader rcl = new RailConfigurationLoader(rc);
        //rcl.loadNewConfiguration("SimpleOneTrack.txt");
        //rcl.loadNewConfiguration("SimpleTwoTrack.txt");
        rcl.loadNewConfiguration("LightThreeTrack.txt");

        // Drawing the components
        int canvasHeight = 600;
        int canvasWidth = 800;
        rc.drawInitialComponents(canvasHeight, canvasWidth);


    }

    public Controller() { }


}
