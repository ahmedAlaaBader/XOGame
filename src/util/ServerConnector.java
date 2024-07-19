package util;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ServerConnector {
    private static ServerConnector connector;

    private Socket serverSocket;
    private DataInputStream serverReader;
    private DataOutputStream serverWriter;

    private ServerConnector() {
        try {
            serverSocket = new Socket(InetAddress.getLocalHost(), 5013);
            serverReader = new DataInputStream(serverSocket.getInputStream());
            serverWriter = new DataOutputStream(serverSocket.getOutputStream());
        } catch (IOException exception) {
            Logger.getLogger(ServerConnector.class.getName()).log(Level.SEVERE, null, exception);
        }
    }

    public static ServerConnector connect() {
        if (connector == null) {
            connector = new ServerConnector();
        }

        return connector;
    }

    public String authenticate(String username, String password) {
        System.out.println("Authenticate -> username: " + username);
        String serverResponse = null;

        try {
            serverWriter.writeUTF("Login");
            serverWriter.writeUTF(username);
            serverWriter.writeUTF(password);

            serverResponse = serverReader.readUTF();
        } catch (IOException exception) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, null, exception);
        }

        System.out.println("Server Response: " + serverResponse);
        return serverResponse;
    }

    public void sendMove(String move) {
        try {
            serverWriter.writeUTF(move);
        } catch (IOException exception) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, null, exception);
        }
    }

    public String receiveMove() {
        String move = null;

        try {
            move = serverReader.readUTF();
        } catch (IOException exception) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, null, exception);
        }

        return move;
    }

    public String receiveStatus() {
        String status = null;

        try {
            status = serverReader.readUTF();
        } catch (IOException exception) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, null, exception);
        }

        return status;
    }
    
    public String receiveInitialPlayer() {
        String player = null;

        try {
            player = serverReader.readUTF();
        } catch (IOException exception) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, null, exception);
        }

        return player;
    }
}