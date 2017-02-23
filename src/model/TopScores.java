package model;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TopScores {

    File file;
    private List<Speler> spelerLijst;

    public TopScores() {
        spelerLijst = new ArrayList<Speler>();
        file = new File("src/bestanden/highscores");
        leesTopSpelers();
    }

    private void leesTopSpelers() {

        // om de bestanden te kunnen lezen, we moeten hier geen inputstream.close doen aangezien we het op deze manier doen (met haakjes)
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(file))) {

            // bestand inlezen in array
            Speler[] spelers = (Speler[]) inputStream.readObject();

            // opgehaalde array in spelerlijst laden
            for (int i = 0; i < spelers.length; i++) {
                spelerLijst.add(spelers[i]);
            }

            System.out.println("INITIAL SCOREBOARD: \n" + this.toString()); //TODO: testcode verwijderen

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (EOFException e) {

            /**
             *  EOFException - if this input stream reaches the end before reading eight bytes. (dus als de file leeg is bv)
             *  in het geval deze exception voorkomt (en de file dus minder dan 8 bytes bevat en dus leeg is, doen we gewoon niets
             */

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void schrijfTopSpelers() {

        // om weg te schrijven naar bestand, moet geen outputstream.close, aangezien we met haakjes werken (nieuw in Java 7)
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(file))) {

            // spelerlijst inladen in een array
            Speler[] spelers = spelerLijst.toArray(new Speler[spelerLijst.size()]);

            // array wegschrijven naar bestand
            outputStream.writeObject(spelers);

            System.out.println("AFTER GAME SCOREBOARD: \n" + this.toString()); //TODO: testcode verwijderen

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // krijgen we binnen vanuit bordpresenter (ctrl+click op de naam om te zien van waar)
    public void voegScoreToe(Speler speler) {

        // als de lijst kleiner is dan 10, en er dus sowieso nog vrije plaats is, laden we de speler gewoon in.
        if (spelerLijst.size() < 10) {

            spelerLijst.add(speler);

            // als de score hoger is dan de laatste speler in de lijst, voegen we deze toe en sorteren we de lijst
        } else if (speler.getScore() > spelerLijst.get(0).getScore()) {

            // speler met de laatste score wegdoen (in dit geval index 0 because sorting)
            spelerLijst.remove(0);
            spelerLijst.add(speler);
        }

        /**
         * sorteren van de lijst (omdat we de speler gewoon achteraan toevoegen MOETEN we hier nog sorteren
         * de manier van rangschikken (bv. klein -> groot) maakt hier niet uit, aangezien we dit toch nog moeten uilezen in view.
         */
        Collections.sort(spelerLijst);
        schrijfTopSpelers();
    }

    //TODO: testmethode, wegdoen!
    @Override
    public String toString() {

        String output = "";

        for (int i = 0; i < spelerLijst.size(); i++) {
            output += spelerLijst.get(i).toString() + "\n";
        }
        return output;
    }
}