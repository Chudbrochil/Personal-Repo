import java.util.ArrayList;
import java.util.HashMap;

public class TrackLine
{
    private Station leftStation;
    private Station rightStation;
    private ArrayList<ArrayList<IDrawable>> drawableListList;
    private ArrayList<IMessagable> messagableList;

    public TrackLine(String[] components)
    {
        drawableListList = new ArrayList<ArrayList<IDrawable>>();
        messagableList = new ArrayList<>();
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
     */
    private void initializeComponents(String[] components)
    {
        ArrayList<IDrawable> leftStationList = new ArrayList<>();
        leftStation = new Station(components[0]);
        leftStationList.add(leftStation);
        drawableListList.add(leftStationList);
        messagableList.add(leftStation);

        //TODO: Add more grid types, 3-4-5-6-7
        // Adds a list of components to each element
        for(int i = 1; i < components.length - 1; ++i)
        {
            ArrayList<IDrawable> gridComponentList = new ArrayList<>();
            RailTrack trackToAdd;
            RailLight lightToAdd;
            if(String.valueOf("1").equals(components[i]))
            {
                trackToAdd = new RailTrack();
                gridComponentList.add(trackToAdd);
                messagableList.add(trackToAdd);
            }
            else if(String.valueOf("2").equals(components[i]))
            {
                //TODO: Does this really need to be a list of lists? The track could contain the switch, light, etc.
                // Look at this part of the design later.
                lightToAdd = new RailLight();
                trackToAdd = new RailTrack(lightToAdd);
                gridComponentList.add(trackToAdd);
                gridComponentList.add(lightToAdd);
                messagableList.add(trackToAdd);
            }
            drawableListList.add(gridComponentList);
        }

        ArrayList<IDrawable> rightStationList = new ArrayList<>();
        rightStation = new Station(components[components.length - 1]);
        rightStationList.add(rightStation);
        drawableListList.add(rightStationList);
        messagableList.add(rightStation);

        attachNeighbors();
    }

    private void attachNeighbors()
    {
        // TODO: This casting can removed by adding setright/setleft neighbor in the interface....
//        ((Station)messagableList.get(0)).setNeighbor(messagableList.get(1));
//        for(int i = 1; i < messagableList.size() - 1; ++i)
//        {
//            ((RailTrack)messagableList.get(i)).setLeftNeighbor(messagableList.get(i-1));
//            ((RailTrack)messagableList.get(i)).setRightNeighbor(messagableList.get(i+1));
//        }
//        ((Station)messagableList.get(messagableList.size()-1)).setNeighbor(messagableList.get(messagableList.size()-1));


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

        }


    }

    public ArrayList<ArrayList<IDrawable>> getDrawableListList() { return drawableListList; }

}
