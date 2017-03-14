package views.gewonnenview;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class GewonnenView extends BorderPane {
    private Label reached;
    private Label question;
    private Label highscore;
    private Label lblHighScoreNumber;

    private Button btnContinue;
    private Button btnNewGame;
    private Button btnExit;

    public GewonnenView() {
        initialiseNodes();
        layoutNodes();
    }

    private void initialiseNodes() {
        reached = new Label("You Reached 2048!");
        question = new Label("What do you want to do?");
        highscore = new Label("Highscore: ");
        lblHighScoreNumber = new Label("0");

        btnContinue = new Button("Continue Playing");
        btnNewGame = new Button("New Game");
        btnExit = new Button("Exit");
    }

    private void layoutNodes() {
        HBox highscorelbls = new HBox(highscore, lblHighScoreNumber);
        VBox tekstBox = new VBox(reached, question, highscorelbls);
        VBox buttonBox = new VBox(10, btnContinue, btnNewGame, btnExit);
        VBox groupBox = new VBox(tekstBox, buttonBox);

        highscorelbls.setAlignment(Pos.CENTER);
        tekstBox.setAlignment(Pos.CENTER);
        buttonBox.setAlignment(Pos.CENTER);
        groupBox.setAlignment(Pos.CENTER);

        setCenter(groupBox);

        //CSS
        reached.setId("winTitle");
        question.setId("winSubTitle");
        lblHighScoreNumber.setId("winHighScore");
        highscore.setId("winHighScore");
        tekstBox.setId("winLbls");
        btnNewGame.setId("menuBtns");
        btnContinue.setId("menuBtns");
        btnExit.setId("menuBtns");
    }

    public Button getBtnContinue() {
        return btnContinue;
    }

    public Button getBtnExit() {
        return btnExit;
    }

    public Button getBtnNewGame() {
        return btnNewGame;
    }

    public Label getLblHighScoreNumber() {
        return lblHighScoreNumber;
    }
}