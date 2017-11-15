import javafx.scene.canvas.GraphicsContext;
import java.util.ArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Conductor
{
    private RailConfiguration railConfig;
    private RailConfigurationLoader railConfigLoader;
    private ArrayList<Station> stationList;
    private ArrayList<IDrawable> drawableList;
    private ArrayList<Train> activeTrains;
    private int trainyardX = 100;
    private int trainyardY = 820;
    private Train currentTrain;
    private static int MAX_TRAINS;
    private GraphicsContext gcDraw;

    /**
     * Conductor()
     * Conductor's constructor
     * @param gcDraw Graphics context used to draw on canvas
     */
    public Conductor(GraphicsContext gcDraw)
    {
        this.gcDraw = gcDraw;
        freshInitialize();
    }

    /**
     * attemptStationSelect()
     * This method attempts to select a station on the canvas via a click. If a station is selected then it will
     * call a follow-up method to decide a behavior.
     *
     * @param x x-coord that was clicked
     * @param y y-coord that was clicked
     */
    public void attemptStationSelect(int x, int y)
    {
        for (int i = 0; i < stationList.size(); ++i)
        {
            Station currentStation = stationList.get(i);
            if (currentStation.isInClickedArea(x, y))
            {
                decideStationAction(currentStation);
            }
        }
    }

    /**
     * decideStationAction()
     * Does a certain action from the station based on what the status of the currentTrain is.
     *
     * @param stationClicked Station that was gathered from the user's click
     */
    private void decideStationAction(Station stationClicked)
    {
        if (currentTrain != null)
        {
            // We want the train to go to the clicked station (i.e. request a route)
            if (currentTrain.hasAStation())
            {
                currentTrain.requestRoute(stationClicked.toString());
                Notifications.updateUserAlert(currentTrain.toString() + " requested a route to " + stationClicked.toString());

                // TODO: Weird situation where if a useer puts a train in a station, but doesn't give it a route right away
                // then it will be stuck there forever...
                currentTrain = null;
            }
            // We want to put the train in the station
            else
            {
                // Setting the currentTrain's element to null so it's spot is now available, avoids redraw errors
                activeTrains.set(activeTrains.indexOf(currentTrain), null);
                currentTrain.setNeighbors(stationClicked, null);
                Notifications.updateUserAlert(currentTrain.toString() + " has been put into " + stationClicked.toString() +
                        ". Select a destination.");
                currentTrain.setCoords(stationClicked.getCanvasX() + 10, stationClicked.getCanvasY() + 10);
            }
        }
        else
        {
            Notifications.updateUserAlert("You can't select a station without a train. Please make a train and select it.");
        }

    }

    /**
     * attemptTrainSelect()
     * Attempts to select a station based on users click. This will set the currentTrain if clicking on a train.
     *
     * @param x x-coord that was clicked
     * @param y y-coord that was clicked
     */
    public void attemptTrainSelect(int x, int y)
    {
        for (int i = 0; i < activeTrains.size(); ++i)
        {
            if (activeTrains.get(i) != null)
            {
                if (activeTrains.get(i).isInClickedArea(x, y))
                {
                    currentTrain = activeTrains.get(i);
                    Notifications.updateUserAlert("You selected " + activeTrains.get(i).toString() + ". Please select a station for it.");
                }
            }

        }
    }

    /**
     * freshInitialize()
     * Initializes the data structures in this class to null for initial use. Could also be used if we are making a
     * new simulation on the fly.
     */
    private void freshInitialize()
    {
        railConfig = new RailConfiguration(gcDraw);
        railConfigLoader = new RailConfigurationLoader(railConfig);
        stationList = new ArrayList<>();
        activeTrains = new ArrayList<>();
        drawableList = new ArrayList<>();
        MAX_TRAINS = 30;

        for (int i = 0; i < MAX_TRAINS; ++i)
        {
            activeTrains.add(null);
        }
    }

    /**
     * reDraw()
     * This effectively becomes a thread that is constantly redrawing the canvas.
     * Inspired by https://stackoverflow.com/questions/3541676/java-thread-every-x-seconds
     */
    private void reDraw()
    {
        ScheduledExecutorService exec = Executors.newSingleThreadScheduledExecutor();
        exec.scheduleAtFixedRate(new Runnable()
        {
            @Override
            public void run()
            {
                gcDraw.clearRect(0, 0, 800, 900);
                gcDraw.fillText("Trainyard", 10, 830);
                for (int i = 0; i < drawableList.size(); ++i)
                {
                    drawableList.get(i).draw();
                }
            }
        }, 0, 40, TimeUnit.MILLISECONDS);

    }

    /**
     * launchNewConfiguration()
     * This starts the program by loading a file with a track configuration in it.
     * This starts instantiating all the objects and does the initial draw
     *
     * @param configurationName The String of the file that holds the configuration.
     */
    public void launchNewConfiguration(String configurationName)
    {
        // Loading the specified configuration of tracks, lights, switches, stations
        railConfigLoader.loadNewConfiguration(configurationName, gcDraw);

        // Drawing the components and getting back the list of components we need to continue to draw
        drawableList = railConfig.drawInitialComponents();

        // Attaching switchs to their switch neighbors
        railConfig.attachSwitches();

        // Getting the list of possible stations that we can put trains into
        stationList = railConfig.getStationList();

        // Begin re-drawing all of our new components
        reDraw();
    }

    /**
     * makeTrain()
     * Method that is engaged by the user. Makes a train on screen in the train yard that is ready to be placed in
     * a station.
     */
    public void makeTrain()
    {
        Notifications.updateUserAlert("Please select a train and then select your station for it to start.");

        // This is all to make sure that the train is drawn in a unique spot. If trains are removed in the middle
        // of the drawn list, the behavior can get kind of weird. This check protects against this.
        int indexOfNullTrain = -1;
        for (int i = 0; i < MAX_TRAINS; ++i)
        {
            if (activeTrains.get(i) == null)
            {
                indexOfNullTrain = i;
                break;
            }
        }

        if (indexOfNullTrain != -1)
        {
            Train aTrain = new Train(gcDraw,
                    trainyardX + (indexOfNullTrain % 10) * 75, trainyardY + (indexOfNullTrain / 10) * 30);
            activeTrains.set(indexOfNullTrain, aTrain);
            drawableList.add(aTrain);
            // This way trains won't outlive Main.
            aTrain.setDaemon(true);
            aTrain.start();
        }

    }



}
