package xogameverone;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Arrays;
import java.util.List;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
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

    public ActivePlayersBase() throws IOException {

        imageView = new ImageView();
        activePlayersButton = new ToggleButton();
        dropShadow = new DropShadow();
        gameRequestbutton = new ToggleButton();
        dropShadow0 = new DropShadow();
        sendRequestButton = new Button();
        dropShadow1 = new DropShadow();
        cancelButton = new Button();
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



        activePlayersButton.setLayoutX(141.0);
        activePlayersButton.setLayoutY(-5.0);
        activePlayersButton.setMnemonicParsing(false);
        activePlayersButton.setPrefHeight(41.0);
        activePlayersButton.setPrefWidth(151.0);
        activePlayersButton.setSelected(true);
        activePlayersButton.setStyle("-fx-background-color: ivory; -fx-border-color: black;");
        activePlayersButton.setText("Active Players");
        activePlayersButton.setFont(new Font("System Bold Italic", 14.0));
        dropShadow.setSpread(0.65);
        activePlayersButton.setEffect(dropShadow);

        gameRequestbutton.setLayoutX(292.0);
        gameRequestbutton.setLayoutY(-5.0);
        gameRequestbutton.setMnemonicParsing(false);
        gameRequestbutton.setPrefHeight(41.0);
        gameRequestbutton.setPrefWidth(160.0);
        gameRequestbutton.setStyle("-fx-background-color: ivory; -fx-border-color: black;");
        gameRequestbutton.setText("Game Requests");
        gameRequestbutton.setFont(new Font("System Bold Italic", 14.0));
        dropShadow0.setSpread(0.58);
        gameRequestbutton.setEffect(dropShadow0);

        sendRequestButton.setLayoutX(112.0);
        sendRequestButton.setLayoutY(358.0);
        sendRequestButton.setMnemonicParsing(false);
        sendRequestButton.setPrefHeight(41.0);
        sendRequestButton.setPrefWidth(190.0);
        sendRequestButton.setStyle("-fx-background-color: gold;");
        sendRequestButton.setText("Send Request");
        sendRequestButton.setFont(new Font("System Bold Italic", 14.0));
        dropShadow1.setSpread(0.36);
        sendRequestButton.setEffect(dropShadow1);

        cancelButton.setLayoutX(332.0);
        cancelButton.setLayoutY(358.0);
        cancelButton.setMnemonicParsing(false);
        cancelButton.setPrefHeight(41.0);
        cancelButton.setPrefWidth(200.0);
        cancelButton.setStyle("-fx-background-color: lavender;");
        cancelButton.setText("Cancel");
        cancelButton.setFont(new Font("System Bold Italic", 14.0));
        dropShadow2.setSpread(0.45);
        cancelButton.setEffect(dropShadow2);

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

        gameRequestbutton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                gameRequestbutton.setStyle("-fx-background-color: grey;");
             
                List<String> gameRequests = Arrays.asList("Player1 Request", "Player2 Request");
                vBox.getChildren().clear();
                for (String playerName : gameRequests) {
                    RadioButton radioButton = new RadioButton(playerName);
                    radioButton.setFont(new Font("System Bold Italic", 14.0));
                    radioButton.setToggleGroup(group1);
                    vBox.getChildren().add(radioButton);
                }
                sendRequestButton.setText("Accept");
                cancelButton.setText("Decline");
                
                
            }
        });
        
        

        activePlayersButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (activePlayersButton.isSelected()) {
                    activePlayersButton.setStyle("-fx-background-color: grey;");
                   
                    List<String> activePlayersNames = Arrays.asList("Player1", "Player2", "Player3");
                    vBox.getChildren().clear();
                    for (String playerName : activePlayersNames) {
                        RadioButton radioButton = new RadioButton(playerName);
                        radioButton.setFont(new Font("System Bold Italic", 14.0));
                        radioButton.setToggleGroup(group1);
                        vBox.getChildren().add(radioButton);
                    }
                    sendRequestButton.setText("Send Request");
                    cancelButton.setText("Cancel");
                } else {
                    sendRequestButton.setText("Accept");
                    cancelButton.setText("Decline");
                }
            }
        });
        sendRequestButton.setOnAction(event -> {
    Alert alert = new Alert(Alert.AlertType.INFORMATION);
    alert.setTitle("Waiting for Approval");
    alert.setHeaderText(null);
    alert.setContentText("Please wait for the other player's approval.");
    alert.showAndWait();
});
        cancelButton.setOnAction(event -> {
    vBox.getChildren().forEach(node -> {
        if (node instanceof RadioButton) {
            ((RadioButton) node).setSelected(false);
        }
    });
});

cancelButton.setOnAction(event -> {
    if (cancelButton.getText().equals("Declined")) {
        showAlert("Game Declined", "The sender has declined the game.");
    }
});

//sendRequestButton.setOnAction(event -> {
//   if (sendRequestButton.getText().equals("Accept")) {
//        openGamePage(primaryStage);
//    }
//});




      
        InetAddress serverAddress = InetAddress.getLocalHost();
        Socket socket = new Socket(serverAddress, 5013);
        DataInputStream input = new DataInputStream(socket.getInputStream());
        DataOutputStream output = new DataOutputStream(socket.getOutputStream());

        output.writeUTF("PlayerName");

        //String response = input.readUTF();
        //System.out.println("Received from server: " + response);

        //String playersList = input.readUTF();
        //System.out.println("Current players: " + playersList);

        output.writeUTF("/request ReceiverName Let's play!");

      //  String requestResponse = input.readUTF();
        //System.out.println("Request response: " + requestResponse);

        socket.close();}

 private void showAlert(String title, String message) {
    Alert alert = new Alert(Alert.AlertType.INFORMATION);
    alert.setTitle(title);
    alert.setHeaderText(null); 
    alert.setContentText(message); 
    alert.showAndWait();
}
}
    

