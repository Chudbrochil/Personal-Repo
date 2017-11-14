import javafx.application.Platform;
import javafx.scene.control.Label;

/**
 * This is a class to update the UI on what is going on in the simulation. User alerts should come from the conductor
 * as that is where most of the logic happens. The back end information is updated in various parts of the code and
 * inserted into the sim status text box.
 */
public class Logger
{

    private static Label lblUserAlert, lblSimStatus;

    public Logger(Label lblUserAlert, Label lblSimStatus)
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


}
