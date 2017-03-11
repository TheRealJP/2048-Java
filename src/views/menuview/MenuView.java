package views.menuview;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class MenuView extends VBox {
    private Label lblMenu;
    private Label lbl2048;
    private Button btnContinue;
    private Button btnSave;
    private Button btnNew;
    private Button btnExit;
    private Button btnLoad;
    private Button btnscoreBord;

    private boolean firstTime;

    public MenuView(boolean firstTime) {
        this.firstTime = firstTime;
        initialiseNodes();
        layoutNodes();
    }

    private void layoutNodes() {
        setAlignment(Pos.TOP_CENTER);
        setSpacing(10);

        //CSS
        lbl2048.setId("2048Title");
        lblMenu.setId("lblMenu");
        btnNew.setId("menuBtns");
        btnExit.setId("menuBtns");
        btnLoad.setId("menuBtns");
        btnContinue.setId("menuBtns");
        btnSave.setId("menuBtns");
        btnscoreBord.setId("menuBtns");
        setId("menuView");
    }

    private void initialiseNodes() {
        // initialisatie
        lbl2048 = new Label("2048");
        lblMenu = new Label("Menu");
        btnLoad = new Button("Load game");
        btnContinue = new Button("Continue Playing");
        btnSave = new Button("Save Game");
        btnNew = new Button("New Game");
        btnExit = new Button("Exit");
        btnscoreBord = new Button("Scoreboard");

        /**
            we controleren of de gebruiker naar de menu is gegaan vanuit een spel
            of dat de gebruiker het spel net geopend heeft, we passen de menu hierop aan.
        */
        if (firstTime) {
            getChildren().addAll(lbl2048, lblMenu, btnNew, btnLoad, btnscoreBord, btnExit);
        } else {
            getChildren().addAll(lbl2048, lblMenu, btnContinue, btnSave, btnNew, btnscoreBord, btnExit);
        }
    }

    public Button getBtnSave() {
        return btnSave;
    }

    public Button getBtnNew() {
        return btnNew;
    }

    public Button getBtnExit() {
        return btnExit;
    }

    public Button getBtnContinue() {
        return btnContinue;
    }

    public Button getBtnLoad() {
        return btnLoad;
    }

    public Button getBtnscoreBord() {
        return btnscoreBord;
    }

    public boolean isFirstTime() {
        return firstTime;
    }
}
