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

    public RailConfigurationLoader(RailConfiguration rc)
    {
        trackLines = new ArrayList<>();
        this.rc = rc;
    }

    public void loadNewConfiguration(String configFileName)
    {
        trackLines.clear();
        try
        {
            InputStream inputFile = getClass().getResourceAsStream("Configurations/" + configFileName);
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputFile,"UTF-8"));
            String line;
            while((line = reader.readLine()) != null)
            {
                String[] components = line.split(",");
                TrackLine tl = new TrackLine(components);
                trackLines.add(tl);
            }
        }
        catch(IOException e) { System.out.println(e.getMessage()); }

        rc.loadTracks(trackLines);
    }



}
