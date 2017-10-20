import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Controller
{

    @FXML
    private Canvas canvasRail;

    @FXML
    private void initialize()
    {
        GraphicsContext gcDraw = canvasRail.getGraphicsContext2D();
        gcDraw.setFill(Color.BLACK);
        gcDraw.fillRect(50, 50, 100, 100); // Drawing a rectangle for effect
    }

    public Controller() { }


}