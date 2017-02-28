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
    private Font fontSize = new Font(40); //TODO: VERVANGEN IN CSS!!!
    private Label[] lblSpelernamen;
    private Label[] lblSpelerScores;

    private HBox naamEnScoreBox; // box met het label Naam & Score (vaste labels)
    private VBox spelerNaamInhoudVBox; //VBOX voor de spelernamen
    private VBox scoreInhoudVBox; //VBOX voor de spelerscores
    private HBox inhoudBox; // HBox met de naam en score VBOXEN
    private VBox schermVBox; // VBOX met: NaamEnScoreBox (HBOX) en de inhoudbox (HBOX)

    public TopScoreView() {

        initialiseNodes();
        layoutNodes();
    }

    private void initialiseNodes() {

        // setup labels
        schermNaamLbl = new Label("Top scores:");
        naamLbl = new Label("Naam");
        scoreLbl = new Label("Score");

        // setup Boxen
        naamEnScoreBox = new HBox(300, naamLbl, scoreLbl);
        spelerNaamInhoudVBox = new VBox();
        scoreInhoudVBox = new VBox();
        inhoudBox = new HBox(300);
        schermVBox = new VBox(20,naamEnScoreBox,inhoudBox);
    }

    private void layoutNodes() {

        // labels
        schermNaamLbl.setPadding(new Insets(30, 0, 0, 0));

        // Fontsize - TODO: DEZE VERVANGEN DOOR CSS!!!
        schermNaamLbl.setFont(fontSize);
        naamLbl.setFont(fontSize);
        scoreLbl.setFont(fontSize);

        // positionering boxen
        setTop(schermNaamLbl);
        setAlignment(schermNaamLbl,Pos.CENTER);
        setCenter(schermVBox);
        setAlignment(schermVBox,Pos.CENTER);
    }

    public void setSpelers(ArrayList<Speler> spelerLijst) {

        // de reden dat we hier altijd met de size van de list werken is omdat er niet altijd 10 topscores zijn.
        final int aantal = spelerLijst.size();

        // grootte van de arrays instellen op de lengte van de lijst TODO: Dit moet nog getest worden met minder dan 10 spelers
        lblSpelernamen = new Label[aantal];
        lblSpelerScores = new Label[aantal];

        // Labels opvullen voor elke speler
        for (int i = 0; i < aantal; i++) {

            lblSpelernamen[i] = new Label(i+1 +".  "+spelerLijst.get(i).getNaam());
            lblSpelerScores[i] = new Label(""+spelerLijst.get(i).getScore());

            // labels toevoegen aan de correcte boxen
            spelerNaamInhoudVBox.getChildren().add(lblSpelernamen[i]);
            scoreInhoudVBox.getChildren().add(lblSpelerScores[i]);
        }
        // de boxen toevoegen aan de inhoudbox
        inhoudBox.getChildren().addAll(spelerNaamInhoudVBox,scoreInhoudVBox);
    }
}