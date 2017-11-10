import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import java.util.ArrayList;
import java.util.Optional;
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
    private int trainyardX = 100;
    private int trainyardY = 550;
    private Train currentTrain;

    @FXML
    private void initialize()
    {
        freshInitialize();
        userSelectConfig();
        reDraw();
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
        configs.add("LightThreeTrack.txt");
        configs.add("SimpleFourTrack.txt");
        configs.add("LightFourTrack.txt");
        configs.add("SwitchFourTrack.txt");
        configs.add("AnnaOneTrack.txt");

        ChoiceDialog dialog = new ChoiceDialog(configs.get(0), configs);
        dialog.setTitle("SmartRail!");
        dialog.setHeaderText("Choose your simulation");
        Optional<String> result = dialog.showAndWait();

        if(result.isPresent())
        {
            System.out.println(result.get());
            launchNewConfiguration(result.get());
        }
        else
        {
            launchNewConfiguration(configs.get(0));
        }

    }

    /**
     * freshInitialize()
     * Initializes the data structures in this class to null for initial use. Could also be used if we are making a
     * new simulation on the fly.
     */
    private void freshInitialize()
    {
        gcDraw = canvasRail.getGraphicsContext2D();
        railConfig = new RailConfiguration(gcDraw);
        railConfigLoader = new RailConfigurationLoader(railConfig);
        stationList = new ArrayList<>();
        activeTrains = new ArrayList<>();
        drawableList = new ArrayList<>();
    }

    /**
     * reDraw()
     * This effectively becomes a thread that is constantly redrawing the canvas.
     * Inspired by https://stackoverflow.com/questions/3541676/java-thread-every-x-seconds
     */
    private void reDraw()
    {
        ScheduledExecutorService exec = Executors.newSingleThreadScheduledExecutor();
        exec.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run()
            {
                gcDraw.clearRect(0,0,800,600);
                gcDraw.fillText("Trainyard", 10, 560);
                for(int i = 0; i < drawableList.size(); ++i)
                {
                    drawableList.get(i).draw();
                }
            }
        }, 0, 25, TimeUnit.MILLISECONDS);
    }

    /**
     * launchNewConfiguration()
     * This starts the program by loading a file with a track configuration in it.
     * This starts instantiating all the objects and does the initial draw
     * @param configurationName The String of the file that holds the configuration.
     */
    private void launchNewConfiguration(String configurationName)
    {
        // Loading the specified configuration of tracks, lights, switches, stations
        railConfigLoader.loadNewConfiguration(configurationName, gcDraw);

        // Drawing the components and getting back the list of components we need to continue to draw
        drawableList = railConfig.drawInitialComponents();

        // Attaching switchs to their switch neighbors
        railConfig.attachSwitches();

        // Getting the list of possible stations that we can put trains into
        stationList = railConfig.getStationList();
    }

    /**
     * makeTrain()
     * Method that is engaged by the user. Makes a train on screen in the train yard that is ready to be placed in
     * a station.
     */
    @FXML
    private void makeTrain()
    {
        tfOutput.setText("Please select a train and then select your station for it to start.");
        // TODO: weird circumstances can cause the trains to be overdrawn, fix this.
        //Train aTrain = new Train();
        //activeTrains.add(aTrain);
        Train aTrain = new Train(gcDraw, trainyardX + activeTrains.size()*75, trainyardY);
        activeTrains.add(aTrain); //TODO: Do I need activeTrains?
        drawableList.add(aTrain);

        aTrain.start();

        // TODO: Add a method for a textbox in train, this will be the narrator. i.e. I arrived at station X, I'm moving to station X, I'm on track blahblah
    }

    /**
     * canvasClicked()
     * @param me This is a click event that I can gather the left-click's, right-click's from, etc.
     */
    @FXML
    private void canvasClicked(MouseEvent me)
    {
        // LEFT CLICK
        if(me.getButton().name() == "PRIMARY")
        {
            attemptTrainSelect((int)me.getX(), (int)me.getY());
            attemptStationSelect((int)me.getX(), (int)me.getY());
        }
    }

    /**
     * attemptStationSelect()
     * This method attempts to select a station on the canvas via a click. If a station is selected then it will
     * call a follow-up method to decide a behavior.
     * @param x x-coord that was clicked
     * @param y y-coord that was clicked
     */
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

    /**
     * decideStationAction()
     * Does a certain action from the station based on what the status of the currentTrain is.
     * @param stationClicked Station that was gathered from the user's click
     */
    private void decideStationAction(Station stationClicked)
    {
        if(currentTrain != null)
        {
            // We want the train to go to the clicked station (i.e. request a route)
            if(currentTrain.hasAStation())
            {
                currentTrain.requestRoute(stationClicked.toString());
                tfOutput.setText("Request route: " + stationClicked.toString());

                // TODO: Weird situation where if a useer puts a train in a station, but doesn't give it a route right away
                // then it will be stuck there forever...
                currentTrain = null;
            }
            // We want to put the train in the station
            else
            {
                activeTrains.remove(currentTrain);
                currentTrain.setNeighbors(stationClicked, null);
                tfOutput.setText(currentTrain.toString() + " has been put into " +  stationClicked.toString() +
                    ". Select a destination.");
                currentTrain.setCoords(stationClicked.getCanvasX() + 10, stationClicked.getCanvasY() + 10);
            }
        }
        else
        {
            tfOutput.setText("You can't select a station without a train. Please make a train and select it.");
        }

    }


    // TODO: I could make this able to be drag and dropped onto the station instead...

    /**
     * attemptTrainSelect()
     * Attempts to select a station based on users click. This will set the currentTrain if clicking on a train.
     * @param x x-coord that was clicked
     * @param y y-coord that was clicked
     */
    private void attemptTrainSelect(int x, int y)
    {
        for(int i = 0; i < activeTrains.size(); ++i)
        {
            if(activeTrains.get(i).isInClickedArea(x,y))
            {
                currentTrain = activeTrains.get(i);
                tfOutput.setText("You selected " + activeTrains.get(i).toString() + ". Please select a station for it.");
            }
        }
    }

    public Controller() { }


}
