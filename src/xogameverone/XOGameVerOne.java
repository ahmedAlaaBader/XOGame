/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xogameverone;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author ahmed alaa
 */
public class XOGameVerOne extends Application {
    
    @Override
   public void start(Stage primaryStage) {
        selectModeBase root = new selectModeBase();
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setTitle("XO Game");
       /* Label mainLabel = new Label("Welcome to the Main Page!");
        Button btnToSecondPage = new Button("Go to Next Page");
        btnToSecondPage.setOnAction(event -> showSecondPage(primaryStage));

        VBox mainLayout = new VBox(20);
        mainLayout.setPadding(new Insets(10));
        mainLayout.getChildren().addAll(mainLabel, btnToSecondPage);

        Scene mainScene = new Scene(mainLayout, 400, 300);
        primaryStage.setScene(mainScene);*/
        primaryStage.show();
    }
   /* private void showSecondPage(Stage stage) {
        // Second Page Components
        Label secondLabel = new Label("This is the Second Page");
        Button btnBack = new Button("Back to Main Page");
        btnBack.setOnAction(event -> showMainPage(stage));

        VBox secondLayout = new VBox(20);
        secondLayout.setPadding(new Insets(10));
        secondLayout.getChildren().addAll(secondLabel, btnBack);

        Scene secondScene = new Scene(secondLayout, 400, 300);
        stage.setScene(secondScene);
    }
    private void showMainPage(Stage stage) {
        // Main Page Components
        Label mainLabel = new Label("Welcome to the Main Page!");
        Button btnToSecondPage = new Button("Go to Next Page");
        btnToSecondPage.setOnAction(event -> showSecondPage(stage));

        VBox mainLayout = new VBox(20);
        mainLayout.setPadding(new Insets(10));
        mainLayout.getChildren().addAll(mainLabel, btnToSecondPage);

        Scene mainScene = new Scene(mainLayout, 400, 300);
        stage.setScene(mainScene);
    }*/
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

   
    
}
