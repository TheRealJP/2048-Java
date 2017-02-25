package views.topscoreview;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import model.Speler;

import java.util.ArrayList;

public class TopScoreView extends BorderPane {

    private Label schermNaamLbl; //het grote, centrale label
    private Label naamLbl; // het vaste label "naam"
    private Label scoreLbl; // het vaste label "score"
    private HBox naamEnScoreBox; //
    private VBox schermLabels; // alle vaste labels van het scherm worden hierin gestoken
    private Font fontSize = new Font(50); //TODO: VERVANGEN IN CSS!!!
    private Label[] lblSpelernamen;
    private Label[] lblSpelerScores;
    private HBox[] hBoxSpeler;
    private VBox scoreInhoudVBox;

    public TopScoreView() {

        initialiseNodes();
        layoutNodes();
    }

    private void initialiseNodes() {

        schermNaamLbl = new Label("Top scores:");
        naamLbl = new Label("Naam");
        scoreLbl = new Label("Score");
        naamEnScoreBox = new HBox(300, naamLbl, scoreLbl);
        schermLabels = new VBox(20, schermNaamLbl, naamEnScoreBox);
    }

    private void layoutNodes() {

        // labels
        schermNaamLbl.setPadding(new Insets(30, 0, 0, 0));

        //TODO: DEZE VERVANGEN DOOR CSS!!!
        schermNaamLbl.setFont(fontSize);
        naamLbl.setFont(fontSize);
        scoreLbl.setFont(fontSize);

        naamEnScoreBox.setAlignment(Pos.CENTER);
        schermLabels.setAlignment(Pos.CENTER);

        setTop(schermLabels);
        setAlignment(schermLabels, Pos.BOTTOM_CENTER);
    }

    public void setSpelers(ArrayList<Speler> spelerLijst) {

        // de reden dat we hier altijd met de size van de list werken is omdat er niet altijd 10 topscores zijn.
        final int aantal = spelerLijst.size();

        lblSpelernamen = new Label[aantal];
        lblSpelerScores = new Label[aantal];
        hBoxSpeler = new HBox[aantal];

        for (int i = 0; i < aantal; i++) {
            lblSpelernamen[i] = new Label((i + 1)+". "+spelerLijst.get(i).getNaam());
            lblSpelernamen[i].setFont(fontSize);
            lblSpelerScores[i] = new Label("" + spelerLijst.get(i).getScore());
            lblSpelerScores[i].setFont(fontSize);
            hBoxSpeler[i] = new HBox(300,lblSpelernamen[i], lblSpelerScores[i]);
            hBoxSpeler[i].setAlignment(Pos.CENTER);
        }
        scoreInhoudVBox = new VBox(hBoxSpeler);
        scoreInhoudVBox.setAlignment(Pos.TOP_CENTER);
        scoreInhoudVBox.setPadding(new Insets(40,0,0,0));
        setCenter(scoreInhoudVBox);
        setAlignment(scoreInhoudVBox, Pos.CENTER);
    }
}