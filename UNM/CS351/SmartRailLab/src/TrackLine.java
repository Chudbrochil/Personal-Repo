import javafx.scene.canvas.GraphicsContext;

import java.util.ArrayList;
import java.util.HashMap;

public class TrackLine
{
    //private ArrayList<ArrayList<IDrawable>> drawableListList;
    private ArrayList<IDrawable> drawableList;
    private ArrayList<IMessagable> messagableList;
    private ArrayList<Station> stationList;
    private GraphicsContext gcDraw;
    private int trackLineNum;

    public TrackLine(String[] components, GraphicsContext gcDraw, int trackLineNum)
    {
        //drawableListList = new ArrayList<>();
        drawableList = new ArrayList<>();
        messagableList = new ArrayList<>();
        stationList = new ArrayList<>();
        this.gcDraw = gcDraw;
        this.trackLineNum = trackLineNum;
        initializeComponents(components);
    }

    @Override
    public String toString()
    {
        String output = "";
        for(int i = 0; i < messagableList.size(); ++i)
        {
            output += messagableList.get(i).toString();
        }
        return output;
    }

    /**
     * 0 reserved for station
     * 1 is track
     * 2 is track + light
     * 3 is track + light + switch up
     * 4 is track + light + switch down
     */
    private void initializeComponents(String[] components)
    {
        int initialX = 0;
        int initialY = 20;
        int xStep = 100;
        int yStep = 150;

        Station leftStation = new Station(components[0], gcDraw, initialX, initialY + yStep*trackLineNum);
        drawableList.add(leftStation);
        messagableList.add(leftStation);
        stationList.add(leftStation);

        //TODO: Add more grid types, 3-4-5-6-7
        // Adds a list of components to each element
        for(int i = 1; i < components.length - 1; ++i)
        {
            RailTrack trackToAdd;
            RailLight lightToAdd;
            if(String.valueOf("1").equals(components[i]))
            {
                trackToAdd = new RailTrack(gcDraw, initialX + xStep*i, initialY + yStep*trackLineNum);
                drawableList.add(trackToAdd);
                messagableList.add(trackToAdd);
            }
            else if(String.valueOf("2").equals(components[i]))
            {
                lightToAdd = new RailLight(gcDraw, initialX + xStep*i, initialY + yStep*trackLineNum);
                trackToAdd = new RailTrack(lightToAdd, gcDraw, initialX + xStep*i, initialY + yStep*trackLineNum);
                drawableList.add(lightToAdd);
                drawableList.add(trackToAdd);
                messagableList.add(trackToAdd);
            }
        }

        Station rightStation = new Station(components[components.length - 1], gcDraw,
                initialX + xStep*(components.length-1), initialY + yStep*trackLineNum);
        drawableList.add(rightStation);
        messagableList.add(rightStation);
        stationList.add(rightStation);

        attachNeighbors();
    }

    private void attachNeighbors()
    {
        for(int i = 0; i < messagableList.size(); ++i)
        {
            // Must be left station...
            if(i == 0) { messagableList.get(i).setNeighbors(null, messagableList.get(i+1)); }
            // Must be right station...
            else if(i == messagableList.size() - 1) { messagableList.get(i).setNeighbors(messagableList.get(i-1), null); }
            else
            {
                messagableList.get(i).setNeighbors(messagableList.get(i-1), messagableList.get(i+1));
            }
            ((Thread)messagableList.get(i)).start();
        }
    }

    public ArrayList<IDrawable> getDrawableList() { return drawableList; }
    public ArrayList<Station> getStationList() { return stationList; }

}
