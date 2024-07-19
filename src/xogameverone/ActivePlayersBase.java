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
import javafx.scene.Cursor;
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
    private static final double BUTTON_WIDTH = 150.0;
    private static final double BUTTON_HEIGHT = 41.0;

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


        activePlayersButton=createToggleButton(141.0,-5.0, "Active Players");
        gameRequestbutton=createToggleButton(292.0,-5.0, "Requests");
        sendRequestButton=createButton(112.0,358.0, "Send Request");
        cancelButton=createButton(332.0,358.0, "Cancel");

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

        gameRequestbutton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
               // gameRequestbutton.setStyle("-fx-background-color: grey;");
             
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
                    //activePlayersButton.setStyle("-fx-background-color: grey;");
                   
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
            if (sendRequestButton.getText().equals("Send Request")){
                //here you can put your logic for send request
    showAlert("Waiting for Approval", "Please wait for the other player's approval.");
            }
            if (sendRequestButton.getText().equals("Accept")){
                //here you can put your logic after accept
             showAlert("ACCEPTED", "Accept");
            }
            
});
        cancelButton.setOnAction(event -> {
    vBox.getChildren().forEach(node -> {
        if (node instanceof RadioButton) {
            ((RadioButton) node).setSelected(false);
        }
    });
});

cancelButton.setOnAction(event -> {
    if (cancelButton.getText().equals("Cancel")){
                //here you can put your logic for cancel
    showAlert("cancel", "cancel");
            }
            if (cancelButton.getText().equals("Decline")){
                //here you can put your logic after "Decline"
             showAlert("Decline", "Decline");
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
 private ToggleButton createToggleButton(double xDiraction,double yDiraction, String text) {
        ToggleButton button = new ToggleButton();
        button.setLayoutX(xDiraction);
        button.setLayoutY(yDiraction);
       // button.setBlendMode(javafx.scene.effect.BlendMode.COLOR_BURN);
        button.setMnemonicParsing(false);
        button.setPrefHeight(BUTTON_HEIGHT);
        button.setPrefWidth(BUTTON_WIDTH);
        button.setText(text);
        button.setCursor(Cursor.HAND);
        button.getStyleClass().add("loginAndSignUp-button");
        return button;
    }
      private Button createButton(double xDiraction,double yDiraction, String text) {
        Button button = new Button();
        button.setLayoutX(xDiraction);
        button.setLayoutY(yDiraction);
        //button.setBlendMode(javafx.scene.effect.BlendMode.COLOR_BURN);
        button.setMnemonicParsing(false);
        button.setPrefHeight(BUTTON_HEIGHT);
        button.setPrefWidth(BUTTON_WIDTH);
        button.setText(text);
        button.setCursor(Cursor.HAND);
        button.getStyleClass().add("loginAndSignUp-button");
        return button;
    }
}
    

