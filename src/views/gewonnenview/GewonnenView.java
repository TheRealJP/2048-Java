package views.gewonnenview;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

public class GewonnenView extends BorderPane {
    private Label reached;
    private Label question;
    private Label highscore;

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
        highscore = new Label("Highscore: 0");

        btnContinue = new Button("Continue Playing");
        btnNewGame = new Button("New Game");
        btnExit = new Button("Exit");
    }

    private void layoutNodes() {
        VBox tekstBox = new VBox(reached, question, highscore);
        VBox buttonBox = new VBox(10,btnContinue, btnNewGame, btnExit);
        VBox groupBox = new VBox(tekstBox, buttonBox);

        buttonBox.setAlignment(Pos.CENTER);
        groupBox.setAlignment(Pos.CENTER);
        tekstBox.setAlignment(Pos.CENTER);

        setCenter(groupBox);
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
}
