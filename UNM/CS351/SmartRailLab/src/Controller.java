import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;

import java.util.ArrayList;

public class Controller
{

    @FXML
    private Canvas canvasRail;

    GraphicsContext gcDraw;

    RailConfiguration railConfig;
    RailConfigurationLoader railConfigLoader;

    @FXML
    private void initialize()
    {
        gcDraw = canvasRail.getGraphicsContext2D();
        railConfig = new RailConfiguration(gcDraw, 50, 10);
        railConfigLoader = new RailConfigurationLoader(railConfig);

        //rcl.loadNewConfiguration("SimpleOneTrack.txt");
        //rcl.loadNewConfiguration("SimpleTwoTrack.txt");
        //rcl.loadNewConfiguration("LightThreeTrack.txt");
        //rcl.loadNewConfiguration("AnnaOneTrack.txt");

        launchNewConfiguration("AnnaOneTrack.txt");




    }

    private void launchNewConfiguration(String configurationName)
    {
        railConfigLoader.loadNewConfiguration(configurationName);
        // Drawing the components
        int canvasHeight = 600;
        int canvasWidth = 800;
        railConfig.drawInitialComponents(canvasHeight, canvasWidth);
        //rc.drawTrain();

        // Assigning the train to station1....
        ArrayList<Station> stationList = railConfig.getStationList();

        Train theTrain = new Train();
        theTrain.setNeighbors(stationList.get(0), null);
        theTrain.requestRoute("Station2");
        theTrain.draw(50, 50, gcDraw);

        // TODO: Not sure why the message isn't being sent to track1....
        System.out.println(stationList.get(0).getNeighbor().toString());

    }

    // Put the testMain in here....
    private void testMessage()
    {

    }

    @FXML
    private void btnTestMain()
    {
        TestMain.testSearchRoute();
    }

    public Controller() { }


}
