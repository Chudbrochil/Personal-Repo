import java.util.ArrayList;
import java.util.HashMap;

public class TrackLine
{
    private String leftStation;
    private String rightStation;
    private ArrayList<Integer> componentsList;
    private ArrayList<ArrayList<IDrawable>> drawableListList;

    public TrackLine(String[] components)
    {
        componentsList = new ArrayList<>();
        drawableListList = new ArrayList<ArrayList<IDrawable>>();

        for(int i = 0; i < components.length; ++i)
        {
            if(i == 0) { leftStation = components[i]; }
            else if (i == components.length - 1) { rightStation = components[i]; }

            // TODO: Catch for exception of incorrect cast?
            else { componentsList.add(Integer.valueOf(components[i])); }
        }
        initializeComponents();
    }

    // TODO: Update this for using drawable list
    @Override
    public String toString()
    {
        String output = "";
        output += leftStation;
        for(int i = 0; i < componentsList.size(); ++i)
        {
            output += componentsList.get(i);
        }
        output += rightStation;
        return output;
    }

    /**
     * 0 reserved for station
     * 1 is track
     * 2 is track + light
     */
    private void initializeComponents()
    {
        ArrayList<IDrawable> leftStationList = new ArrayList<>();
        leftStationList.add(new Station(this.leftStation));
        drawableListList.add(leftStationList);

        //TODO: Add more grid types, 3-4-5-6-7
        for(int i = 0; i < componentsList.size(); ++i)
        {
            ArrayList<IDrawable> gridComponentList = new ArrayList<>();
            gridComponentList.add(new RailTrack());
            if(componentsList.get(i) == 2)
            {
                gridComponentList.add(new RailLight());
            }
            drawableListList.add(gridComponentList);
        }

        ArrayList<IDrawable> rightStationList = new ArrayList<>();
        rightStationList.add(new Station(this.rightStation));
        drawableListList.add(rightStationList);
    }

    public ArrayList<ArrayList<IDrawable>> getDrawableListList() { return drawableListList; }

}
