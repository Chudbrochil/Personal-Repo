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
    
        /**
         * Layout:
         *  tracka - trackb - trackc
         */
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
        RailTrack track1 = new RailTrack();
        RailTrack track2 = new RailTrack();
        RailTrack track3 = new RailTrack();
        Station station1 = new Station(); //named "Station1"
        Station station2 = new Station(); //named "Station2"
    
        /**
         * Layout:
         *  {train1}
         *  station2 - track1 - track2 - track3 - station1
         */
        t.setCurrentTrack(station2);
        station2.setNeighbor(track1);
        track1.setLeftNeighbor(station2);
        track1.setRightNeighbor(track2);
        track2.setLeftNeighbor(track1);
        track2.setRightNeighbor(track3);
        track3.setLeftNeighbor(track2);
        track3.setRightNeighbor(station1);
        station1.setNeighbor(track3);
        
        t.requestRoute("Station1");
        
        //If there were threads, this would run on its own. But we have to pretend to be multiple threads for now.
        for(int i=0; i<5; i++)
        {
            track1.run(); //this should read and send the message
            station2.run();
            track2.run(); //this should read and send the message
            track3.run(); //this should read and send the message
            station1.run();//This should read and station should confirm that it has been found, as requested.
        }
    }
}
