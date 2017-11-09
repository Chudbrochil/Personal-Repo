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
    private int initialTrainX = 100;
    private int initialTrainY = 550;
    private Train currentTrain;

    @FXML
    private void initialize()
    {
        freshInitialize();
        userSelectConfig();
        reDraw();
    }

    private void userSelectConfig()
    {
        ArrayList<String> configs = new ArrayList<>();
        configs.add("SimpleOneTrack.txt");
        configs.add("SimpleTwoTrack.txt");
        configs.add("LightThreeTrack.txt");
        configs.add("SimpleFourTrack.txt");
        configs.add("LightFourTrack.txt");
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

    private void freshInitialize()
    {
        gcDraw = canvasRail.getGraphicsContext2D();
        railConfig = new RailConfiguration(gcDraw);
        railConfigLoader = new RailConfigurationLoader(railConfig);
        stationList = new ArrayList<>();
        activeTrains = new ArrayList<>();
        drawableList = new ArrayList<>();
        gcDraw.fillText("Trainyard", 10, 560);
    }

    // This effectively becomes a thread that is constantly redrawing the canvas
    // https://stackoverflow.com/questions/3541676/java-thread-every-x-seconds
    private void reDraw()
    {
        ScheduledExecutorService exec = Executors.newSingleThreadScheduledExecutor();
        exec.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run()
            {
                gcDraw.clearRect(0,0,800,600);
                for(int i = 0; i < drawableList.size(); ++i)
                {
                    drawableList.get(i).draw();

                    // The combination of clearing the lower portion of the board and then re-drawing
                    // active trains allows the active trains to be cleared when moved.
                    //gcDraw.clearRect(initialTrainX,initialTrainY,700,50);
                    drawActiveTrains();
                }
            }
        }, 0, 25, TimeUnit.MILLISECONDS);
    }

    // TODO: weird circumstances can cause the trains to be overdrawn, fix this.
    private void drawActiveTrains()
    {
        for(int i = 0; i < activeTrains.size(); ++i)
        {
            activeTrains.get(i).draw();
        }
    }

    private void launchNewConfiguration(String configurationName)
    {
        // Loading the specified configuration of tracks, lights, switches, stations
        railConfigLoader.loadNewConfiguration(configurationName, gcDraw);

        // Drawing the components and getting back the list of components we need to continue to draw
        drawableList = railConfig.drawInitialComponents();

        // Getting the list of possible stations that we can put trains into
        stationList = railConfig.getStationList();

    }

    @FXML
    private void makeTrain()
    {
        tfOutput.setText("Please select a train and then select your station for it to start.");
        Train aTrain = new Train(gcDraw, initialTrainX + activeTrains.size()*75, initialTrainY);
        activeTrains.add(aTrain);
        drawableList.add(aTrain);
        //aTrain.setCoords(activeTrains.indexOf(aTrain)*75, initialTrainY);

        aTrain.start();

        // TODO: Add a method for a textbox in train, this will be the narrator. i.e. I arrived at station X, I'm moving to station X, I'm on track blahblah
    }

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
                currentTrain.setCoords(stationClicked.getCanvasX() + 10, stationClicked.getCanvasY());
            }
        }
        else
        {
            tfOutput.setText("You can't select a station without a train. Please make a train and select it.");
        }

    }


    // TODO: I could make this able to be drag and dropped onto the station instead...
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
