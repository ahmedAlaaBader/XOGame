package xogameverone;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import static java.lang.Thread.sleep;
//import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextInputDialog;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import video_palyer.VideoPalyer;
import javafx.util.Duration;



public  class playGameBase extends BorderPane {
    
    protected final GridPane gridPane;
    protected final ColumnConstraints columnConstraints;
    protected final ColumnConstraints columnConstraints0;
    protected final ColumnConstraints columnConstraints1;
    protected final RowConstraints rowConstraints;
    protected final RowConstraints rowConstraints0;
    protected final RowConstraints rowConstraints1;
    protected final Button btn00;
    protected final Button btn10;
    protected final Button btn20;
    protected final Button btn01;
    protected final Button btn11;
    protected final Button btn21;
    protected final Button btn02;
    protected final Button btn12;
    protected final Button btn22;
    protected final GridPane gridPane0;
    protected final GridPane gridPane1;
    protected final ColumnConstraints columnConstraints2;
    protected final ColumnConstraints columnConstraints3;
    protected final ColumnConstraints columnConstraints4;
    protected final RowConstraints rowConstraints2;
    protected final RowConstraints rowConstraints3;
    protected final Text text;
    protected final Text text0;
    protected final Text text1;
    protected final Text text2;
    protected final Text xScoreTxt;
    protected final Text oScoreTxt;
    protected final Button exitBtn;
    protected final Button recordeBtn;
    protected final ColorAdjust colorAdjust;
    private String currentPlayer = "X"; 
    private final Button[][] btn;
    private int counter = 0;
    private int playerXScore = 0;
    private int playerOScore = 0;
    VideoPalyer playVideo = new VideoPalyer();
    String winVideoPath = "file:///F:/java%20labs/finalClient/src/videos/winnerVideo.mp4";
    String loseVideoPath = "file:///F:/java%20labs/finalClient/src/videos/loseVideo.mp4";
    String sound = "file:///F:/java%20labs/finalClient/src/Sounds/ui-click-43196.mp3";
  
    private static final Random random = new Random();
    private final List<String> recordedMoves = new ArrayList<>();
    private List<String> recordedMovess = new ArrayList<>();
    private int replayIndex = 0;
    Boolean flag = Boolean.FALSE;
    Boolean comMoveFlag = Boolean.FALSE;
    int count = 1;
    String recordName = "F:\\java labs\\finalClient\\src\\recording\\game";
    String recordTxt = "moves.txt";
    
    public playGameBase() {
        showAlert(Alert.AlertType.CONFIRMATION, "Record", "Do you want to record game");
        gridPane = new GridPane();
        columnConstraints = new ColumnConstraints();
        columnConstraints0 = new ColumnConstraints();
        columnConstraints1 = new ColumnConstraints();
        rowConstraints = new RowConstraints();
        rowConstraints0 = new RowConstraints();
        rowConstraints1 = new RowConstraints();
        btn00 = new Button();
        btn10 = new Button();
        btn20 = new Button();
        btn01 = new Button();
        btn11 = new Button();
        btn21 = new Button();
        btn02 = new Button();
        btn12 = new Button();
        btn22 = new Button();
        gridPane0 = new GridPane();
        gridPane1 = new GridPane();
        columnConstraints2 = new ColumnConstraints();
        columnConstraints3 = new ColumnConstraints();
        columnConstraints4 = new ColumnConstraints();
        rowConstraints2 = new RowConstraints();
        rowConstraints3 = new RowConstraints();
        text = new Text();
        text0 = new Text();
        text1 = new Text();
        text2 = new Text();
        xScoreTxt = new Text();
        oScoreTxt = new Text();
        exitBtn = new Button();
        recordeBtn = new Button();
        colorAdjust = new ColorAdjust();

        btn = new Button[][] {
            {btn00, btn01, btn02},
            {btn10, btn11, btn12},
            {btn20, btn21, btn22}
        };
        
        setMaxHeight(USE_PREF_SIZE);
        setMaxWidth(USE_PREF_SIZE);
        setMinHeight(USE_PREF_SIZE);
        setMinWidth(USE_PREF_SIZE);
        setPrefHeight(400.0);
        setPrefWidth(600.0);
        setStyle("-fx-background-color: #8023dd;");

        BorderPane.setAlignment(gridPane, javafx.geometry.Pos.CENTER);
        gridPane.setMaxHeight(USE_PREF_SIZE);
        gridPane.setMaxWidth(USE_PREF_SIZE);
        gridPane.setMinHeight(USE_PREF_SIZE);
        gridPane.setPrefHeight(200.0);
        gridPane.setPrefWidth(274.0);

        columnConstraints.setHgrow(javafx.scene.layout.Priority.SOMETIMES);
        columnConstraints.setMinWidth(10.0);
        columnConstraints.setPrefWidth(100.0);

        columnConstraints0.setHgrow(javafx.scene.layout.Priority.SOMETIMES);
        columnConstraints0.setMinWidth(10.0);
        columnConstraints0.setPrefWidth(100.0);

        columnConstraints1.setHgrow(javafx.scene.layout.Priority.SOMETIMES);
        columnConstraints1.setMinWidth(10.0);
        columnConstraints1.setPrefWidth(100.0);

        rowConstraints.setMinHeight(10.0);
        rowConstraints.setPrefHeight(30.0);
        rowConstraints.setVgrow(javafx.scene.layout.Priority.SOMETIMES);

        rowConstraints0.setMinHeight(10.0);
        rowConstraints0.setPrefHeight(30.0);
        rowConstraints0.setVgrow(javafx.scene.layout.Priority.SOMETIMES);

        rowConstraints1.setMinHeight(10.0);
        rowConstraints1.setPrefHeight(30.0);
        rowConstraints1.setVgrow(javafx.scene.layout.Priority.SOMETIMES);

        handleButtonProperties(btn00);
        GridPane.setColumnIndex(btn00, 0);
        GridPane.setRowIndex(btn00, 0);

        GridPane.setColumnIndex(btn10, 1);
        GridPane.setRowIndex(btn10, 0);
        handleButtonProperties(btn10);

        GridPane.setColumnIndex(btn20, 2);
        GridPane.setRowIndex(btn20, 0);
        handleButtonProperties(btn20);

        GridPane.setColumnIndex(btn01, 0);
        GridPane.setRowIndex(btn01, 1);
        handleButtonProperties(btn01);

        GridPane.setColumnIndex(btn11, 1);
        GridPane.setRowIndex(btn11, 1);
        handleButtonProperties(btn11);

        GridPane.setColumnIndex(btn21, 2);
        GridPane.setRowIndex(btn21, 1);
        handleButtonProperties(btn21);

        GridPane.setColumnIndex(btn02, 0);
        GridPane.setRowIndex(btn02, 2);
        handleButtonProperties(btn02);

        GridPane.setColumnIndex(btn12, 1);
        GridPane.setRowIndex(btn12, 2);
        handleButtonProperties(btn12);

        GridPane.setColumnIndex(btn22, 2);
        GridPane.setRowIndex(btn22, 2);
        handleButtonProperties(btn22);

         
        setCenter(gridPane);
        

        BorderPane.setAlignment(gridPane0, javafx.geometry.Pos.CENTER);
        gridPane0.setPrefHeight(112.0);
        gridPane0.setPrefWidth(600.0);

        columnConstraints2.setHgrow(javafx.scene.layout.Priority.SOMETIMES);
        columnConstraints2.setMinWidth(10.0);
        columnConstraints2.setPrefWidth(100.0);

        columnConstraints3.setHgrow(javafx.scene.layout.Priority.SOMETIMES);
        columnConstraints3.setMinWidth(10.0);
        columnConstraints3.setPrefWidth(100.0);

        columnConstraints4.setHgrow(javafx.scene.layout.Priority.SOMETIMES);
        columnConstraints4.setMinWidth(10.0);
        columnConstraints4.setPrefWidth(100.0);

        rowConstraints2.setMinHeight(10.0);
        rowConstraints2.setPrefHeight(30.0);
        rowConstraints2.setVgrow(javafx.scene.layout.Priority.SOMETIMES);

        rowConstraints3.setMinHeight(10.0);
        rowConstraints3.setPrefHeight(30.0);
        rowConstraints3.setVgrow(javafx.scene.layout.Priority.SOMETIMES);

        GridPane.setRowIndex(text, 1);
        text.setStrokeType(javafx.scene.shape.StrokeType.OUTSIDE);
        text.setStrokeWidth(0.0);
        text.setText("score");
        text.setTextAlignment(javafx.scene.text.TextAlignment.CENTER);
        text.setWrappingWidth(200.13671875);
        GridPane.setMargin(text, new Insets(0.0));
        text.setFont(new Font(30.0));

        text0.setStrokeType(javafx.scene.shape.StrokeType.OUTSIDE);
        text0.setStrokeWidth(0.0);
        text0.setText("player");
        text0.setTextAlignment(javafx.scene.text.TextAlignment.CENTER);
        text0.setWrappingWidth(201.13671875);
        text0.setFont(new Font(30.0));
        GridPane.setMargin(text0, new Insets(0.0));

        GridPane.setColumnIndex(text1, 1);
        text1.setStrokeType(javafx.scene.shape.StrokeType.OUTSIDE);
        text1.setStrokeWidth(0.0);
        text1.setText("x");
        text1.setTextAlignment(javafx.scene.text.TextAlignment.CENTER);
        text1.setWrappingWidth(198.13671875);
        text1.setFont(new Font(30.0));

        GridPane.setColumnIndex(text2, 2);
        text2.setStrokeType(javafx.scene.shape.StrokeType.OUTSIDE);
        text2.setStrokeWidth(0.0);
        text2.setText("o");
        text2.setTextAlignment(javafx.scene.text.TextAlignment.CENTER);
        text2.setWrappingWidth(198.13671875);
        text2.setFont(new Font(30.0));

        GridPane.setColumnIndex(xScoreTxt, 1);
        GridPane.setRowIndex(xScoreTxt, 1);
        xScoreTxt.setStrokeType(javafx.scene.shape.StrokeType.OUTSIDE);
        xScoreTxt.setStrokeWidth(0.0);
        xScoreTxt.setTextAlignment(javafx.scene.text.TextAlignment.CENTER);
        xScoreTxt.setWrappingWidth(198.13671875);
        xScoreTxt.setFont(new Font(30.0));
        xScoreTxt.setText(playerXScore+"");
        
        GridPane.setColumnIndex(oScoreTxt, 2);
        GridPane.setRowIndex(oScoreTxt, 1);
        oScoreTxt.setStrokeType(javafx.scene.shape.StrokeType.OUTSIDE);
        oScoreTxt.setStrokeWidth(0.0);
        oScoreTxt.setTextAlignment(javafx.scene.text.TextAlignment.CENTER);
        oScoreTxt.setWrappingWidth(198.13671875);
        oScoreTxt.setFont(new Font(30.0));
        oScoreTxt.setText(playerOScore+"");
        setTop(gridPane0);

        BorderPane.setAlignment(exitBtn, javafx.geometry.Pos.CENTER);
        exitBtn.setMnemonicParsing(false);
        exitBtn.setPrefWidth(154.0);
        exitBtn.setText("Exit");
        exitBtn.setOnAction(this::handleExitButtonAction);
        exitBtn.getStyleClass().add("custom-button");

      
        setBottom(exitBtn);
        
        BorderPane.setAlignment(recordeBtn, javafx.geometry.Pos.CENTER);
        recordeBtn.setMnemonicParsing(false);
        recordeBtn.setPrefWidth(154.0);
        recordeBtn.setText("recorde");
        recordeBtn.setOnAction(this::handleRecordeButtonAction);
        recordeBtn.getStyleClass().add("custom-button");

      
        gridPane1.add(recordeBtn, 0, 1);
        gridPane1.add(exitBtn, 0, 2);

        setBottom(gridPane1);

        gridPane.getColumnConstraints().add(columnConstraints);
        gridPane.getColumnConstraints().add(columnConstraints0);
        gridPane.getColumnConstraints().add(columnConstraints1);
        gridPane.getRowConstraints().add(rowConstraints);
        gridPane.getRowConstraints().add(rowConstraints0);
        gridPane.getRowConstraints().add(rowConstraints1);
        gridPane.getChildren().add(btn00);
        gridPane.getChildren().add(btn10);
        gridPane.getChildren().add(btn20);
        gridPane.getChildren().add(btn01);
        gridPane.getChildren().add(btn11);
        gridPane.getChildren().add(btn21);
        gridPane.getChildren().add(btn02);
        gridPane.getChildren().add(btn12);
        gridPane.getChildren().add(btn22);
        gridPane0.getColumnConstraints().add(columnConstraints2);
        gridPane0.getColumnConstraints().add(columnConstraints3);
        gridPane0.getColumnConstraints().add(columnConstraints4);
        gridPane0.getRowConstraints().add(rowConstraints2);
        gridPane0.getRowConstraints().add(rowConstraints3);
        gridPane0.getChildren().add(text);
        gridPane0.getChildren().add(text0);
        gridPane0.getChildren().add(text1);
        gridPane0.getChildren().add(text2);
        gridPane0.getChildren().add(xScoreTxt);
        gridPane0.getChildren().add(oScoreTxt);
        this.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
        
    }
    private void handleButtonProperties(Button button)
    {
        button.setBlendMode(javafx.scene.effect.BlendMode.COLOR_DODGE);
        button.setMnemonicParsing(false);
        button.setPrefHeight(75.0);
        button.setPrefWidth(91.0);
        button.setFont(new Font("System Bold Italic", 30.0));
        button.setCursor(Cursor.HAND);
        button.getStyleClass().add("game-button");
        button.setOnMouseClicked((MouseEvent event) -> {
            switch (selectModeBase.selectMode) {
                case "Two players" :  
                    
                    handlePlayerMove(button);
                    xScoreTxt.setText(playerXScore+"");
                    oScoreTxt.setText(playerOScore+"");
                    
                    break;
                case "Easy":  
                    
                    handlePlayerMove(button);
                    computerMove(); 
                    String winner = checkWinner();
                    if (winner != null && "O".equals(currentPlayer)) {
                        playerXScore+=10;
                        flag = false;
                        displayVideo(winVideoPath);
                    } else if (winner != null && "X".equals(currentPlayer)) {
                        playerOScore+=10;
                        flag = false;
                        displayLoser("you lose :(", loseVideoPath);
                        resetBoard();
                    } else if (counter == 9) {
                        playerOScore+=5;
                        playerXScore+=5;
                        flag = false;
                        displayDraw("no winner it's draw");
                    }
                    xScoreTxt.setText(playerXScore+"");
                    oScoreTxt.setText(playerOScore+"");
                    break;
                    
                     case "Hard":  
                    handlePlayerMove(button);
                   
                    computerMoveHard(); 
                    String winn = checkWinner();
                    if (winn != null && "O".equals(currentPlayer)) {
                        playerXScore+=10;
                        flag = false;
                        displayVideo(winVideoPath);
                    } else if (winn != null && "X".equals(currentPlayer)) {
                        playerOScore+=10;
                        displayLoser("you lose :(", loseVideoPath);
                        flag = false;
                        resetBoard();
                    } else if (counter == 9)
                    {
                        playerOScore+=5;
                        playerXScore+=5;
                        flag = false;
                        displayDraw("no winner it's draw");
                    }
                    xScoreTxt.setText(playerXScore+"");
                    oScoreTxt.setText(playerOScore+"");
                    break;
            }
        });
        
    }
    private void handleExitButtonAction(ActionEvent event) 
    {
        
        Parent selectModeBase = new selectModeBase();
        Scene selectModeScene = new Scene(selectModeBase);
        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        stage.setScene(selectModeScene);
        stage.show();
    }//will be at interface later
    
    private void handleRecordeButtonAction(ActionEvent event) {
    TextInputDialog dialog = new TextInputDialog();
    dialog.setTitle("Record");
    dialog.setHeaderText("Recorded Games");
    dialog.setContentText("Please enter the number of the game for replaying:");

    Optional<String> result = dialog.showAndWait();
    result.ifPresent(input -> {
        try {
            // Convert input to integer and validate
            int gameNumber = Integer.parseInt(input.trim());
            System.out.println(gameNumber);
            if (gameNumber < 1) {
                showErrorDialog("Invalid number", "Game number must be a positive integer.");
            } else {
                playRecordedGame(gameNumber);
            }
        } catch (NumberFormatException e) {
            showErrorDialog("Invalid input", "Please enter a valid number.");
        } catch (IOException ex) {
            Logger.getLogger(playGameBase.class.getName()).log(Level.SEVERE, null, ex);
            showErrorDialog("Playback error", "An error occurred while trying to replay the game.");
        }
    });
}
    private void showErrorDialog(String title, String message) {
    Alert alert = new Alert(Alert.AlertType.ERROR);
    alert.setTitle(title);
    alert.setHeaderText(null);
    alert.setContentText(message);
    alert.showAndWait();
}
    public  void handlePlayerMove(Button button) {
        
        if (button.getText().isEmpty())
        {
            comMoveFlag=true;
            Sound_Player.SoundPlayer.playSound(sound);
            if(flag){
             recordMove(button);
            }
            button.setText(currentPlayer);
            currentPlayer = ("X".equals(currentPlayer)) ? "O" : "X";
            counter++;
            String winner = checkWinner();
            if (winner != null && "X".equals(currentPlayer)) {
                displayWinner(winner, winVideoPath);
                playerOScore+=10;
                flag = false;
            }
            else if (winner != null && "O".equals(currentPlayer)) {
                displayWinner(winner, winVideoPath);
                playerXScore+=10;
                flag = false;
            }
            else if (counter == 9) {
                flag = false;
                playerXScore+=5;
                playerOScore+=5;
                displayDraw("no winner it's draw");
            }
        }
        else comMoveFlag=false;
        
        
    }

    private void computerMove() {
        if (comMoveFlag){
        int row, col;
        do {
            row = random.nextInt(3);
            col = random.nextInt(3);
        } while (!isValidMove(row, col));

        btn[row][col].setText("O");
        if(flag){
            recordMove(btn[row][col]);
        }
        
        currentPlayer = "X";
        counter++;
        }
    }

    private boolean isValidMove(int row, int col) {
        return (row >= 0 && row < 3 && col >= 0 && col < 3 && btn[row][col].getText().isEmpty());
    }

    private String checkWinner() {
        for (int i = 0; i < 3; i++) {
            if (!btn[i][0].getText().isEmpty() &&
                btn[i][0].getText().equals(btn[i][1].getText()) &&
                btn[i][0].getText().equals(btn[i][2].getText())) {
                return btn[i][0].getText();
            }
        }

        for (int i = 0; i < 3; i++) {
            if (!btn[0][i].getText().isEmpty() &&
                btn[0][i].getText().equals(btn[1][i].getText()) &&
                btn[0][i].getText().equals(btn[2][i].getText())) {
                return btn[0][i].getText();
            }
        }

        if (!btn[0][0].getText().isEmpty() &&
            btn[0][0].getText().equals(btn[1][1].getText()) &&
            btn[0][0].getText().equals(btn[2][2].getText())) {
            return btn[0][0].getText();
        }
        if (!btn[0][2].getText().isEmpty() &&
            btn[0][2].getText().equals(btn[1][1].getText()) &&
            btn[0][2].getText().equals(btn[2][0].getText())) {
            return btn[0][2].getText();
        }

        return null;
    }

    private void displayLoser(String message, String path) {
        System.out.println(message);
        displayVideo(path);
        resetBoard();
    }

    private void displayWinner(String winner, String path) {
        System.out.println("The winner is: " + winner);
        displayVideo(path);
        resetBoard();
    }

    private void displayVideo(String path) {
        playVideo.playVideo(path);   
    }

    private void displayDraw(String message) {
        System.out.println(message);
        resetBoard();
    }

    private void resetBoard() {

        // Clear the recorded moves
        recordedMoves.clear();
        
        currentPlayer = "X";
        counter = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                btn[i][j].setText("");
            }
        }
    }
    
    
    private void computerMoveHard() {
        if (comMoveFlag){
    int[] bestMove = findBestMove();
    btn[bestMove[0]][bestMove[1]].setText("O");
    if(flag){
            recordMove(btn[bestMove[0]][bestMove[1]]);
        }
    currentPlayer = "X";
    counter++;
}}

   private int[] findBestMove() {
    int bestVal = Integer.MIN_VALUE;
    int[] bestMove = new int[2];
    bestMove[0] = -1;
    bestMove[1] = -1;

    for (int i = 0; i < 3; i++) {
        for (int j = 0; j < 3; j++) {
            if (btn[i][j].getText().equals("")) {
                btn[i][j].setText("O");
                int moveVal = minimax(0, false);
                btn[i][j].setText("");

                if (moveVal > bestVal) {
                    bestMove[0] = i;
                    bestMove[1] = j;
                    bestVal = moveVal;
                }
            }
        }
    }
    return bestMove;
}

   private int minimax(int depth, boolean isMax) {
    String score = evaluate();
    if (score.equals("O")) {
        return 10 - depth;
    }
    if (score.equals("X")) {
        return depth - 10;
    }
    if (isMovesLeft() == false) {
        return 0;
    }

    if (isMax) {
        int best = Integer.MIN_VALUE;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (btn[i][j].getText().equals("")) {
                    btn[i][j].setText("O");
                    best = Math.max(best, minimax(depth + 1, false));
                    btn[i][j].setText("");
                }
            }
        }
        return best;
    } else {
        int best = Integer.MAX_VALUE;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (btn[i][j].getText().equals("")) {
                    btn[i][j].setText("X");
                    best = Math.min(best, minimax(depth + 1, true));
                    btn[i][j].setText("");
                }
            }
        }
        return best;
    }
}

   private boolean isMovesLeft() {
    for (int i = 0; i < 3; i++) {
        for (int j = 0; j < 3; j++) {
            if (btn[i][j].getText().equals("")) {
                return true;
            }
        }
    }
    return false;
}

   private String evaluate() {
    for (int row = 0; row < 3; row++) {
        if (btn[row][0].getText().equals(btn[row][1].getText()) && btn[row][1].getText().equals(btn[row][2].getText())) {
            if (btn[row][0].getText().equals("O")) {
                return "O";
            } else if (btn[row][0].getText().equals("X")) {
                return "X";
            }
        }
    }

    for (int col = 0; col < 3; col++) {
        if (btn[0][col].getText().equals(btn[1][col].getText()) && btn[1][col].getText().equals(btn[2][col].getText())) {
            if (btn[0][col].getText().equals("O")) {
                return "O";
            } else if (btn[0][col].getText().equals("X")) {
                return "X";
            }
        }
    }

    if (btn[0][0].getText().equals(btn[1][1].getText()) && btn[1][1].getText().equals(btn[2][2].getText())) {
        if (btn[0][0].getText().equals("O")) {
            return "O";
        } else if (btn[0][0].getText().equals("X")) {
            return "X";
        }
    }

    if (btn[0][2].getText().equals(btn[1][1].getText()) && btn[1][1].getText().equals(btn[2][0].getText())) {
        if (btn[0][2].getText().equals("O")) {
            return "O";
        } else if (btn[0][2].getText().equals("X")) {
            return "X";
        }
    }

    return "";
}
   private void recordMove(Button button) {
    Integer row = GridPane.getRowIndex(button);
    Integer col = GridPane.getColumnIndex(button);
    try (
        BufferedWriter writer = new BufferedWriter(new FileWriter(recordName + count + recordTxt, true))) {
        String move = currentPlayer + "," + row + "," + col;
        writer.write(move);
        writer.newLine();
    } catch (IOException e) {
        e.printStackTrace();
    }
}
   


   
   private List<String> loadRecordedMovesFromFile(int gameNumber) throws IOException {
    String fileName=new String(recordName +  gameNumber + recordTxt);
    File file = new File(fileName);
    List<String> moves = new ArrayList<>();
    try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
        String line;
        while ((line = reader.readLine()) != null) {
            moves.add(line);
        }
    }
    return moves;
}

    private void playRecordedGame(int gameNumber) throws IOException {
    recordedMovess = loadRecordedMovesFromFile(gameNumber);
    replayIndex = 0;

    if (!recordedMovess.isEmpty()) {
        playNextMove();
    }
}

 private void playNextMove() {
    if (replayIndex >= recordedMovess.size()) {
        return;
    }

    String[] moveData = recordedMovess.get(replayIndex).split(",");
    int row = Integer.parseInt(moveData[0]);
    int col = Integer.parseInt(moveData[1]);
    String player = moveData[2];

    Button button = btn[row][col];
    button.setText(player);

    replayIndex++;
    PauseTransition pause = new PauseTransition(Duration.seconds(1));
    pause.setOnFinished(event -> playNextMove());
    pause.play();
}
 private void showAlert(Alert.AlertType alertType, String title, String message)
 {
        Platform.runLater(() -> {
            Alert alert = new Alert(alertType);
            alert.setTitle(title);
            alert.setHeaderText(null);
            alert.setContentText(message);

            if (alertType == Alert.AlertType.CONFIRMATION) {
                alert.getButtonTypes().setAll(ButtonType.OK, ButtonType.CANCEL);
                alert.showAndWait().ifPresent(response -> {
                    if (response == ButtonType.OK) 
                    {
                            flag = true;
                            File file = new File(recordName + count + recordTxt);
                            while (file.exists())
                            {
                            count++;
                            file = new File(recordName + count + recordTxt);
                            }
                    }
                    else {
                flag=false;
                         }
                });
            } 
        });
    }

} 