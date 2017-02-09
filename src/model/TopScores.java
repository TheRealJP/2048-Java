package model;

import java.util.ArrayList;
import java.util.Collections;

public class TopScores {

ArrayList<Speler> spelerLijst;

    public TopScores() {

        spelerLijst = new ArrayList<Speler>();
    }

    private void sorteer(ArrayList<Speler> spelerLijst){

        Collections.sort(spelerLijst);
    }

    public String getTopSpelers(ArrayList<Speler> spelerLijst){

        String resultaat="";

        //TODO: text uit bestand ophalen en returnen als string

        return resultaat;
    }

    public void voegSpelerToe(Speler speler){

        spelerLijst.add(speler);
    }
}
