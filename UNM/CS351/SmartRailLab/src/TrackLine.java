import java.util.ArrayList;

public class TrackLine
{
    private int length; //TODO: Do I even need this? ComponentsList has a length...
    private String leftStation;
    private String rightStation;
    private ArrayList<Integer> componentsList;

    public TrackLine(String[] components)
    {
        componentsList = new ArrayList<>();
        for(int i = 0; i < components.length; ++i)
        {
            if(i == 0) { leftStation = components[i]; }
            else if (i == components.length - 1) { rightStation = components[i]; }

            // TODO: Catch for exception of incorrect cast?
            else { componentsList.add(Integer.valueOf(components[i])); }
        }
        length = components.length;
    }

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

    public String getLeftStation() { return leftStation; }
    public String getRightStation() { return rightStation; }
    public ArrayList<Integer> getComponentsList() { return componentsList; }

}
