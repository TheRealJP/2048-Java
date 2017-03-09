package views.menuview;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class MenuView extends VBox {
    private Label lblMenu;
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
    }

    private void initialiseNodes() {
        lblMenu = new Label("Menu");
        btnContinue = new Button("Continue Playing");
        btnSave = new Button("Save Game");
        btnNew = new Button("New Game");
        btnExit = new Button("Exit");
        getChildren().addAll(lblMenu, btnContinue, btnSave, btnNew, btnExit);
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
