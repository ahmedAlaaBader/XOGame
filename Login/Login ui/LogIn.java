package TheServer;

import javafx.event.ActionEvent;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.RowConstraints;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import uiregister.SignUp;

public class LogIn extends AnchorPane {

    protected final ImageView imageView;
    protected final ImageView imageView0;
    protected final ImageView imageView1;
    protected final Region region;
    protected final DropShadow dropShadow;
    protected final Button logIn;
    protected final Button signUp;
    protected final DropShadow dropShadow0;
    protected final GridPane gridPane;
    protected final ColumnConstraints columnConstraints;
    protected final ColumnConstraints columnConstraints0;
    protected final RowConstraints rowConstraints;
    protected final RowConstraints rowConstraints0;
    protected final RowConstraints rowConstraints1;
    protected final Text username;
    protected final Text text;
    protected final TextField usernametext;
    protected final PasswordField passwordtext;

    public LogIn() {

        imageView = new ImageView();
        imageView0 = new ImageView();
        imageView1 = new ImageView();
        region = new Region();
        dropShadow = new DropShadow();
        logIn = new Button();
        signUp = new Button();
        dropShadow0 = new DropShadow();
        gridPane = new GridPane();
        columnConstraints = new ColumnConstraints();
        columnConstraints0 = new ColumnConstraints();
        rowConstraints = new RowConstraints();
        rowConstraints0 = new RowConstraints();
        rowConstraints1 = new RowConstraints();
        username = new Text();
        text = new Text();
        usernametext = new TextField();
        passwordtext = new PasswordField();

        setMaxHeight(USE_PREF_SIZE);
        setMaxWidth(USE_PREF_SIZE);
        setMinHeight(USE_PREF_SIZE);
        setMinWidth(USE_PREF_SIZE);
        setPrefHeight(400.0);
        setPrefWidth(600.0);

        imageView.setFitHeight(408.0);
        imageView.setFitWidth(607.0);
        imageView.setLayoutX(-3.0);
        imageView.setLayoutY(-3.0);
        imageView.setImage(new Image(getClass().getResource("/imag/picpic.png").toExternalForm()));

        imageView0.setFitHeight(98.0);
        imageView0.setFitWidth(109.0);
        imageView0.setLayoutX(7.0);
        imageView0.setLayoutY(14.0);
        imageView0.setImage(new Image(getClass().getResource("/imag/OIP-removebg-preview (1).png").toExternalForm()));

        imageView1.setFitHeight(98.0);
        imageView1.setFitWidth(118.0);
        imageView1.setLayoutX(468.0);
        imageView1.setLayoutY(14.0);
        imageView1.setImage(new Image(getClass().getResource("/imag/OIP-removebg-preview (1).png").toExternalForm()));

        region.setLayoutX(11.0);
        region.setLayoutY(144.0);
        region.setPrefHeight(243.0);
        region.setPrefWidth(583.0);
        region.setStyle("-fx-border-color: black;");

        dropShadow.setHeight(9.13);
        dropShadow.setRadius(19.8825);
        dropShadow.setSpread(0.84);
        dropShadow.setWidth(72.4);
        region.setEffect(dropShadow);
        
        logIn.setLayoutX(31.0);
        logIn.setLayoutY(290.0);
        logIn.setMnemonicParsing(false);
        logIn.setPrefHeight(39.0);
        logIn.setPrefWidth(546.0);
        logIn.setStyle("-fx-background-color: gold; -fx-border-color: black;");
        logIn.setText("log In");
        logIn.setFont(new Font("System Bold Italic", 16.0));
        logIn.setCursor(Cursor.HAND);


        signUp.setLayoutX(31.0);
        signUp.setLayoutY(333.0);
        signUp.setMnemonicParsing(false);
        signUp.setPrefHeight(39.0);
        signUp.setPrefWidth(546.0);
        signUp.setStyle("-fx-background-color: gold; -fx-border-color: black;");
        signUp.setText("Sign Up");
        signUp.setFont(new Font("System Bold Italic", 16.0));
        signUp.setCursor(Cursor.HAND);
        signUp.setOnAction(this::goToSignUpPage);
        
        dropShadow0.setHeight(72.4);
        dropShadow0.setRadius(31.25);
        dropShadow0.setSpread(0.19);
        dropShadow0.setWidth(54.6);
        logIn.setEffect(dropShadow0);

        gridPane.setLayoutX(23.0);
        gridPane.setLayoutY(155.0);
        gridPane.setPrefHeight(162.0);
        gridPane.setPrefWidth(139.0);

        columnConstraints.setHgrow(javafx.scene.layout.Priority.SOMETIMES);
        columnConstraints.setMaxWidth(95.33334350585938);
        columnConstraints.setMinWidth(0.0);
        columnConstraints.setPrefWidth(0.0);

        columnConstraints0.setHgrow(javafx.scene.layout.Priority.SOMETIMES);
        columnConstraints0.setMaxWidth(200.0);
        columnConstraints0.setMinWidth(10.0);
        columnConstraints0.setPrefWidth(200.0);

        rowConstraints.setMinHeight(10.0);
        rowConstraints.setPrefHeight(30.0);
        rowConstraints.setVgrow(javafx.scene.layout.Priority.SOMETIMES);

        rowConstraints0.setMinHeight(10.0);
        rowConstraints0.setPrefHeight(30.0);
        rowConstraints0.setVgrow(javafx.scene.layout.Priority.SOMETIMES);

        rowConstraints1.setMinHeight(10.0);
        rowConstraints1.setPrefHeight(30.0);
        rowConstraints1.setVgrow(javafx.scene.layout.Priority.SOMETIMES);

        GridPane.setColumnIndex(username, 1);
        username.setStrokeType(javafx.scene.shape.StrokeType.OUTSIDE);
        username.setStrokeWidth(0.0);
        username.setText("User Name");
        username.setTextAlignment(javafx.scene.text.TextAlignment.CENTER);
        username.setWrappingWidth(134.486328125);
        username.setFont(new Font("System Bold Italic", 18.0));

        GridPane.setColumnIndex(text, 1);
        GridPane.setRowIndex(text, 1);
        text.setStrokeType(javafx.scene.shape.StrokeType.OUTSIDE);
        text.setStrokeWidth(0.0);
        text.setText("Password");
        text.setTextAlignment(javafx.scene.text.TextAlignment.CENTER);
        text.setWrappingWidth(126.22720336914062);
        text.setFont(new Font("System Bold Italic", 18.0));

        usernametext.setLayoutX(153.0);
        usernametext.setLayoutY(169.0);
        usernametext.setPrefHeight(32.0);
        usernametext.setPrefWidth(417.0);
        usernametext.setPromptText("please insert your name");
        usernametext.setStyle("-fx-background-color: ivory;");

        passwordtext.setLayoutX(153.0);
        passwordtext.setLayoutY(225.0);
        passwordtext.setPrefHeight(32.0);
        passwordtext.setPrefWidth(417.0);
        passwordtext.setPromptText("please enter a password");
        passwordtext.setStyle("-fx-background-color: ivory;");

        getChildren().add(imageView);
        getChildren().add(imageView0);
        getChildren().add(imageView1);
        getChildren().add(region);
        getChildren().add(logIn);
        getChildren().add(signUp);
        gridPane.getColumnConstraints().add(columnConstraints);
        gridPane.getColumnConstraints().add(columnConstraints0);
        gridPane.getRowConstraints().add(rowConstraints);
        gridPane.getRowConstraints().add(rowConstraints0);
        gridPane.getRowConstraints().add(rowConstraints1);
        gridPane.getChildren().add(username);
        gridPane.getChildren().add(text);
        getChildren().add(gridPane);
        getChildren().add(usernametext);
        getChildren().add(passwordtext);

        logIn.setOnAction(e -> {
            String userName = usernametext.getText();
            String password = passwordtext.getText();
            // PlayerHandler.logIn(email,password);
        });
    }
    private void goToSignUpPage(ActionEvent ev) {
        Stage primaryStage = new Stage();
        // Create an instance of your custom Login pane
        SignUp signupPan = new SignUp();

        // Create a scene and set the Login pane as its root
        Scene scene = new Scene(signupPan, 600, 400);

        // Set the scene on the primary stage and show it
        primaryStage.setScene(scene);
        primaryStage.setTitle("Login"); // Set your desired title here
        primaryStage.show();
    }

   
}
