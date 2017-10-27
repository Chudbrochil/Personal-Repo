// Class that instantiates all of the initial pieces for the rail

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.util.ArrayList;
import java.util.Collections;

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

    public ArrayList<IDrawable> drawInitialComponents(int canvasHeight, int canvasWidth)
    {
        int height = 50;
        int width = 10;
        int heightStep = canvasHeight / trackLines.size();
        int widthStep = canvasWidth / trackLines.get(0).getDrawableListList().size();

        gcDraw.setFill(Color.BLACK);
        gcDraw.setFont(new Font("Arial", 12));

        // trackLines is each row of components, each trackLine is down in height
        for(int i = 0; i < trackLines.size(); ++i)
        {
            ArrayList<ArrayList<IDrawable>> currentTrack = trackLines.get(i).getDrawableListList();
            // currentTrack is a list of lists of drawables, i.e. a list of components within a row
            for(int j = 0; j < currentTrack.size(); ++j)
            {
                // currentGrid is a list of the components in each grid
                ArrayList<IDrawable> currentGrid = currentTrack.get(j);
                for(int k = 0; k < currentGrid.size(); ++k)
                {
                    currentGrid.get(k).draw(width + widthStep*j, height + heightStep*i, gcDraw);
                }

            }
        }

        // TODO: This is a placeholder for now.
        return new ArrayList<IDrawable>();
    }


}
