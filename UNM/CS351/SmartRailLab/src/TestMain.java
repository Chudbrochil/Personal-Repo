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
        
        tracka.sendTestMessage();
        
        //If there were threads, this would run on its own. But we have to pretend to be multiple threads for now.
        tracka.run(); //this should do nothing
        trackb.run(); //this should read and send the message
        trackc.run(); //this should read and send the message and report the end of the line.
        
    }
}
