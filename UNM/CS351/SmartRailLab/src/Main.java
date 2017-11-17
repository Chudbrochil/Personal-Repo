// Resource Attributions
// Tracks: https://www.freepik.com/free-vector/train-track-collection_1111346.htm
// Trains: https://opengameart.org/content/passenger-train-car


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application
{

    public static boolean DEBUG = false;

    /**
     * start()
     * @param primaryStage Stage that we are drawing via FXML
     * @throws Exception Just in case we get a UI exception
     */
    @Override
    public void start(Stage primaryStage) throws Exception
    {
        Parent root = FXMLLoader.load(getClass().getResource("FXMLDocumentController.fxml"));
        primaryStage.setTitle("SmartRail!");
        primaryStage.setScene(new Scene(root, 800, 1000));
        primaryStage.show();
        primaryStage.setResizable(false);
        primaryStage.setOnCloseRequest(e -> System.exit(0));
    }

    /**
     * main()
     * Starts the primary stage, launchs the UI.
     * @param args Default command line args
     */
    public static void main(String[] args)
    {
        launch(args);
    }
}




//