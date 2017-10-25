/**
 * Created by Anna Careyon 10/20/17
 *
 * This class is intended to hold methods for testing as we slowly progress toward our goal.
 */

public class TestMain
{
    public static void main (String args[])
    {
        testSearchRoute();
    }
    
    private static void testMessage()
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
    
    private static void testSearchRoute()
    {
        Train t = new Train();
        RailTrack tracka = new RailTrack();
        RailTrack trackb = new RailTrack();
        RailTrack trackc = new RailTrack();
        Station station = new Station(); //named "Station0"
        
        t.setCurrentTrack(tracka);
        tracka.setRightNeighbor(trackb);
        trackb.setLeftNeighbor(tracka);
        trackb.setRightNeighbor(trackc);
        trackc.setLeftNeighbor(trackb);
        
        t.requestRoute("Station0");
        
        //If there were threads, this would run on its own. But we have to pretend to be multiple threads for now.
        tracka.run(); //this should read and send the message
        trackb.run(); //this should read and send the message
        trackc.run(); //this should read and send the message
        station.run();
    }
}
