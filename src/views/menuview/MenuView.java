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

    public MenuView() {
        initialiseNodes();
        layoutNodes();
    }

    private void layoutNodes() {
        setAlignment(Pos.CENTER);
        setSpacing(10);

        //CSS
        lbl2048.setId("2048Title");
        lblMenu.setId("lblMenu");
        btnContinue.setId("menuBtns");
        btnSave.setId("menuBtns");
        btnNew.setId("menuBtns");
        btnExit.setId("menuBtns");
    }

    private void initialiseNodes() {
        lbl2048 = new Label("2 0 4 8");
        lblMenu = new Label("Menu");
        btnContinue = new Button("Continue Playing");
        btnSave = new Button("Save Game");
        btnNew = new Button("New Game");
        btnExit = new Button("Exit");
        getChildren().addAll(lbl2048,lblMenu, btnContinue, btnSave, btnNew, btnExit);
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
}
