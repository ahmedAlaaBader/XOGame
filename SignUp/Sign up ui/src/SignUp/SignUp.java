package uiregister;

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
import sun.security.util.Password;
import TheServer.LogIn;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;

public class SignUp extends AnchorPane {

    protected final ImageView imageView;
    protected final ImageView imageView0;
    protected final ImageView imageView1;
    protected final Region region;
    protected final DropShadow dropShadow;
    protected final Button SignUp;
    protected final DropShadow dropShadow0;
    protected final GridPane gridPane;
    protected final ColumnConstraints columnConstraints;
    protected final ColumnConstraints columnConstraints0;
    protected final RowConstraints rowConstraints;
    protected final RowConstraints rowConstraints0;
    protected final RowConstraints rowConstraints1;
    protected final Text username;
    protected final Text Email;
    protected final Text text;
    protected final TextField usernametext;
    protected final TextField emailtext;
    protected final PasswordField passwordtext;

    public SignUp() {

        imageView = new ImageView();
        imageView0 = new ImageView();
        imageView1 = new ImageView();
        region = new Region();
        dropShadow = new DropShadow();
        SignUp = new Button();
        dropShadow0 = new DropShadow();
        gridPane = new GridPane();
        columnConstraints = new ColumnConstraints();
        columnConstraints0 = new ColumnConstraints();
        rowConstraints = new RowConstraints();
        rowConstraints0 = new RowConstraints();
        rowConstraints1 = new RowConstraints();
        username = new Text();
        Email = new Text();
        text = new Text();
        usernametext = new TextField();
        emailtext = new TextField();
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

        SignUp.setLayoutX(31.0);
        SignUp.setLayoutY(333.0);
        SignUp.setMnemonicParsing(false);
        SignUp.setPrefHeight(39.0);
        SignUp.setPrefWidth(546.0);
        SignUp.setStyle("-fx-background-color: gold; -fx-border-color: black;");
        SignUp.setText("Sign Up");
        SignUp.setFont(new Font("System Bold Italic", 16.0));
        SignUp.setCursor(Cursor.CLOSED_HAND);
        SignUp.setOnAction(this::gobackToMainPage);

        dropShadow0.setHeight(72.4);
        dropShadow0.setRadius(31.25);
        dropShadow0.setSpread(0.19);
        dropShadow0.setWidth(54.6);
        SignUp.setEffect(dropShadow0);

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

        GridPane.setColumnIndex(Email, 1);
        GridPane.setRowIndex(Email, 1);
        Email.setStrokeType(javafx.scene.shape.StrokeType.OUTSIDE);
        Email.setStrokeWidth(0.0);
        Email.setText("Email");
        Email.setTextAlignment(javafx.scene.text.TextAlignment.CENTER);
        Email.setWrappingWidth(131.48959350585938);
        Email.setFont(new Font("System Bold Italic", 18.0));

        GridPane.setColumnIndex(text, 1);
        GridPane.setRowIndex(text, 2);
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

        emailtext.setLayoutX(153.0);
        emailtext.setLayoutY(220.0);
        emailtext.setPrefHeight(32.0);
        emailtext.setPrefWidth(417.0);
        emailtext.setPromptText("please enter your email");
        emailtext.setStyle("-fx-background-color: ivory;");

        passwordtext.setLayoutX(153.0);
        passwordtext.setLayoutY(276.0);
        passwordtext.setPrefHeight(32.0);
        passwordtext.setPrefWidth(417.0);
        passwordtext.setPromptText("please enter a password");
        passwordtext.setStyle("-fx-background-color: ivory;");

        getChildren().add(imageView);
        getChildren().add(imageView0);
        getChildren().add(imageView1);
        getChildren().add(region);
        getChildren().add(SignUp);
        gridPane.getColumnConstraints().add(columnConstraints);
        gridPane.getColumnConstraints().add(columnConstraints0);
        gridPane.getRowConstraints().add(rowConstraints);
        gridPane.getRowConstraints().add(rowConstraints0);
        gridPane.getRowConstraints().add(rowConstraints1);
        gridPane.getChildren().add(username);
        gridPane.getChildren().add(Email);
        gridPane.getChildren().add(text);
        getChildren().add(gridPane);
        getChildren().add(usernametext);
        getChildren().add(emailtext);
        getChildren().add(passwordtext);
        
       
        
        SignUp.setOnAction(e->{
        String username=usernametext.getText();
    String email= emailtext.getText();
String password =passwordtext.getText();

//PlayerHandler.logIn(email,password);
});

    }
     private void gobackToMainPage(ActionEvent ev) {
       while ( usernametext.getText().isEmpty()|| emailtext.getText().isEmpty()||passwordtext.getText().isEmpty()){
           Alert alert = new Alert(Alert.AlertType.INFORMATION,"cant sign up with empty fields");
       }
       
       
        Stage primaryStage = new Stage();
        // Create an instance of your custom Login pane
        LogIn signupPan = new LogIn();

        // Create a scene and set the Login pane as its root
        Scene scene = new Scene(signupPan, 600, 400);

        // Set the scene on the primary stage and show it
        primaryStage.setScene(scene);
        primaryStage.setTitle("Login"); // Set your desired title here
        primaryStage.show();
    }  
   
}
