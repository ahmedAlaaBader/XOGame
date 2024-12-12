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
        new Thread(this::runClient).start();
    }

    private void runClient() {
        // any one will work at login or sign up will use this function and may be need to build another one (you must conseder 
        //that this function for test the conection between server and client only )
        try (Socket mySocket = new Socket(InetAddress.getLocalHost(), 5007);
             DataOutputStream myDataOutStream = new DataOutputStream(mySocket.getOutputStream());
             DataInputStream myDataInStream = new DataInputStream(mySocket.getInputStream())) {

            // Send username and password to server for test only 
            myDataOutStream.writeUTF("Ahmed");
            myDataOutStream.writeUTF("123");

            // Receive server response
            String myMessage = myDataInStream.readUTF();
            System.out.println("Server Response: " + myMessage);

        } catch (IOException ex) {
            Logger.getLogger(XOGameVerOne.class.getName()).log(Level.SEVERE, "Failed to connect to the server", ex);
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}

