import javafx.scene.canvas.GraphicsContext;

import java.util.ArrayList;

public class TrackLine
{
    private ArrayList<IDrawable> drawableList;
    private ArrayList<IMessagable> messagableList;
    private ArrayList<Station> stationList;
    private GraphicsContext gcDraw;
    private int trackLineNum;

    /**
     * TrackLine()
     * TrackLine's constructor. This will be fed in an array of items corresponding to what that grid piece on the
     * track should look like.
     * @param components Digits corresponding to what should be created and drawn on the board.
     * @param gcDraw Graphics context that we want to draw on.
     * @param trackLineNum What track line number is this? i.e. Is it the very first track? (element 0) This is important
     *                     for giving the components an accurate y position to be drawn on.
     */
    public TrackLine(String[] components, GraphicsContext gcDraw, int trackLineNum)
    {
        drawableList = new ArrayList<>();
        messagableList = new ArrayList<>();
        stationList = new ArrayList<>();
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

    /**
     * toString()
     * @return A string representing each messagable component in this trackline
     */
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
     * initializeComponents()
     * 0 reserved
     * 1 is track
     * 2 is track + light
     * 3 is track + light + switch UpRight
     * 4 is track + light + switch DownLeft
     * 5 is track + light + switch UpLeft
     * 6 is track + light + switch DownRight
     *
     * This is where the majority of the objects get created. Based upon what type of configuration file was fed
     * into the simulation it will make a simulation.
     * @param components List of an individual track lines' corresponding key
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
            String componentString = components[i].trim();

            Station stationToAdd = null;
            RailTrack trackToAdd = null;
            RailLight lightToAdd = null;
            RailSwitch switchToAdd = null;

            // Station - If the first character is a letter, it must be a station
            if(Character.isLetter(componentString.charAt(0)))
            {
                stationToAdd = new Station(componentString, gcDraw, initialX + xStep*i, initialY + yStep*trackLineNum);
            }
            // Track
            else if (String.valueOf("1").equals(componentString))
            {
                trackToAdd = new RailTrack(gcDraw, initialX + xStep * i, initialY + yStep * trackLineNum);
            }
            // Track and Light
            else if (String.valueOf("2").equals(componentString))
            {
                lightToAdd = new RailLight(gcDraw, initialX + xStep * i, initialY + yStep * trackLineNum);
                trackToAdd = new RailTrack(lightToAdd, gcDraw, initialX + xStep * i, initialY + yStep * trackLineNum);
            }
            // Track, UpRightSwitch and its Light - UpSwitch means light goes on right-side
            else if (String.valueOf("3").equals(componentString))
            {
                lightToAdd = new RailLight(gcDraw, initialX + xStep * i, initialY + yStep * trackLineNum, Direction.RIGHT);
                switchToAdd = new RailSwitch(lightToAdd, gcDraw, initialX + xStep * i, initialY + yStep * trackLineNum, Direction.UPRIGHT);
            }
            // Track, DownLeftSwitch and its Light - DownSwitch means light goes on left-side
            else if (String.valueOf("4").equals(componentString))
            {
                lightToAdd = new RailLight(gcDraw, initialX + xStep * i, initialY + yStep * trackLineNum, Direction.LEFT);
                switchToAdd = new RailSwitch(lightToAdd, gcDraw, initialX + xStep * i, initialY + yStep * trackLineNum, Direction.DOWNLEFT);
            }
            // Track, UpLeftSwitch and its Light
            else if (String.valueOf("5").equals(componentString))
            {
                lightToAdd = new RailLight(gcDraw, initialX + xStep * i, initialY + yStep * trackLineNum, Direction.RIGHT);
                switchToAdd = new RailSwitch(lightToAdd, gcDraw, initialX + xStep * i, initialY + yStep * trackLineNum, Direction.UPLEFT);
            }
            // Track, DownRightSwitch and its Light
            else if (String.valueOf("6").equals(componentString))
            {
                lightToAdd = new RailLight(gcDraw, initialX + xStep * i, initialY + yStep * trackLineNum, Direction.LEFT);
                switchToAdd = new RailSwitch(lightToAdd, gcDraw, initialX + xStep * i, initialY + yStep * trackLineNum, Direction.DOWNRIGHT);
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

            ((Thread) messagableList.get(i)).start();
        }
    }

}
