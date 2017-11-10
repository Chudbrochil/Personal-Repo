import javafx.scene.canvas.GraphicsContext;

import java.util.ArrayList;

public class TrackLine
{
    private ArrayList<IDrawable> drawableList;
    private ArrayList<IMessagable> messagableList;
    private ArrayList<Station> stationList;
    private GraphicsContext gcDraw;
    private int trackLineNum;

    // TODO: Dynamic city selection?
    private static ArrayList<String> westCoastCities;
    private static ArrayList<String> eastCoastCities;

    public TrackLine(String[] components, GraphicsContext gcDraw, int trackLineNum)
    {
        drawableList = new ArrayList<>();
        messagableList = new ArrayList<>();
        stationList = new ArrayList<>();
        westCoastCities = new ArrayList<>();
        eastCoastCities = new ArrayList<>();
        this.gcDraw = gcDraw;
        this.trackLineNum = trackLineNum;
        initializeComponents(components);
    }

    /**
     * getDrawableList()
     * Useful for re-drawing all the components
     * @return The drawable list of components in this line.
     */
    public ArrayList<IDrawable> getDrawableList()
    {
        return drawableList;
    }

    /**
     * getStationList()
     * Useful for attaching trains to stations and requesting routes to stations.
     * @return The list of stations in this line.
     */
    public ArrayList<Station> getStationList()
    {
        return stationList;
    }

    /**
     * getMessagableList()
     * Useful for attaching neighbors to eachother. This is exclusively used at the moment for finding switches
     * and attaching them to eachother.
     * @return The messagable list of components in this line.
     */
    public ArrayList<IMessagable> getMessagableList()
    {
        return messagableList;
    }

    @Override
    public String toString()
    {
        String output = "";
        for (int i = 0; i < messagableList.size(); ++i)
        {
            output += messagableList.get(i).toString();
        }
        return output;
    }

    /**
     * 0 station
     * 1 is track
     * 2 is track + light
     * 3 is track + light + switch up
     * 4 is track + light + switch down
     * Currently we only have switches heading from 2nd track upto 1st track going left to right and the reverse
     * e.g.
     * ------------------------
     *     /
     *    /
     *   /
     * ------------------------
     */
    private void initializeComponents(String[] components)
    {
        int initialX = 0;
        int initialY = 40;
        int xStep = 100;
        int yStep = 100;

        // Adds each component to messagable/drawable lists
        for (int i = 0; i < components.length; ++i)
        {
            Station stationToAdd = null;
            RailTrack trackToAdd = null;
            RailLight lightToAdd = null;
            RailSwitch switchToAdd = null;

            // Station - If the first character is a letter, it must be a station
            if(Character.isLetter(components[i].charAt(0)))
            {
                stationToAdd = new Station(components[i], gcDraw, initialX + xStep*i, initialY + yStep*trackLineNum);
            }
            // Track
            else if (String.valueOf("1").equals(components[i]))
            {
                trackToAdd = new RailTrack(gcDraw, initialX + xStep * i, initialY + yStep * trackLineNum);
            }
            // Track and Light
            else if (String.valueOf("2").equals(components[i]))
            {
                lightToAdd = new RailLight(gcDraw, initialX + xStep * i, initialY + yStep * trackLineNum);
                trackToAdd = new RailTrack(lightToAdd, gcDraw, initialX + xStep * i, initialY + yStep * trackLineNum);
            }
            // Track, UpSwitch and its Light - UpSwitch means light goes on right-side
            else if (String.valueOf("3").equals(components[i]))
            {
                lightToAdd = new RailLight(gcDraw, initialX + xStep * i, initialY + yStep * trackLineNum, Direction.RIGHT);
                switchToAdd = new RailSwitch(lightToAdd, gcDraw, initialX + xStep * i, initialY + yStep * trackLineNum, Direction.RIGHT);
            }
            // Track, DownSwitch and its Light - DownSwitch means light goes on left-side
            else if (String.valueOf("4").equals(components[i]))
            {
                lightToAdd = new RailLight(gcDraw, initialX + xStep * i, initialY + yStep * trackLineNum, Direction.LEFT);
                switchToAdd = new RailSwitch(lightToAdd, gcDraw, initialX + xStep * i, initialY + yStep * trackLineNum, Direction.LEFT);
            }

            if(trackToAdd != null)
            {
                drawableList.add(trackToAdd);
                messagableList.add(trackToAdd);
            }
            if(lightToAdd != null) { drawableList.add(lightToAdd); }
            if(switchToAdd != null)
            {
                drawableList.add(switchToAdd);
                messagableList.add(switchToAdd);
            }
            if(stationToAdd != null)
            {
                drawableList.add(stationToAdd);
                messagableList.add(stationToAdd);
                stationList.add(stationToAdd);
            }

        }

        attachNeighbors();
    }

    /**
     * attachNeighbors()
     *
     * Attachs all the left and right neighbors to IMessagable's. Additional work needs to be done for switches as
     * they span multiple trackLines.
     */
    private void attachNeighbors()
    {
        for (int i = 0; i < messagableList.size(); ++i)
        {
            // Must be left station...
            if (i == 0)
            {
                messagableList.get(i).setNeighbors(null, messagableList.get(i + 1));
            }
            // Must be right station...
            else if (i == messagableList.size() - 1)
            {
                messagableList.get(i).setNeighbors(messagableList.get(i - 1), null);
            }
            else
            {
                messagableList.get(i).setNeighbors(messagableList.get(i - 1), messagableList.get(i + 1));
            }
            // Fixes the annoying issue of threads outliving main
            ((Thread) messagableList.get(i)).setDaemon(true);
            ((Thread) messagableList.get(i)).start();


        }
    }

}
