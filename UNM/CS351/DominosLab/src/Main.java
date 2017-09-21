// Anthony Galczak - agalczak@unm.edu - WGalczak@gmail.com
// Dominos Game - Lab 1 CS 351

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("FXMLDocumentController.fxml"));
        primaryStage.setTitle("Dominos");
        primaryStage.setScene(new Scene(root, 1100, 400));
        primaryStage.setResizable(false); // Fixes a lot of formatting problems to not let user resize
        primaryStage.show();
    }

    public static void main(String[] args)
    {
        launch(args);
    }

}
