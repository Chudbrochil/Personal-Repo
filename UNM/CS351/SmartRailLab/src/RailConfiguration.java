// Class that instantiates all of the initial pieces for the rail

import java.util.ArrayList;

// TODO: This class should be "static", we only need one...
public class RailConfiguration
{
    private ArrayList<TrackLine> trackLines;

    public RailConfiguration() { trackLines = new ArrayList<>(); }

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

    public ArrayList<IDrawable> loadComponents(ArrayList<IDrawable> drawableComponents)
    {
        return new ArrayList<IDrawable>();
    }


}
