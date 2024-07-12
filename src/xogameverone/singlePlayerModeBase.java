package xogameverone;

import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import static xogameverone.selectModeBase.selectMode;

public class singlePlayerModeBase extends AnchorPane 
{

    protected final ImageView backGroundPic;
    protected final Button esayBtn;
    protected final Button hardBtn;
    protected final Button exitBtn;

    private static final double BUTTON_WIDTH = 232.0;
    private static final double BUTTON_HEIGHT = 38.0;
    private static final double BUTTON_FONT_SIZE = 20.0;
    private static final String BUTTON_FONT_STYLE = "System Italic";
    private static final String BACKGROUND_IMAGE_PATH = "/images/background.jfif";
    
    public singlePlayerModeBase()
    {

        backGroundPic = new ImageView();
        
        esayBtn = createButton("Easy");
        esayBtn.setOnAction(this::handleEsayOrHardButtonAction);
        esayBtn.addEventHandler(ActionEvent.ACTION, (ActionEvent event) -> {
            selectMode = esayBtn.getText();      
        });
        
        hardBtn = createButton("Hard");
        hardBtn.setOnAction(this::handleEsayOrHardButtonAction);
        hardBtn.addEventHandler(ActionEvent.ACTION, (ActionEvent event) -> {
            selectMode = hardBtn.getText();
        }); 
        
        exitBtn = createButton("Exit");
        exitBtn.setOnAction(this::handleExitButtonAction);

        VBox buttonContainer = new VBox(10,hardBtn, esayBtn, exitBtn);
        buttonContainer.setAlignment(Pos.CENTER);
        buttonContainer.setPrefWidth(BUTTON_WIDTH);
        buttonContainer.setMaxWidth(BUTTON_WIDTH);
        setMinHeight(500.0);
        setMinWidth(600.0);
        setPrefHeight(400.0);
        setPrefWidth(600.0);
        setMaxHeight(Double.MAX_VALUE);
        setMaxWidth(Double.MAX_VALUE);

        backGroundPic.setPreserveRatio(false);
        backGroundPic.setId("backGround");
        Image image = new Image(getClass().getResource(BACKGROUND_IMAGE_PATH).toExternalForm());
        if (image.isError()) 
        {
            System.err.println("Error loading image: " + image.getException());
        } else 
        {
            backGroundPic.setImage(image);
        }
        backGroundPic.setCursor(Cursor.DEFAULT);

        // Bind image view dimensions to the pane dimensions
        backGroundPic.fitWidthProperty().bind(widthProperty());
        backGroundPic.fitHeightProperty().bind(heightProperty());

        // Set anchors to center the VBox
        AnchorPane.setTopAnchor(buttonContainer, 0.0);
        AnchorPane.setBottomAnchor(buttonContainer, 0.0);
        AnchorPane.setLeftAnchor(buttonContainer, 0.0);
        AnchorPane.setRightAnchor(buttonContainer, 0.0);

        getChildren().add(backGroundPic);
        getChildren().add(buttonContainer);

    }
    private void handleExitButtonAction(ActionEvent event) 
    {
        Parent selectModeBase = new selectModeBase();
        Scene selectModeScene = new Scene(selectModeBase);
        // Get the current stage
        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        stage.setScene(selectModeScene);
        stage.show();
    }//will be at interface later
    private void handleEsayOrHardButtonAction(ActionEvent event) 
    {
        Parent playGame = new playGameBase();
        Scene selectModeScene = new Scene(playGame);
        // Get the current stage
        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        stage.setScene(selectModeScene);
        stage.show();
    }
    
    private Button createButton(String text)
    {
        Button button = new Button();
        button.setAlignment(Pos.CENTER);
        button.setBlendMode(javafx.scene.effect.BlendMode.COLOR_BURN);
        button.setMnemonicParsing(false);
        button.setPrefHeight(BUTTON_HEIGHT);
        button.setPrefWidth(BUTTON_WIDTH);
        button.setText(text);
        button.setFont(new Font(BUTTON_FONT_STYLE, BUTTON_FONT_SIZE));
        button.setCursor(Cursor.HAND);
        return button;
    }
}

