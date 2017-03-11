package model;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

public class TopScores {

    private File file;
    private ArrayList<Speler> spelerLijst;

    public TopScores() {
        spelerLijst = new ArrayList<Speler>();
        file = new File("src" + File.separator + "bestanden" + File.separator + "highscores");
        leesTopSpelers();
    }

    private void leesTopSpelers() {

        // inlezen van bestand in spelerLijst (op deze manier moeten we geen close gebruiken voor de stream)
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(file))) {

            spelerLijst = (ArrayList<Speler>) inputStream.readObject();

            // sorteren na het lezen (kan zijn dat dit overbodig is, but meh, better to be sure)
            Collections.sort(spelerLijst);

        } catch (FileNotFoundException e) {
            System.out.println("Topscores bestand niet gevonden, zal aangemaakt worden nadat de speler het spel verliest/wint. ");
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

    // speler toevoegen aan lijst
    public void voegScoreToe(Speler speler) {

        // lijst sorteren
        Collections.sort(spelerLijst);

        // als de lijst geen 10 topscores bevat, gewoon toevoegen (en achteraf sorteren bij schrijfTopSpelers)
        if (spelerLijst.size() < 10) {

            spelerLijst.add(speler);

            // in het andere geval kijken we of de score hoger is als de laagste waarde, als dit het geval is deze waarde verwijderen en nieuwe toevoegen
        } else if (speler.getScore() > spelerLijst.get(0).getScore()) {

            spelerLijst.remove(0);
            spelerLijst.add(speler);
        }

        // wegschrijven
        schrijfTopSpelers();
    }

    private void schrijfTopSpelers() {

        // sorteren voor we wegschrijven zodat we correct kunnen wegschrijven
        Collections.sort(spelerLijst);

        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(file))) {

            // wegschrijven (geen close nodig voor de stream aangezien we het op deze manier doen)
            outputStream.writeObject(spelerLijst);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // inlezen van bestand in spelerLijst
        leesTopSpelers();
    }

    // lijst ophalen (eerst reversen)
    public ArrayList<Speler> getSpelerLijst() {
        leesTopSpelers();
        Collections.reverse(spelerLijst);
        return spelerLijst;
    }

    // topscore ophalen
    public int getTopscore() {

        if (spelerLijst.size() == 0) {
            return 0;
        }
        return spelerLijst.get(0).getScore();
    }
}