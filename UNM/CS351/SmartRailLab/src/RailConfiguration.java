// Class that instantiates all of the initial pieces for the rail

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import java.util.ArrayList;

public class RailConfiguration
{
    private ArrayList<TrackLine> trackLines;
    private GraphicsContext gcDraw;
    private ArrayList<Station> stationList;

    public RailConfiguration(GraphicsContext gcDraw)
    {
        trackLines = new ArrayList<>();
        stationList = new ArrayList<>();
        this.gcDraw = gcDraw;
    }

    /**
     * loadTracks()
     *
     * This loads all of the trackline objects into drawable lists and imessagable lists.
     * This also allows the initial draw to be done.
     *
     * @param trackLines A list holding objects that hold all components in a line.
     */
    public void loadTracks(ArrayList<TrackLine> trackLines)
    {
        this.trackLines.addAll(trackLines);
    }

    /**
     * drawInitialComponents()
     *
     * Does the first draw of all the components. This is done one trackLine at a time.
     *
     * @return The full list of all drawable items. These need to be redrawn by the controller.
     */
    public ArrayList<IDrawable> drawInitialComponents()
    {
        ArrayList<IDrawable> fullTrackDrawable = new ArrayList<>();

        gcDraw.setFill(Color.BLACK);
        gcDraw.setFont(new Font("Arial", 12));

        // trackLines is each row of components, each trackLine is down in height
        for (int i = 0; i < trackLines.size(); ++i)
        {
            ArrayList<IDrawable> currentTrack = trackLines.get(i).getDrawableList();
            fullTrackDrawable.addAll(currentTrack);
            // currentTrack is a list of lists of drawables, i.e. a list of components within a row
            for (int j = 0; j < currentTrack.size(); ++j)
            {
                currentTrack.get(j).draw();
            }
        }

        return fullTrackDrawable;
    }

    /**
     * attachSwitches()
     * Finds all the switches and matches them up as neighbors. This facilitates the communication between
     * the train and when it's on a switch for drawing and path finding.
     */
    public void attachSwitches()
    {
        ArrayList<IMessagable> aTrackLine = new ArrayList<>();

        int maximumTrackSize  = 0;
        for(int i = 0; i < trackLines.size(); ++i)
        {
            if(trackLines.get(i).getMessagableList().size() > maximumTrackSize)
            {
                maximumTrackSize = trackLines.get(i).getMessagableList().size();
            }
        }
        RailSwitch[] switchList = new RailSwitch[maximumTrackSize];

        for (int i = 0; i < trackLines.size(); ++i)
        {
            aTrackLine = trackLines.get(i).getMessagableList();

            for (int j = 0; j < aTrackLine.size(); ++j)
            {
                System.out.print(j);
                IMessagable currentComponent = aTrackLine.get(j);
                if (currentComponent instanceof RailSwitch)
                {
                    // Add yourself to the array
                    if (switchList[j] == null)
                    {
                        switchList[j] = (RailSwitch) currentComponent;
                    }
                    // Attach yourself to your buddy,
                    else
                    {
                        // Getting the switch's from the list
                        RailSwitch upSwitch = (RailSwitch) currentComponent;
                        RailSwitch downSwitch = switchList[j];
                        switchList[j] = null;

                        // Assigning it's switch neighbors
                        downSwitch.setSwitchNeighbor(upSwitch);
                        upSwitch.setSwitchNeighbor(downSwitch);
                    }
                }
            }


        }
    }

    /**
     * getStationList()
     *
     * Returns the full list of stations. We need these to get trains to hook into for initial placement and
     * requesting routes.
     *
     * @return A list of all the station objects.
     */
    public ArrayList<Station> getStationList()
    {
        for (int i = 0; i < trackLines.size(); ++i)
        {
            stationList.addAll(trackLines.get(i).getStationList());
        }
        return stationList;
    }

}
