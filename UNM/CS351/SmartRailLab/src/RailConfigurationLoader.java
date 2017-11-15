import javafx.scene.canvas.GraphicsContext;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

// Class to interface with some structure to load the initial rail configuration
// Should be able to load a file, XML or a hard coded constructor, imo use hard coded at first
public class RailConfigurationLoader
{
    private ArrayList<TrackLine> trackLines;
    private RailConfiguration rc;

    /**
     * RailConfigurationLoader()
     * RailConfigurationLoader's constructor. This class is used to interface with a file structure that holds what
     * a given train simulation should look like. This is done by loading the CSV file, reading it, filling an array
     * with it's values and feeding it to RailConfiguration/TrackLine.
     * This class could easily be converted to XML/other file format
     * @param rc RailConfiguration object that will use this class' output
     */
    public RailConfigurationLoader(RailConfiguration rc)
    {
        trackLines = new ArrayList<>();
        this.rc = rc;
    }

    /**
     * loadNewConfiguration()
     * Loads a new configuration text file and loads it into an array that builds an object that holds the components.
     * For example, this is how we know what area has a light, switch, etc.
     *
     * @param configFileName Filename of the configuration file that shows this class to setup the board.
     * @param gcDraw         GraphicsContext that gets passed to the underlying component holding object.
     */
    public void loadNewConfiguration(String configFileName, GraphicsContext gcDraw)
    {
        trackLines.clear();
        try
        {
            InputStream inputFile = getClass().getResourceAsStream("Configurations/" + configFileName);
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputFile, "UTF-8"));
            String line;
            int trackLineNum = 0; // Tells us what trackLine we are working on. 0 is the first trackLine, 3 is the 4th... etc.
            while ((line = reader.readLine()) != null)
            {
                String[] components = line.split(",");
                TrackLine tl = new TrackLine(components, gcDraw, trackLineNum);
                trackLines.add(tl);
                trackLineNum++;
            }
        }
        catch (IOException e)
        {
            System.out.println(e.getMessage());
        }

        rc.loadTracks(trackLines);
    }


}
