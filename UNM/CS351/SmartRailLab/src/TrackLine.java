import javafx.scene.canvas.GraphicsContext;

import java.util.ArrayList;
import java.util.HashMap;

public class TrackLine
{
    private ArrayList<ArrayList<IDrawable>> drawableListList;
    private ArrayList<IMessagable> messagableList;
    private ArrayList<Station> stationList;
    private GraphicsContext gcDraw;
    private int trackLineNum;

    public TrackLine(String[] components, GraphicsContext gcDraw, int trackLineNum)
    {
        drawableListList = new ArrayList<>();
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
        int initialX = 20;
        int initialY = 20;
        int xStep = 100;
        int yStep = 150;

        ArrayList<IDrawable> leftStationList = new ArrayList<>();
        Station leftStation = new Station(components[0], gcDraw, initialX, initialY + yStep*trackLineNum);
        leftStationList.add(leftStation);
        drawableListList.add(leftStationList);
        messagableList.add(leftStation);
        stationList.add(leftStation);

        //TODO: Add more grid types, 3-4-5-6-7
        // Adds a list of components to each element
        for(int i = 1; i < components.length - 1; ++i)
        {
            ArrayList<IDrawable> gridComponentList = new ArrayList<>();
            RailTrack trackToAdd;
            RailLight lightToAdd;
            if(String.valueOf("1").equals(components[i]))
            {
                trackToAdd = new RailTrack(gcDraw, initialX + xStep*i, initialY + yStep*trackLineNum);
                gridComponentList.add(trackToAdd);
                messagableList.add(trackToAdd);
            }
            else if(String.valueOf("2").equals(components[i]))
            {
                //TODO: Does this really need to be a list of lists? The track could contain the switch, light, etc.
                // Look at this part of the design later.
                lightToAdd = new RailLight(gcDraw, initialX + xStep*i, initialY + yStep*trackLineNum);
                trackToAdd = new RailTrack(lightToAdd, gcDraw, initialX + xStep*i, initialY + yStep*trackLineNum);
                gridComponentList.add(trackToAdd);
                gridComponentList.add(lightToAdd);
                messagableList.add(trackToAdd);
            }
            drawableListList.add(gridComponentList);
        }

        ArrayList<IDrawable> rightStationList = new ArrayList<>();
        Station rightStation = new Station(components[components.length - 1], gcDraw, initialX + xStep*drawableListList.size(), initialY + yStep*trackLineNum);
        rightStationList.add(rightStation);
        drawableListList.add(rightStationList);
        messagableList.add(rightStation);
        stationList.add(rightStation);

        attachNeighbors();
    }

    private void attachNeighbors()
    {
        for(int i = 0; i < messagableList.size(); ++i)
        {
            // Must be left station...
            if(i == 0) { messagableList.get(i).setNeighbors(messagableList.get(i+1), messagableList.get(i+1)); }
            // Must be right station...
            else if(i == messagableList.size() - 1) { messagableList.get(i).setNeighbors(messagableList.get(i-1), messagableList.get(i-1)); }
            else
            {
                messagableList.get(i).setNeighbors(messagableList.get(i-1), messagableList.get(i+1));
            }
            ((Thread)messagableList.get(i)).start();
        }
    }

    public ArrayList<ArrayList<IDrawable>> getDrawableListList() { return drawableListList; }
    public ArrayList<Station> getStationList() { return stationList; }

}
