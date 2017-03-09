package views.nieuwspelview;

import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

public class NieuwSpelView extends BorderPane {
    private Label lblNewGame;
    private Label lblEnterName;
    private TextField txtName;
    private Button btnSpeel;

    public NieuwSpelView() {
        initialiseNodes();
        layoutNodes();
    }


    private void initialiseNodes() {
        lblEnterName = new Label("What is your name?");
        lblNewGame = new Label("New Game");
        txtName = new TextField();
        btnSpeel = new Button("Play!");
    }

    private void layoutNodes() {
        //Boxes
        VBox newGameBox = new VBox(lblNewGame, lblEnterName, txtName, btnSpeel);
        newGameBox.setSpacing(30);
        setMinSize(800, 800);
        txtName.setMaxSize(250, 100);
        txtName.setFocusTraversable(true);
        btnSpeel.setMinSize(100,40);

        //orientering van elementen op de borderpane
        setCenter(newGameBox);

        //uitlijning
        newGameBox.setAlignment(Pos.CENTER);
        txtName.setCenterShape(true);

        //CSS
        txtName.setId("txtName");
        lblEnterName.setId("enterName");
        lblNewGame.setId("newGame");
    }

    public TextField getTxtName() {
        return txtName;
    }

    public Button getBtnSpeel() {
        return btnSpeel;
    }
}
