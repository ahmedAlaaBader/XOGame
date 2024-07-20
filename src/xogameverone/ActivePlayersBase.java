package xogameverone;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import static javafx.scene.layout.Region.USE_PREF_SIZE;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class ActivePlayersBase extends AnchorPane {

    protected final ImageView imageView;
    protected final ToggleButton activePlayersButton;
    protected final DropShadow dropShadow;
    protected final ToggleButton gameRequestbutton;
    protected final DropShadow dropShadow0;
    protected final Button sendRequestButton;
    protected final DropShadow dropShadow1;
    protected final Button cancelButton;
    protected final DropShadow dropShadow2;
    protected final VBox vBox;
    protected final ToggleGroup group1;
    private static final double BUTTON_WIDTH = 150.0;
    private static final double BUTTON_HEIGHT = 41.0;
    private static final int MAX_RADIOBUTTONS = 15;

    public ActivePlayersBase() throws IOException {

        imageView = new ImageView();
        dropShadow = new DropShadow();
        dropShadow0 = new DropShadow();
        dropShadow1 = new DropShadow();
        dropShadow2 = new DropShadow();
        vBox = new VBox();
        group1 = new ToggleGroup();

        setMaxHeight(USE_PREF_SIZE);
        setMaxWidth(USE_PREF_SIZE);
        setMinHeight(USE_PREF_SIZE);
        setMinWidth(USE_PREF_SIZE);
        setPrefHeight(400.0);
        setPrefWidth(600.0);

        imageView.setFitHeight(407.0);
        imageView.setFitWidth(600.0);
        imageView.setLayoutY(-5.0);
        imageView.setImage(new Image("/images/khlfia.png"));

        activePlayersButton = createToggleButton(141.0, -5.0, "Active Players");
        gameRequestbutton = createToggleButton(292.0, -5.0, "Requests");
        sendRequestButton = createButton(112.0, 358.0, "Send Request");
        cancelButton = createButton(332.0, 358.0, "Cancel");

        vBox.setLayoutX(141.0);
        vBox.setLayoutY(43.0);
        vBox.setPrefHeight(311.0);
        vBox.setPrefWidth(315.0);

        getChildren().add(imageView);
        getChildren().add(activePlayersButton);
        getChildren().add(gameRequestbutton);
        getChildren().add(sendRequestButton);
        getChildren().add(cancelButton);
        getChildren().add(vBox);
        this.getStylesheets().add(getClass().getResource("style.css").toExternalForm());

       gameRequestbutton.setOnAction(event -> {
    List<String> gameRequests = fetchGameRequestsFromServer();
    vBox.getChildren().clear();
    for (String playerName : gameRequests) {
        if (vBox.getChildren().size() < MAX_RADIOBUTTONS) {
            RadioButton radioButton = new RadioButton(playerName);
            radioButton.setFont(new Font("System Bold Italic", 14.0));
            radioButton.setToggleGroup(group1);
            radioButton.setMaxWidth(vBox.getPrefWidth());
            radioButton.setWrapText(true);
            vBox.getChildren().add(radioButton);
        }
    }
    sendRequestButton.setText("Accept");
    cancelButton.setText("Decline");
});



        activePlayersButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (activePlayersButton.isSelected()) {
                    String currentplayer = null;
                    fetchActivePlayersFromServer(currentplayer);
                    sendRequestButton.setText("Send Request");
                    cancelButton.setText("Cancel");
                }
            }
        });

       sendRequestButton.setOnAction(event -> {
    boolean isRadioButtonSelected = group1.getSelectedToggle() != null;
    String selectedPlayer = isRadioButtonSelected ? ((RadioButton) group1.getSelectedToggle()).getText() : null;

    if (sendRequestButton.getText().equals("Send Request")) {
        if (isRadioButtonSelected) {
            sendRequest(selectedPlayer, "currentPlayerName"); 

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Waiting for Approval");
            alert.setHeaderText(null);
            alert.setContentText("Please wait for the other player's approval.");
            alert.showAndWait();
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("No Selection");
            alert.setHeaderText(null);
            alert.setContentText("Please select a player before sending a request.");
            alert.showAndWait();
        }
    }  else if (sendRequestButton.getText().equals("Accept")) {
        if (isRadioButtonSelected) {
            playGameBase gamePage = new playGameBase();
            Scene gameScene = new Scene(gamePage, 600, 400);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(gameScene);
            stage.show();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Accepted");
            alert.setHeaderText(null);
            alert.setContentText("Request Accepted");
            alert.showAndWait();
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("No Selection");
            alert.setHeaderText(null);
            alert.setContentText("Please select a request before accepting.");
            alert.showAndWait();
        }
    }
});


        
        cancelButton.setOnAction(event -> {
            if (cancelButton.getText().equals("Cancel")) {
                vBox.getChildren().forEach(node -> {
                    if (node instanceof RadioButton) {
                        ((RadioButton) node).setSelected(false);
                    }
                });
                showAlert("Cancel", "All selections have been cleared.");
            } else if (cancelButton.getText().equals("Decline")) {
                List<Node> nodesToRemove = new ArrayList<>();
                vBox.getChildren().forEach(currentNode -> {
                    if (currentNode instanceof RadioButton) {
                        RadioButton radioButton = (RadioButton) currentNode;
                        if (radioButton.isSelected()) {
                            nodesToRemove.add(currentNode);
                            radioButton.setSelected(false);
                        }
                    }
                });
                vBox.getChildren().removeAll(nodesToRemove);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Declined");
                alert.setHeaderText(null);
                alert.setContentText("Request is declined.");
                alert.showAndWait();
            }
        });
    }

    private void fetchActivePlayersFromServer(String currentPlayerName) {
    try {
        InetAddress serverAddress = InetAddress.getLocalHost();
        Socket socket = new Socket(serverAddress, 5013);
        DataInputStream input = new DataInputStream(socket.getInputStream());
        DataOutputStream output = new DataOutputStream(socket.getOutputStream());

        output.writeUTF("GetActivePlayers");

        int size = input.readInt();
        List<String> activePlayersNames = new ArrayList<>();

        for (int i = 0; i < size; i++) {
            activePlayersNames.add(input.readUTF());
        }

     
        if (currentPlayerName != null) {
            activePlayersNames.remove(currentPlayerName);
        }

      
        if (activePlayersNames.size() > MAX_RADIOBUTTONS) {
            activePlayersNames = activePlayersNames.subList(activePlayersNames.size() - MAX_RADIOBUTTONS, activePlayersNames.size());
        }

        vBox.getChildren().clear();
        for (String playerName : activePlayersNames) {
            if (vBox.getChildren().size() < MAX_RADIOBUTTONS) {
                RadioButton radioButton = new RadioButton(playerName);
                radioButton.setFont(new Font("System Bold Italic", 14.0));
                radioButton.setToggleGroup(group1);
                radioButton.setMaxWidth(vBox.getPrefWidth());
                radioButton.setWrapText(true);
                vBox.getChildren().add(radioButton);
            }
        }

        socket.close();
    } catch (IOException e) {
        e.printStackTrace();
    }
}
   public void sendRequest(String targetPlayer, String requestor) {
    try (Socket socket = new Socket("localhost", 5013);
         DataOutputStream output = new DataOutputStream(socket.getOutputStream());
         DataInputStream input = new DataInputStream(socket.getInputStream())) {
        
        
        output.writeUTF("SendRequest");
        output.writeUTF(targetPlayer);
        output.writeUTF(requestor);
        output.flush();
        
      
        String response = input.readUTF();
        System.out.println("Response from server: " + response);

        System.out.println("Request sent to server. Target: " + targetPlayer + ", Requestor: " + requestor);
        
    } catch (IOException e) {
        System.err.println("Error sending request: " + e.getMessage());
        e.printStackTrace();
    }
}

 private List<String> fetchGameRequestsFromServer() {
    List<String> gameRequests = new ArrayList<>();
    try (Socket socket = new Socket("localhost", 5013);
         DataInputStream input = new DataInputStream(socket.getInputStream());
         DataOutputStream output = new DataOutputStream(socket.getOutputStream())) {

        output.writeUTF("GetGameRequests");
        output.flush();
        System.out.println("Request sent to server: GetGameRequests");

        int count = input.readInt();
        System.out.println("Number of requests expected: " + count);

        for (int i = 0; i < count; i++) {
            String request = input.readUTF();
            System.out.println("Received request: " + request);
            gameRequests.add(request);
        }

    } catch (IOException e) {
        System.err.println("Error fetching game requests from server: " + e.getMessage());
        e.printStackTrace();
    }
    return gameRequests;
}






    

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private ToggleButton createToggleButton(double xDiraction, double yDiraction, String text) {
        ToggleButton button = new ToggleButton();
        button.setLayoutX(xDiraction);
        button.setLayoutY(yDiraction);
        button.setMnemonicParsing(false);
        button.setPrefHeight(BUTTON_HEIGHT);
        button.setPrefWidth(BUTTON_WIDTH);
        button.setText(text);
        button.setCursor(Cursor.HAND);
        button.getStyleClass().add("loginAndSignUp-button");
        return button;
    }

    private Button createButton(double xDiraction, double yDiraction, String text) {
        Button button = new Button();
        button.setLayoutX(xDiraction);
        button.setLayoutY(yDiraction);
        button.setMnemonicParsing(false);
        button.setPrefHeight(BUTTON_HEIGHT);
        button.setPrefWidth(BUTTON_WIDTH);
        button.setText(text);
        button.setCursor(Cursor.HAND);
        button.getStyleClass().add("loginAndSignUp-button");
        return button;
    }
}
