import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    /**
     * start()
     * @param primaryStage Stage that we are using javafx on
     * @throws Exception Needed for this method, mandatory exception catch
     */
    @Override
    public void start(Stage primaryStage) throws Exception
    {
        Parent root = FXMLLoader.load(getClass().getResource("FXMLDocumentController.fxml"));
        primaryStage.setTitle("Boggle!");
        primaryStage.setScene(new Scene(root, 800, 700));
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    /**
     * main()
     * Main thread that launchs the javafx scene
     * @param args Run arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}
