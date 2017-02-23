package views.topscoreview;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;

public class TopScoreView extends BorderPane {

    private Label screenNameLbl;

    public TopScoreView() {

        initialiseNodes();
        layoutNodes();
    }

    private void initialiseNodes() {

        screenNameLbl = new Label("Top scores:");
    }

    private void layoutNodes() {

        setAlignment(screenNameLbl, Pos.TOP_CENTER);
    }
}
