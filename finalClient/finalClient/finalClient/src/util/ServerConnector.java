package util;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Move;

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
            Logger.getLogger(ServerConnector.class.getName()).log(Level.SEVERE, "Error initializing server connector", exception);
        }
    }

    public static synchronized ServerConnector connect() {
        if (connector == null) {
            connector = new ServerConnector();
        }
        return connector;
    }

    public String authenticate(String user, String pass) {
        System.out.println("Authenticate -> username: " + user);
        String serverResponse = null;

        try {
            serverWriter.writeUTF("Login");
            serverWriter.writeUTF(user);
            serverWriter.writeUTF(pass);
            serverWriter.flush();  // Ensure data is sent before reading

            serverResponse = serverReader.readUTF();
        } catch (IOException exception) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "Error during authentication", exception);
        }

        System.out.println("Server Response: " + serverResponse);
        return serverResponse;
    }

    public String authenticate(String username, String email, String password) {
        System.out.println("Authenticate -> username: " + username);
        String serverResponse = null;

        try {
            serverWriter.writeUTF("SignUp");
            serverWriter.writeUTF(username);
            serverWriter.writeUTF(email);
            serverWriter.writeUTF(password);
            serverWriter.flush();  // Ensure data is sent before reading

            serverResponse = serverReader.readUTF();
        } catch (IOException exception) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "Error during sign up", exception);
        }

        System.out.println("Server Response: " + serverResponse);
        return serverResponse;
    }

    public List<String> getActivePlayer() {
        List<String> activePlayersNames = new ArrayList<>();
        try {
            serverWriter.writeUTF("GetActivePlayers");
            int size = serverReader.readInt();
            System.out.println("Current acitve players: " + size);
            for (int i = 0; i < size; i++) {
                activePlayersNames.add(serverReader.readUTF());
            }
            System.out.println(activePlayersNames);
        } catch (IOException ex) {
            Logger.getLogger(ServerConnector.class.getName()).log(Level.SEVERE, null, ex);
        }
        return activePlayersNames;
    }

    public List<String> getGameRequests() {
        List<String> gameRequest = new ArrayList<>();
        try {
            serverWriter.writeUTF("GetGameRequests");
            int size = serverReader.readInt();
            System.out.println("Current Game Request: " + size);
            for (int i = 0; i < size; i++) {
                gameRequest.add(serverReader.readUTF());
            }
            System.out.println(gameRequest);
        } catch (IOException ex) {
            Logger.getLogger(ServerConnector.class.getName()).log(Level.SEVERE, null, ex);
        }
        return gameRequest;
    }
//    public List<String> checkForRequestor() {
//        List<String> requestorNames = new ArrayList<>();
//        Thread t = new Thread(new Runnable() {
//            @Override
//            public void run() {
//               try {
//
//                int size = serverReader.readInt();
//                   System.out.println("this is size "+size);
//                for (int i = 0; i < size; i++) {
//                    requestorNames.add(serverReader.readUTF());
//                }
//
//            } catch (IOException ex) {
//                Logger.getLogger(ServerConnector.class.getName()).log(Level.SEVERE, null, ex);
//            }
//            
//            }
//            
//        });
//        t.start();
//         return requestorNames;
//            
//            
//        }

    public Future<String> checkForRequestor() {
        Callable<String> callable = () -> {
            String requestorNames = null;
            try {
                requestorNames = serverReader.readUTF();
            } catch (IOException ex) {
                Logger.getLogger(ServerConnector.class.getName()).log(Level.SEVERE, null, ex);
            }
            return requestorNames;
        };

        FutureTask<String> futureTask = new FutureTask<>(callable);
        Thread t = new Thread(futureTask);
        t.start();
        return futureTask;
    }

    public String requestForPlaying(String targetUserName) {
        String serverResponse = null;

        try {
            serverWriter.writeUTF("SendRequest");
            serverWriter.writeUTF(targetUserName);
            serverWriter.flush();

            serverResponse = serverReader.readUTF();
        } catch (IOException exception) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "Error during authentication", exception);
        }

        System.out.println("Server Response: " + serverResponse);
        return serverResponse;
    }

    public void refuseRequest(String targetUserName) {
        try {

            serverWriter.writeUTF("RefuseRequest");
            serverWriter.writeUTF(targetUserName);
            serverWriter.flush();
        } catch (IOException exception) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "Error during authentication", exception);
        }
    }
    
    public boolean receiveFirstFlag() {
        Boolean firstFlag = null;

        try {
            firstFlag = serverReader.readBoolean();
        } catch (IOException exception) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "Error receiving move", exception);
        }
        
        return firstFlag;
    }

    public void sendMove(String move) {
        try {
            serverWriter.writeUTF(move);
            serverWriter.flush();  // Ensure data is sent
        } catch (IOException exception) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "Error sending move", exception);
        }
    }

    public Move receiveMove() {
        String move = null;

        try {
            move = serverReader.readUTF();
        } catch (IOException exception) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "Error receiving move", exception);
        }

        return MoveCaster.castToMove(move);
    }

    public String receiveStatus() {
        String status = null;

        try {
            status = serverReader.readUTF();
        } catch (IOException exception) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "Error receiving status", exception);
        }

        return status;
    }

    public String receiveXorO() {
        String player = null;

        try {
            player = serverReader.readUTF();
        } catch (IOException exception) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "Error receiving initial player", exception);
        }

        return player;
    }

    public void closeSocket() {
        try {
            if (serverWriter != null) {
                serverWriter.close();
            }
            if (serverReader != null) {
                serverReader.close();
            }
            if (serverSocket != null && !serverSocket.isClosed()) {
                serverSocket.close();
            }
        } catch (IOException e) {
            Logger.getLogger(ServerConnector.class.getName()).log(Level.SEVERE, "Error closing socket", e);
        }
    }
}
