import javafx.application.Platform;
import javafx.scene.control.Label;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

import java.io.IOException;
import java.io.InputStream;

/**
 * This is a class to update the UI on what is going on in the simulation. User alerts should come from the conductor
 * as that is where most of the logic happens. The back end information is updated in various parts of the code and
 * inserted into the sim status text box. Additionally, playing sounds will come from here.
 */
public class Notifications
{

    private static Label lblUserAlert, lblSimStatus;

    public Notifications(Label lblUserAlert, Label lblSimStatus)
    {
        this.lblUserAlert = lblUserAlert;
        this.lblSimStatus = lblSimStatus;
    }

    /**
     * updateUserAlert()
     * The platform runlater is necessary if the message originates from a thread outside the UI. Otherwise there
     * will be a conflict and ultimately an error.
     * @param userAlert String to put into user alert box
     */
    public static void updateUserAlert(String userAlert)
    {
        Platform.runLater(()->{ lblUserAlert.setText(userAlert); });
    }

    /**
     * updateSimStatus()
     * The platform runlater is necessary if the message originates from a thread outside the UI. Otherwise there
     * will be a conflict and ultimately an error.
     * @param simStatus String to put into sim status box
     */
    public static void updateSimStatus(String simStatus)
    {
        Platform.runLater(()->{ lblSimStatus.setText(simStatus); });
    }

    /**
     * playSound()
     * Plays a given sound.
     * There is no reason that this can't be a static. It doesn't hold any state.
     * @param filePath Path of the sound you're trying to play
     */
    public static void playSound(String filePath)
    {
        try{
            InputStream is = Conductor.class.getResourceAsStream(filePath);
            AudioStream audioStream = new AudioStream(is);
            AudioPlayer.player.start(audioStream);
        }
        catch(IOException e) { System.out.println(e.getMessage()); }
    }



}
