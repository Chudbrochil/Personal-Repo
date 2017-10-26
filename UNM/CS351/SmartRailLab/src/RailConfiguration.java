// Class that instantiates all of the initial pieces for the rail

import javafx.scene.canvas.GraphicsContext;

import java.util.ArrayList;

// TODO: This class should be "static", we only need one...
public class RailConfiguration
{
    private ArrayList<TrackLine> trackLines;
    private GraphicsContext gcDraw;

    public RailConfiguration(GraphicsContext gcDraw)
    {
        trackLines = new ArrayList<>();
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
        //TODO: Fixed size for now!
        for(int i = 0; i < trackLines.size(); ++i)
        {

        }
        return new ArrayList<IDrawable>();
    }


}
