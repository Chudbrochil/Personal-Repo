/**
 * Created by Anna Careyon 10/20/17
 *
 * This class is intended to hold methods for testing as we slowly progress toward our goal.
 */

public class TestMain
{
    public static void main (String args[])
    {
        RailTrack tracka = new RailTrack();
        RailTrack trackb = new RailTrack();
        RailTrack trackc = new RailTrack();
        
        tracka.setRightNeighbor(trackb);
        trackb.setLeftNeighbor(tracka);
        trackb.setRightNeighbor(trackc);
        trackc.setLeftNeighbor(trackb);
        
        
    }
}
