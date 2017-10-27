// Resource Attributions
// Tracks: https://www.freepik.com/free-vector/train-track-collection_1111346.htm
// Trains: https://opengameart.org/content/passenger-train-car


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception
    {
      Parent root = FXMLLoader.load(getClass().getResource("FXMLDocumentController.fxml"));
      primaryStage.setTitle("SmartRail!");
      primaryStage.setScene(new Scene(root, 800, 800));
      primaryStage.show();
    }


    public static void main(String[] args) { launch(args); }
}
