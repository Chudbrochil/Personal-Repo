import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.util.ArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Controller
{

    @FXML
    private Canvas canvasRail;

    @FXML
    private TextField tfOutput;

    private GraphicsContext gcDraw;

    private RailConfiguration railConfig;
    private RailConfigurationLoader railConfigLoader;
    private ArrayList<Station> stationList;
    private ArrayList<IDrawable> drawableList;
    private ArrayList<Train> activeTrains;
    private int initialTrainX = 400;
    private int initialTrainY = 550;
    private Train currentTrain;

    @FXML
    private void initialize()
    {
        gcDraw = canvasRail.getGraphicsContext2D();
        railConfig = new RailConfiguration(gcDraw);
        railConfigLoader = new RailConfigurationLoader(railConfig);
        stationList = new ArrayList<>();
        activeTrains = new ArrayList<>();
        drawableList = new ArrayList<>();



        // TODO: Remove this, just a cool placeholder to show where trains are showing up
        gcDraw.fillText("Train purgatory", 50, 550);

        // TODO: Gather this using an initial dropdown or a button that shows a drop-down
        launchNewConfiguration("SimpleFourTrack.txt");

        reDraw();
    }

    // This effectively becomes a thread that is constantly redrawing the canvas
    private void reDraw()
    {
        ScheduledExecutorService exec = Executors.newSingleThreadScheduledExecutor();
        exec.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run()
            {
                for(int i = 0; i < drawableList.size(); ++i)
                {
                    drawableList.get(i).draw();
                }
            }
        }, 0, 100, TimeUnit.MILLISECONDS);
    }

    private void launchNewConfiguration(String configurationName)
    {
        // Loading the specified configuration of tracks, lights, switches, stations
        railConfigLoader.loadNewConfiguration(configurationName, gcDraw);

        // Drawing the components
        drawableList = railConfig.drawInitialComponents();

        // Getting the list of possible stations that we can put trains into
        stationList = railConfig.getStationList();

    }

    @FXML
    private void makeTrain()
    {
        tfOutput.setText("Please select a station for your train to start.");
        Train aTrain = new Train(gcDraw, initialTrainX, initialTrainY);
        drawableList.add(aTrain);
        activeTrains.add(aTrain);
        currentTrain = aTrain;
        aTrain.start();

        // TODO: Add a method for a textbox in train, this will be the narrator. i.e. I arrived at station X, I'm moving to station X, I'm on track blahblah
    }

    @FXML
    private void canvasClicked(MouseEvent me)
    {
        // LEFT CLICK
        if(me.getButton().name() == "PRIMARY")
        {
            attemptStationSelect((int)me.getX(), (int)me.getY());
        }
    }

    private void attemptStationSelect(int x, int y)
    {
        for(int i = 0; i < stationList.size(); ++i)
        {
            Station currentStation = stationList.get(i);
            if(currentStation.isInClickedArea(x,y))
            {
                decideStationAction(currentStation);
            }
        }
    }



    private void decideStationAction(Station stationClicked)
    {
        // We want the train to go to the clicked station (i.e. request a route)
        if(currentTrain.hasAStation())
        {
            currentTrain.requestRoute(stationClicked.toString());
            tfOutput.setText("Request route: " + stationClicked.toString());
        }
        // We want to put the train in the station
        else
        {
            currentTrain.setNeighbors(stationClicked, null);
            tfOutput.setText("Set station: " + stationClicked.toString());
            currentTrain.setCoords(stationClicked.getCanvasX() + 10, stationClicked.getCanvasY());
        }
    }

    public Controller() { }


}
