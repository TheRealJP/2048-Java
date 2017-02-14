package model;

import java.util.ArrayList;
import java.util.Collections;

public class TopScores {

    private ArrayList<Speler> spelerLijst;

    public TopScores() {
        spelerLijst = new ArrayList<Speler>();
        getTopSpelers();
    }

    // arraylist sorteren op score
    private void sorteer(ArrayList<Speler> spelerLijst) {
        Collections.sort(spelerLijst);
    }

    // top spelers lezen uit bestand
    public String getTopSpelers() {

        String resultaat = "";

        //TODO: HighScores lezen uit bestand

        return resultaat;
    }

    public int getTopScoreVanSpeler(Speler speler) {
        return speler.getScore();
    }

    // speler toevoegen
    public void voegSpelerToe(Speler speler) {
        spelerLijst.add(speler);
    }

    // top spelers schrijven naar bestand
    public void setTopSpelers(ArrayList<Speler> spelerLijst) {

        //TODO: HighScores opslaan in bestand
    }
}
