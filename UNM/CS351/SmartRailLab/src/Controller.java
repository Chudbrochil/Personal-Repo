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
    ArrayList<Station> stationList;

    @FXML
    private void initialize()
    {
        gcDraw = canvasRail.getGraphicsContext2D();
        railConfig = new RailConfiguration(gcDraw);
        railConfigLoader = new RailConfigurationLoader(railConfig);
        stationList = new ArrayList<>();

        //rcl.loadNewConfiguration("SimpleOneTrack.txt");
        //rcl.loadNewConfiguration("SimpleTwoTrack.txt");
        //rcl.loadNewConfiguration("LightThreeTrack.txt");
        //rcl.loadNewConfiguration("AnnaOneTrack.txt");

        //launchNewConfiguration("AnnaOneTrack.txt");
        launchNewConfiguration("SimpleFourTrack.txt");
        makeTrain();

    }

    private void launchNewConfiguration(String configurationName)
    {
        railConfigLoader.loadNewConfiguration(configurationName, gcDraw);
        // Drawing the components
        int canvasHeight = 600;
        int canvasWidth = 800;
        railConfig.drawInitialComponents(canvasHeight, canvasWidth);
        //rc.drawTrain();

        // Assigning the train to station1....
        stationList = railConfig.getStationList();
    }

    private void makeTrain()
    {
        // Maybe make a list of trains?
        Train theTrain = new Train(gcDraw, 50, 50);

        // TODO: Add a method for a textbox in train, this will be the narrator. i.e. I arrived at station X, I'm moving to station X, I'm on track blahblah



        theTrain.setNeighbors(stationList.get(0), null);
        theTrain.requestRoute("Miami");
        theTrain.draw();

        // Other components are started in TrackLine
        theTrain.start();
    }

    @FXML
    private void btnTestMain()
    {
        TestMain.testSearchRoute();
    }

    public Controller() { }


}
