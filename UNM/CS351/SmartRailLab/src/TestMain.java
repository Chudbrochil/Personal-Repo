import javafx.embed.swing.JFXPanel;

/**
 * Created by Anna Careyon 10/20/17
 *
 * This class is intended to hold methods for testing as we slowly progress toward our goal.
 */

public class TestMain
{
    public static void main (String args[])
    {
        testSwitch();
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
        tracka.setNeighbors(null, trackb);
        trackb.setNeighbors(tracka, trackc);
        trackc.setNeighbors(trackb, null);
    
        //tracka.sendTestMessage();
    
        //If there were threads, this would run on its own. But we have to pretend to be multiple threads for now.
        tracka.run(); //this should do nothing
        trackb.run(); //this should read and send the message
        trackc.run(); //this should read and send the message and report the end of the line.
    }
    
    public static void testSearchRoute()
    {
        JFXPanel jfxPanel = new JFXPanel(); //this line is needed so the object images can be stored somewhere.
        Train train1 = new Train();
        RailTrack track1 = new RailTrack(new RailLight());
        RailTrack track2 = new RailTrack();
        RailTrack track3 = new RailTrack();
        Station station1 = new Station(); //named "Station1"
        Station station2 = new Station(); //named "Station2"
    
        /**
         * Layout:
         *  {train1}
         *  station2 - track1 - track2 - track3 - station1
         */
        train1.setNeighbors(station2, null);
        station2.setNeighbors(track1, null);
        track1.setNeighbors(station2, track2);
        track2.setNeighbors(track1, track3);
        track3.setNeighbors(track2, station1);
        station1.setNeighbors(track3, null);
        
        train1.start();
        station1.start();
        station2.start();
        track1.start();
        track2.start();
        track3.start();
    
        train1.requestRoute("Station1");
        try{Thread.sleep(500);}
        catch(Exception e){}
        train1.requestRoute("Station2");
        
        
        //If there were threads, this would run on its own. But we have to pretend to be multiple threads for now.
        //for(int i=0; i<15; i++)
        //{
        //    train1.run();
        //    track1.run(); //this should read and send the message
        //    station2.run();
        //    track2.run(); //this should read and send the message
        //    track3.run(); //this should read and send the message
        //    station1.run();//This should read and station should confirm that it has been found, as requested.
        //}
    }
    
    public static void testSwitch()
    {
        JFXPanel jfxPanel = new JFXPanel(); //this line is needed so the object images can be stored somewhere.
        Train train1 = new Train();
        RailTrack track1 = new RailTrack(new RailLight());
        RailTrack track2 = new RailTrack();
        RailTrack track3 = new RailTrack();
        RailSwitch switch1 = new RailSwitch();
        RailSwitch switch2 = new RailSwitch();
        //RailUpSwitch
        Station station1 = new Station(); //named "Station1"
        Station station2 = new Station(); //named "Station2"
        Station station3 = new Station();
        Station station4 = new Station();
    
        /**
         * Layout:
         *           station4 - switch2 - track3 - station1
         *  {train1}              _-'
         *  station2 - track1 - switch1 - track2 - station3
         *
         */
        train1.setNeighbors(station2, null);
        station2.setNeighbors(null,track1);
        track1.setNeighbors(station2, switch1);
        switch1.setNeighbors(track1, track2);
        switch1.setSwitchNeighbor(switch2, Direction.RIGHT);
        track2.setNeighbors(switch1, station3);
        station4.setNeighbors(null, switch2);
        switch2.setNeighbors(station4,track3);
        switch2.setSwitchNeighbor(switch1, Direction.LEFT);
        track3.setNeighbors(switch2, station1);
        station1.setNeighbors(track3, null);
    
        train1.start();
        station1.start();
        station2.start();
        station3.start();
        station4.start();
        switch1.start();
        switch2.start();
        track1.start();
        track2.start();
        track3.start();
    
        train1.requestRoute("Station4");
        try{Thread.sleep(6000);}
        catch(Exception e){}
        train1.requestRoute("Station1");
        try{Thread.sleep(6000);}
        catch(Exception e){}
        train1.requestRoute("Station4");
    }
}
