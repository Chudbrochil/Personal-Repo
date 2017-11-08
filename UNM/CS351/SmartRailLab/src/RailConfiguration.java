// Class that instantiates all of the initial pieces for the rail

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import java.util.ArrayList;

// TODO: This class should be "static", we only need one...
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

    public void loadTracks(ArrayList<TrackLine> trackLines)
    {
        this.trackLines.addAll(trackLines);
        testTrackLines();
    }

    // TODO: Purely debug
    private void testTrackLines()
    {
        for(int i = 0; i < trackLines.size(); ++i)
        {
            System.out.println(trackLines.get(i).toString());
        }
        System.out.println("");
    }

    public ArrayList<IDrawable> drawInitialComponents()
    {
        ArrayList<IDrawable> fullTrackDrawable = new ArrayList<>();

        gcDraw.setFill(Color.BLACK);
        gcDraw.setFont(new Font("Arial", 12));

        // trackLines is each row of components, each trackLine is down in height
        for(int i = 0; i < trackLines.size(); ++i)
        {
            ArrayList<IDrawable> currentTrack = trackLines.get(i).getDrawableList();
            fullTrackDrawable.addAll(currentTrack);
            // currentTrack is a list of lists of drawables, i.e. a list of components within a row
            for(int j = 0; j < currentTrack.size(); ++j)
            {
                currentTrack.get(j).draw();
            }
        }

        return fullTrackDrawable;
    }

    public ArrayList<Station> getStationList()
    {
        for(int i = 0; i < trackLines.size(); ++i)
        {
            stationList.addAll(trackLines.get(i).getStationList());
        }
        return stationList;
    }

}
