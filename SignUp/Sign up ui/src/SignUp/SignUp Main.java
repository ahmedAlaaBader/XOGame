package uiregister;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import uiregister.SignUp;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Create an instance of your custom Login pane
        SignUp signupPane = new SignUp();

        // Create a scene and set the Login pane as its root
        Scene scene = new Scene(signupPane, 600, 400);

        // Set the scene on the primary stage and show it
        primaryStage.setScene(scene);
        primaryStage.setTitle(""); // Set your desired title here
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}