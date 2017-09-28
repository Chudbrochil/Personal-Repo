import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.util.Duration;
import java.util.concurrent.TimeUnit;

public class BoggleTimer
{

    Timeline timeline;
    int timeInSeconds;
    Label lblTime;
    MutableBoolean gameOver;

    /**
     * BoggleTimer()
     * This is BoggleTimer's constructor.
     * @param timeInSeconds How many seconds we want the timer for
     * @param gameOver Boolean to set whether the game has ended via time
     */
    public BoggleTimer(int timeInSeconds, MutableBoolean gameOver)
    {
        this.timeInSeconds = timeInSeconds;
        this.gameOver = gameOver;
        timeline = new Timeline();
        initializeGameTimer();
    }

    /**
     * startTime()
     * Starts the timer to start playing the game
     * @param lblTime
     */
    public void startTime(Label lblTime)
    {
        timeline.playFromStart();
        this.lblTime = lblTime;
        updateLabel();
    }

    /**
     * initializeGameTimer()
     * Sets up the timeline and handlers for each second of the gameplay and the game end signal
     */
    private void initializeGameTimer()
    {
        timeline.setCycleCount(timeInSeconds);

        EventHandler secondTick = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e)
            {
                decrementTime();
            }
        };

        EventHandler timeUp = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e)
            {
                timeUp();
            }
        };

        KeyFrame gameTick = new KeyFrame(Duration.seconds(1), secondTick);
        timeline.getKeyFrames().add(gameTick);
        timeline.setOnFinished(timeUp);
    }

    /**
     * decrementTime()
     * Method that is called every second to reduce the play clock time
     */
    private void decrementTime()
    {
        timeInSeconds--;
        updateLabel();
    }

    /**
     * timeUp()
     * Method to update data structure to end the game via time
     */
    private void timeUp()
    {
        gameOver.set(true);
    }

    /**
     * updateLabel()
     * Updates the time label
     */
    private void updateLabel()
    {
        String timeString = String.format("%02d:%02d", TimeUnit.SECONDS.toMinutes(timeInSeconds), TimeUnit.SECONDS.toSeconds(timeInSeconds % 60));
        lblTime.setText("Time: " + timeString);
    }



}
