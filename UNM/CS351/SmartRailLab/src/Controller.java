import javafx.fxml.FXML;

import java.util.concurrent.atomic.AtomicInteger;

public class Controller
{
    @FXML
    private void initialize()
    {
        // Example of atomic variables
        AtomicInteger ai = new AtomicInteger(0);

        System.out.println(ai);
    }



    public Controller() { }


}
