package xogameverone;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
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
    protected final Text userNameLabel;
    protected final Text passWordLabel;
    protected final TextField userNameTextFiled;
    protected final PasswordField passwordTextField;
    private static final double BUTTON_WIDTH = 510.0;
    private static final double BUTTON_HEIGHT = 10.0;
    
    public LogIn() {

        imageView = new ImageView();
        imageView0 = new ImageView();
        imageView1 = new ImageView();
        region = new Region();
        dropShadow = new DropShadow();
        dropShadow0 = new DropShadow();
        gridPane = new GridPane();
        columnConstraints = new ColumnConstraints();
        columnConstraints0 = new ColumnConstraints();
        rowConstraints = new RowConstraints();
        rowConstraints0 = new RowConstraints();
        rowConstraints1 = new RowConstraints();
        
        

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
        imageView.setImage(new Image(getClass().getResource("/images/picpic.png").toExternalForm()));

        imageView0.setFitHeight(98.0);
        imageView0.setFitWidth(109.0);
        imageView0.setLayoutX(7.0);
        imageView0.setLayoutY(14.0);
        imageView0.setImage(new Image(getClass().getResource("/images/OIP-removebg-preview (1).png").toExternalForm()));

        imageView1.setFitHeight(98.0);
        imageView1.setFitWidth(118.0);
        imageView1.setLayoutX(468.0);
        imageView1.setLayoutY(14.0);
        imageView1.setImage(new Image(getClass().getResource("/images/OIP-removebg-preview (1).png").toExternalForm()));

//        region.setLayoutX(11.0);
//        region.setLayoutY(144.0);
//        region.setPrefHeight(243.0);
//        region.setPrefWidth(583.0);
//        region.setStyle("-fx-border-color: black;");
//
//        dropShadow.setHeight(9.13);
//        dropShadow.setRadius(19.8825);
//        dropShadow.setSpread(0.84);
//        dropShadow.setWidth(72.4);
//        region.setEffect(dropShadow);

        logIn=createButton(306.0,"Login");
       // logIn.setOnAction(this::handleLogin);
        
        signUp=createButton(354.0,"Sign Up");
        signUp.setOnAction(this::goToSignUpPage);

        dropShadow0.setHeight(72.4);
        dropShadow0.setRadius(31.25);
        dropShadow0.setSpread(0.19);
        dropShadow0.setWidth(54.6);
        logIn.setEffect(dropShadow0);

        gridPane.setLayoutX(23.0);
        gridPane.setLayoutY(155.0);
        gridPane.setPrefHeight(162.0);
        gridPane.setPrefWidth(554.0);

        columnConstraints.setHgrow(javafx.scene.layout.Priority.SOMETIMES);
        columnConstraints.setMaxWidth(95.33334350585938);
        columnConstraints.setMinWidth(10.0);
        columnConstraints.setPrefWidth(95.0);

        columnConstraints0.setHgrow(javafx.scene.layout.Priority.SOMETIMES);
        columnConstraints0.setMaxWidth(364.0);
        columnConstraints0.setMinWidth(10.0);
        columnConstraints0.setPrefWidth(259.0);

        rowConstraints.setMinHeight(10.0);
        rowConstraints.setPrefHeight(30.0);
        rowConstraints.setVgrow(javafx.scene.layout.Priority.SOMETIMES);

        rowConstraints0.setMinHeight(10.0);
        rowConstraints0.setPrefHeight(30.0);
        rowConstraints0.setVgrow(javafx.scene.layout.Priority.SOMETIMES);

        rowConstraints1.setMinHeight(10.0);
        rowConstraints1.setPrefHeight(30.0);
        rowConstraints1.setVgrow(javafx.scene.layout.Priority.SOMETIMES);
        
        userNameLabel = creatLabel (0 ,0,"User Name");  
        passWordLabel = creatLabel (1 ,0,"password");
        
        userNameTextFiled=creatTextField (164.0 ,"Please insert your name");
        passwordTextField= creatPasswordField (220.0 ,"Please enter your password name");

        

        gridPane.getColumnConstraints().add(columnConstraints);
        gridPane.getColumnConstraints().add(columnConstraints0);
        gridPane.getRowConstraints().add(rowConstraints);
        gridPane.getRowConstraints().add(rowConstraints0);
        gridPane.getRowConstraints().add(rowConstraints1);
        gridPane.getChildren().addAll(userNameLabel, passWordLabel);
        
        getChildren().addAll(imageView, imageView0, imageView1, region, logIn, signUp, gridPane, userNameTextFiled, passwordTextField);
        this.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
    }

    private void goToSignUpPage(ActionEvent ev) {
        Stage primaryStage = new Stage();
        SignUp signUpPane = new SignUp();
        Scene scene = new Scene(signUpPane, 600, 400);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Sign Up");
        primaryStage.show();
    }

//    private void handleLogin(ActionEvent event) {
//    String userName = userNameTextFiled.getText();
//    String password = passwordTextField.getText();
//    
//    try {
//        String loginResult = DAL.checkSignIn(userName, password);
//        
//        switch (loginResult) {
//            case "Logged in successfully":
//                DAL.login(userName, password);
//                System.out.println("Login successful");
//                // Perform any UI update or navigation here
//                break;
//            case "Password is incorrect":
//                passwordTextField.setText("");
//                userNameTextFiled.setText("");
//                passwordTextField.setPromptText("Password is incorrect");
//                break;
//            case "UserName is incorrect":
//                userNameTextFiled.setText("");
//                passwordTextField.setText("");
//                userNameTextFiled.setPromptText("Username is incorrect");
//                break;
//            case "This UserName is already signed-in":
//                userNameTextFiled.setText("");
//                passwordTextField.setText("");
//                userNameTextFiled.setPromptText("This UserName is already signed-in");
//                break;
//        }
//    } catch (SQLException e) {
//        e.printStackTrace();
//        // Handle database access error
//    }
//}
    
    private Text creatLabel (int row ,int col,String label){
        Text text =new Text();
        GridPane.setColumnIndex(text, col);
        GridPane.setRowIndex(text, row);
        text.setStrokeType(javafx.scene.shape.StrokeType.OUTSIDE);
        text.setStrokeWidth(0.0);
        text.setText(label);
        text.setTextAlignment(javafx.scene.text.TextAlignment.CENTER);
        text.setWrappingWidth(134.486328125);
        text.setFont(new Font("System Bold Italic", 18.0));
        return text;
    }
    private TextField creatTextField (double yDiraction ,String PromptText){
        TextField text =new TextField();
        text.setLayoutX(153.0);
        text.setLayoutY(yDiraction);
        text.setPrefHeight(32.0);
        text.setPrefWidth(417.0);
        text.setPromptText(PromptText);
        text.setStyle("-fx-background-color: ivory;");
        return text;
    }
    private PasswordField creatPasswordField (double yDiraction ,String PromptText){
        PasswordField text =new PasswordField();
        text.setLayoutX(153.0);
        text.setLayoutY(yDiraction);
        text.setPrefHeight(32.0);
        text.setPrefWidth(417.0);
        text.setPromptText(PromptText);
        text.setStyle("-fx-background-color: ivory;");
        return text;
    }
     private Button createButton(double yDiraction,String text)
    {
        Button button = new Button();
        button.setLayoutX(31.0);
        button.setLayoutY(yDiraction);
        button.setBlendMode(javafx.scene.effect.BlendMode.COLOR_BURN);
        button.setMnemonicParsing(false);
        button.setPrefHeight(BUTTON_HEIGHT);
        button.setPrefWidth(BUTTON_WIDTH);
        button.setText(text);
        button.setCursor(Cursor.HAND);
        button.getStyleClass().add("loginAndSignUp-button");
        
        return button;
    }

}
