package views.topscoreview;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class TopScoreView extends BorderPane {

    private Label schermNaamLbl;
    private Label naamLbl;
    private Label scoreLbl;
    private HBox naamEnScoreBox;
    private VBox schermLabels; // alle vaste labels van het scherm worden hierin gestoken
    private Font fontSize = new Font(50); //TODO: VERVANGEN IN CSS!!!

    public TopScoreView() {

        initialiseNodes();
        layoutNodes();
    }

    private void initialiseNodes() {

        schermNaamLbl = new Label("Top scores:");
        naamLbl = new Label("Naam");
        scoreLbl = new Label("Score");
        naamEnScoreBox = new HBox(300, naamLbl,scoreLbl);
        schermLabels = new VBox(20, schermNaamLbl,naamEnScoreBox);
    }

    private void layoutNodes() {

        // labels
        schermNaamLbl.setPadding(new Insets(50,0,0,0));

        //TODO: DEZE VERVANGEN DOOR CSS!!!
        schermNaamLbl.setFont(fontSize);
        naamLbl.setFont(fontSize);
        scoreLbl.setFont(fontSize);

        naamEnScoreBox.setAlignment(Pos.CENTER);
        schermLabels.setAlignment(Pos.CENTER);

        setTop(schermLabels);
        setAlignment(schermLabels, Pos.BOTTOM_CENTER);
    }
}
