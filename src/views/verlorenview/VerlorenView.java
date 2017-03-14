package views.verlorenview;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class VerlorenView extends BorderPane {
    private Label lblYouLost;
    private Label lblYourScore;
    private Label lblscoreNumber;

    private Button btnScoreBoard;
    private Button btnNewGame;
    private Button btnExit;

    public VerlorenView() {
        initialiseNodes();
        layoutNodes();
    }

    private void initialiseNodes() {
        lblYouLost = new Label("You lost!");
        lblYourScore = new Label("Your Score: ");
        lblscoreNumber = new Label("0");

        btnNewGame = new Button("New Game");
        btnScoreBoard = new Button("Scoreboard");
        btnExit = new Button("Exit");
    }

    private void layoutNodes() {
        HBox scoreBox = new HBox(lblYourScore, lblscoreNumber); //your score: + 0
        VBox tekstBox = new VBox(lblYouLost, scoreBox);
        VBox buttonBox = new VBox(10, btnNewGame, btnScoreBoard, btnExit);
        VBox groupBox = new VBox(30, tekstBox, buttonBox);

        buttonBox.setAlignment(Pos.CENTER);
        groupBox.setAlignment(Pos.CENTER);
        tekstBox.setAlignment(Pos.CENTER);
        scoreBox.setAlignment(Pos.CENTER);

        setCenter(groupBox);

        lblYouLost.setId("youLost");
        lblYourScore.setId("yourScore");
        lblscoreNumber.setId("yourScore");
        btnNewGame.setId("menuBtns");
        btnScoreBoard.setId("menuBtns");
        btnExit.setId("menuBtns");
    }

    public Label getLblscoreNumber() {
        return lblscoreNumber;
    }

    public Button getBtnScoreBoard() {
        return btnScoreBoard;
    }

    public Button getBtnExit() {
        return btnExit;
    }

    public Button getBtnNewGame() {
        return btnNewGame;
    }
}