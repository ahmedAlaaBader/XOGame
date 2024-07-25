package xogameverone;

import java.util.ArrayList;
import java.util.List;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Parent;
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
import util.ServerConnector;

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

    public ActivePlayersBase() {
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
        imageView.setImage(new Image("/images/background2.jfif"));

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
//     List<String> gameRequests = new ArrayList<>();
//    String player = new String();
////    player = fetchGameRequestsFromServer();
//    String[] moveData = player.split(",");
//    String player1 = moveData[0];
//    String player2 = moveData[1];
//    if (LogIn.userName.equals(player2)) {
//                    gameRequests.add(player1 + " wants to play with you");
//        }
//    if (gameRequests.size() > MAX_RADIOBUTTONS) {
//            gameRequests = gameRequests.subList(gameRequests.size() - MAX_RADIOBUTTONS, gameRequests.size());
//        }
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
                    String opponentResponse = sendRequest(selectedPlayer);
                    if ("AcceptRequest".equals(opponentResponse)) {
                        goToGame(event);
                    } else if ("RefuseRequest".equals(opponentResponse)) {
                        showAlert("Request Response", "Request Refused");
                    }
                } else {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("No Selection");
                    alert.setHeaderText(null);
                    alert.setContentText("Please select a player before sending a request.");
                    alert.showAndWait();
                }
            } else if (sendRequestButton.getText().equals("Accept")) {
                if (isRadioButtonSelected) {
//                    playGameBase gamePage = new playGameBase();
//                    Scene gameScene = new Scene(gamePage, 600, 400);
//                    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
//                    stage.setScene(gameScene);
//                    stage.show();
//
//                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
//                    alert.setTitle("Accepted");
//                    alert.setHeaderText(null);
//                    alert.setContentText("Request Accepted");
//                    alert.showAndWait();
                    goToGame(event);
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
                            ServerConnector.connect().refuseRequest(radioButton.getText());
                            System.out.println("Refuse: " + radioButton.getText());
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

        List<String> activePlayersNames = new ArrayList<>();
        activePlayersNames = ServerConnector.connect().getActivePlayer();

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

    }

    public String sendRequest(String targetPlayer) {
        System.out.println("Request sent to server. Target: " + targetPlayer);
        String opponentResponse = ServerConnector.connect().requestForPlaying(targetPlayer);
        System.out.println("Response from server: " + opponentResponse);

        return opponentResponse;
    }

    // private List<String> fetchGameRequestsFromServer() {
    private List<String> fetchGameRequestsFromServer() {
        return ServerConnector.connect().getGameRequests();
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

    private void goToGame(ActionEvent ev) {
        System.out.println("Start an Online game");

        playGameBase playGameBase = new playGameBase();
        Scene selectModeScene = new Scene(playGameBase);
        // Get the current stage
        Stage stage = (Stage) ((Button) ev.getSource()).getScene().getWindow();
        stage.setScene(selectModeScene);
        stage.setOnShowing(event -> {
            playGameBase.initializeOnline();
        });
        stage.show();
    }
}
