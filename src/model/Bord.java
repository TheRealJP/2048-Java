package model;

import com.sun.javafx.scene.traversal.Direction;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Bord {
    // dimensies van het bord
    private static final int GROOTTE = 4;
    private Speler speler;
    private TopScores topScores;
    private Tegel[][] tegels = new Tegel[GROOTTE][GROOTTE];
    private File spelbestand = new File("src" + File.separator + "bestanden" + File.separator + "spel.bin");
    private File spelerbestand = new File("src" + File.separator + "bestanden" + File.separator + "speler.bin");

    // initialieer n x n bord met null waardes (leeg bord)
    public Bord() {
        topScores = new TopScores();
        speler = new Speler();
        for (int i = 0; i < tegels[0].length; i++) {
            for (int j = 0; j < tegels.length; j++) {
                tegels[i][j] = new Tegel();
            }
        }
    }

    // genereer een tegel met een random waarde van 2 of 4 in een random positie
    // return true als een nieuwe tegel geplaatst is, false als er geen lege tegel meer is
    public boolean genereerNieuweTegel() {
        if (!(heeftLegeTegel())) {
            return false;
        }

        Random random = new Random();
        // herhalen tot een lege tegel gevonden is
        while (true) {

            int x = random.nextInt(GROOTTE);
            int y = random.nextInt(GROOTTE);

            if (tegels[x][y].getWaarde() == 0) {
                tegels[x][y].setWaarde(getNieuweTegelWaarde());
                return true;
            }
        }
    }

    // 1 op 10 kans om het getal 4 terug te geven
    private int getNieuweTegelWaarde() {
        Random random = new Random();
        return random.nextInt(10) == 1 ? 4 : 2;
    }

    public void bordLeegMaken() {
        //zet tegels op null
        for (int i = 0; i < tegels.length; i++) {
            for (int j = 0; j < tegels[i].length; j++) {
                tegels[i][j] = new Tegel();
            }
        }
        //zet score op nul
        speler.scoreOpNul();
    }

    /**
     * Dit is het hoofdalgoritme van het spel, we maken een groep/set aan adhv de richting waar de gebruiker naar schuift,
     * bv, wanneer een gebruiker op de toets voor naar links te schuiven drukt, dan zal de groep bestaan uit alle rijen tegels (horizontaal).
     * Hierdoor kunnen we alle tegels op de horizontale rijen in dezelfde richting doen bewegen.
     * de parameter richting geeft aan in welke richting de gebruiker schuift.
     */

    public void verplaats(Direction richting) {
        for (int i = 0; i < GROOTTE; i++) {

            //groep v/d tegel
            List<Tegel> tegelSet = new ArrayList<Tegel>();
            for (int j = 0; j < GROOTTE; j++) {

                switch (richting) {
                    case LEFT:
                        tegelSet.add(tegels[i][j]);
                        break;
                    case RIGHT:
                        tegelSet.add(tegels[i][GROOTTE - j - 1]);
                        break;
                    case UP:
                        tegelSet.add(tegels[j][i]);
                        break;
                    case DOWN:
                        tegelSet.add(tegels[GROOTTE - j - 1][i]);
                        break;
                    default:
                        break;
                }
            }

            if (!(isLegeTegel(tegelSet))) {
                schuif(tegelSet); // hoofdtegel groep algoritme
            }
        }
    }

    private boolean isLegeTegel(List<Tegel> tegelSet) {
        for (Tegel tegel : tegelSet) {
            if (tegel.getWaarde() != 0) {
                return false;
            }
        }
        return true;
    }

    //hoofdtegel groep algoritme
    private void schuif(List<Tegel> tegelSet) {
        schuifNaarKant(tegelSet);
        tegelsSamenvoegen(tegelSet);
    }

    //schuif alle tegels naar een kant in het geval dat er een nul tussen zit
    private void schuifNaarKant(List<Tegel> tegelSet) {
        for (int i = 0; i < tegelSet.size(); i++) {
            if (overblijvendeTegelIsNul(tegelSet, i)) {
                return;
            }

            while (tegelSet.get(i).getWaarde() == 0) {
                schuifNaar(tegelSet, i);
            }
        }
    }

    private boolean overblijvendeTegelIsNul(List<Tegel> tegelSet, int i) {

        List<Tegel> overblijvendeTegel = new ArrayList<Tegel>();
        for (int j = i; j < tegelSet.size(); j++) {
            overblijvendeTegel.add(tegelSet.get(j));
        }

        return (isLegeTegel(overblijvendeTegel));
    }

    private void schuifNaar(List<Tegel> tegelSet, int index) {

        for (int j = index; j < tegelSet.size() - 1; j++) {
            tegelSet.get(j).setWaarde(tegelSet.get(j + 1).getWaarde());
        }
        tegelSet.get(tegelSet.size() - 1).clear();
    }

    // tegel samenvoegen als de tegels in de richting waarin we schuiven dezelfde waarde hebben.
    private void tegelsSamenvoegen(List<Tegel> tegelSet) {
        for (int i = 0; i < tegelSet.size() - 1; i++) {
            if (tegelSet.get(i).equals(tegelSet.get(i + 1))) {
                tegelSet.get(i).merge(tegelSet.get(i + 1));

                //score van speler verhogen met de waarde van de samengevoegde tegels
                // aangezien de setScore methode de score automatisch optelt geven we enkel de waarde door
                speler.setScore(tegelSet.get(i).getWaarde());
                //de waarde van de tegel op positie i+1 verwijderen
                tegelSet.get(i + 1).clear();
                schuifNaar(tegelSet, i + 1);
            }
        }
    }

    // Controleren of er nog mogelijkheden zijn, als er geen mogelijkheden meer zijn om te "verplaatsen" is de speler verloren
    public boolean isVol() {

        if (heeftLegeTegel()) {
            return false;
        }
        return !(heeftGelijkeBuur());
    }

    private boolean heeftLegeTegel() {

        for (int i = 0; i < GROOTTE; i++) {
            for (int j = 0; j < GROOTTE; j++) {
                if (tegels[i][j].getWaarde() == 0) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean heeftGelijkeBuur() {

        for (int i = 0; i < GROOTTE; i++) {
            for (int j = 0; j < GROOTTE; j++) {

                //controleer de tegel aan de rechterkant van de gekozen tegen, negeer de laatste kolom.
                if (j < GROOTTE - 1) {
                    if (tegels[i][j].equals(tegels[i][j + 1])) {
                        return true;
                    }
                }

                //controleer de tegel onder de gekozen tegel, negeer de laatste rij.
                if (i < GROOTTE - 1) {
                    if (tegels[i][j].equals(tegels[i + 1][j])) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public boolean heeft2048() {
        for (int i = 0; i < tegels.length; i++) {
            for (int j = 0; j < tegels[i].length; j++) {
                if (tegels[i][j].getWaarde() == 2048) {
                    return true;
                }
            }
        }
        return false;
    }

    // we returnen hier een 2-dimentionale array van Strings zodat we deze kunnen gebruiken om de labels in te stellen
    public String[][] getTegels() {

        String[][] values = new String[4][4];
        for (int i = 0; i < tegels.length; i++) {
            for (int j = 0; j < tegels[i].length; j++) {
                // als een waarde 0 is, vervangen we deze door een lege string zodat er geen nutteloze 0-en zijn
                values[i][j] = tegels[i][j].getWaarde() == 0 ? " " : "" + tegels[i][j].getWaarde();
            }
        }
        return values;
    }

    public Speler getSpeler() {
        return speler;
    }

    public TopScores getTopScores() {
        return topScores;
    }

    public void setTegels(Tegel[][] tegels) {
        this.tegels = tegels;
    }

    public Tegel[][] bordLaden() {
        // om de bestanden te kunnen lezen, we moeten hier geen inputstream.close doen aangezien we het op deze manier doen (met haakjes)

        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(spelbestand))) {

            // bestand inlezen in array
            Tegel[][] tegels = (Tegel[][]) inputStream.readObject();

            // opgehaalde array in spelerlijst laden
            for (int i = 0; i < tegels.length; i++) {
                for (int j = 0; j < tegels[i].length; j++) {
                    this.tegels[i][j] = tegels[i][j];
                }
            }

        } catch (FileNotFoundException e) {
            System.out.println("Savefile niet gevonden, zal aangemaakt worden nadat de speler het spel opslaat. ");

        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }
        return tegels;
    }

    public boolean bordOpslaan() {
        // om weg te schrijven naar bestand, moet geen outputstream.close, aangezien we met haakjes werken (nieuw in Java 7)
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(spelbestand))) {

            // array wegschrijven naar bestand
            outputStream.writeObject(tegels);
            return true;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public Speler spelerLaden() {


        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(spelerbestand))) {

            // bestand inlezen in array
            speler = (Speler) inputStream.readObject();

        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }

        return speler;
    }

    public void spelerOpslaan() {
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(spelerbestand))) {

            // array wegschrijven naar bestand
            outputStream.writeObject(speler);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
