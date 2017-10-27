import java.util.ArrayList;

public class TrackLine
{
    private String leftStation;
    private String rightStation;
    private ArrayList<Integer> componentsList;
    private ArrayList<IDrawable> drawableList;

    public TrackLine(String[] components)
    {
        componentsList = new ArrayList<>();
        drawableList = new ArrayList<>();

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

    private void initializeComponents()
    {
        drawableList.add(new Station(this.leftStation));
        for(int i = 0; i < componentsList.size(); ++i)
        {
            if(componentsList.get(i) == 1)
            {
                drawableList.add(new RailTrack());
            }
            //TODO: Add 2, 3, 4, 5, 6, etc.
        }
        drawableList.add(new Station(this.rightStation));
    }

    public ArrayList<IDrawable> getDrawableList() { return drawableList; }

}
