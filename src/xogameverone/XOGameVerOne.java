package xogameverone;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class XOGameVerOne extends Application {

    @Override
    public void start(Stage primaryStage) {
        selectModeBase root = new selectModeBase();
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setTitle("XO Game");
        primaryStage.show();
        
    }

    

    public static void main(String[] args) {
        launch(args);
    }
}

