/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TheServer;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Uilogin extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Create an instance of your custom Login pane
        LogIn signupPan = new LogIn();

        // Create a scene and set the Login pane as its root
        Scene scene = new Scene(signupPan, 600, 400);

        // Set the scene on the primary stage and show it
        primaryStage.setScene(scene);
        primaryStage.setTitle("Login"); // Set your desired title here
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
