package xogameverone;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class XOGameVerOne extends Application {

    @Override
    public void start(Stage primaryStage) {
        selectModeBase root = new selectModeBase();
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setTitle("XO Game");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}