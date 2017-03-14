package views.topscoreview;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import model.Speler;

import java.util.ArrayList;

public class TopScoreView extends BorderPane {

    private Label schermNaamLbl; //het grote, centrale label
    private Label naamLbl; // het vaste label "naam"
    private Label scoreLbl; // het vaste label "score"
    private HBox naamEnScoreBox; // box met het label Naam & Score (vaste labels)
    private VBox schermVBox; // VBOX met schermNaamLbl en naamEnScoreBox

    // labels met de namen en scores
    private Label[] lblSpelernamen;
    private Label[] lblSpelerScores;

    private VBox spelerNaamInhoudVBox; //VBOX voor de spelernamen
    private VBox scoreInhoudVBox; //VBOX voor de spelerscores
    private HBox inhoudBox; // HBox met de naam en score VBOXEN

    // button om terug te gaan naar menu
    private Button menuBtn;

    public TopScoreView() {
        initialiseNodes();
        layoutNodes();
    }

    private void initialiseNodes() {

        // setup labels
        schermNaamLbl = new Label("High scores");
        naamLbl = new Label("Name");
        scoreLbl = new Label("Score");

        // setup boxen
        naamEnScoreBox = new HBox(320, naamLbl, scoreLbl);
        schermVBox = new VBox(20, schermNaamLbl, naamEnScoreBox);
        spelerNaamInhoudVBox = new VBox();
        scoreInhoudVBox = new VBox();
        inhoudBox = new HBox(95);

        //setup buttons
        menuBtn = new Button("Back");
    }

    private void layoutNodes() {

        // CSS instellen
        schermNaamLbl.setId("schermNaamLbl");
        naamLbl.setId("naamEnScoreLbls");
        scoreLbl.setId("naamEnScoreLbls");
        naamEnScoreBox.setId("naamEnScoreBox");
        schermVBox.setId("schermVBox");
        inhoudBox.setId("inhoudBox");
        menuBtn.setId("menuBtns");

        // positionering boxen
        setTop(schermVBox);
        setCenter(inhoudBox);
        setBottom(menuBtn);

        // items die we niet in css kunnen alignen hier
        setAlignment(menuBtn, Pos.BASELINE_CENTER);
    }

    public void setSpelers(ArrayList<Speler> spelerLijst) {

        // de reden dat we hier altijd met de size van de list werken is omdat er niet altijd 10 topscores zijn.
        final int aantal = spelerLijst.size();

        // grootte van de arrays instellen op de lengte van de lijst
        lblSpelernamen = new Label[aantal];
        lblSpelerScores = new Label[aantal];

        String nummer;

        // Labels opvullen voor elke speler
        for (int i = 0; i < aantal; i++) {

            nummer = (i + 1 + ".").length() == 3 ? (i + 1 + ".") + "  " : (i + 1 + ".") + "    "; //hiermee zorgen we ervoor dat de text na de positienr in het scorebord goed gepositioneerd is.
            lblSpelernamen[i] = new Label(nummer + spelerLijst.get(i).getNaam());
            lblSpelernamen[i].setId("spelerNamenLbl");
            lblSpelerScores[i] = new Label("" + spelerLijst.get(i).getScore());
            lblSpelerScores[i].setId("spelerScoresLbl");

            // labels toevoegen aan de correcte boxen
            spelerNaamInhoudVBox.getChildren().add(lblSpelernamen[i]);
            scoreInhoudVBox.getChildren().add(lblSpelerScores[i]);
        }
        // de boxen toevoegen aan de inhoudbox
        inhoudBox.getChildren().addAll(spelerNaamInhoudVBox, scoreInhoudVBox);
    }

    public Button getMenuBtn() {
        return menuBtn;
    }
}