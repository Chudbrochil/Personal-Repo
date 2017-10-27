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
        int height = 10;
        int width = 10;
        int heightStep = canvasHeight / trackLines.size();
        int widthStep = canvasWidth / trackLines.get(0).getDrawableList().size();

        gcDraw.setFill(Color.BLACK);
        gcDraw.setFont(new Font("Arial", 12));

        for(int i = 0; i < trackLines.size(); ++i)
        {
            ArrayList<IDrawable> currentTrack = trackLines.get(i).getDrawableList();
            for(int j = 0; j < currentTrack.size(); ++j)
            {
                currentTrack.get(j).draw(width + widthStep*j, height + heightStep*i, gcDraw);
                System.out.println(currentTrack.get(j).toString());
            }
        }

        return new ArrayList<IDrawable>();
    }


}
