package model;

import com.sun.javafx.scene.traversal.Direction;
import javafx.scene.control.Label;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class Bord {
    // dimensies van het bord
    private static final int GROOTTE = 4;

    private Tegel[][] tegels = new Tegel[GROOTTE][GROOTTE];


    // initialieer n x n bord met 0 waardes (leeg bord)
    public Bord() {

        for (int i = 0; i < tegels[0].length; i++) {
            for (int j = 0; j < tegels.length; j++) {
                tegels[i][j] = new Tegel();
            }

        }
    }

    public Label[][] tegelsNaarLabels() {
        Label[][] dummyMatrix = new Label[GROOTTE][GROOTTE];
        for (int x = 0; x < tegels.length; x++) {
            for (int y = 0; y < tegels[x].length; y++) {
                dummyMatrix[x][y] = new Label(tegels[x][y].getWaarde() + "");
            }
        }
        return dummyMatrix;
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

    /**
     * 1 op 10 kans om het getal 4 terug te geven
     */
    private int getNieuweTegelWaarde() {
        Random random = new Random();
        return random.nextInt(10) == 1 ? 4 : 2;
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

    public String toString() {

        StringBuilder sb = new StringBuilder();

        for (Tegel[] tegelRow : tegels) {
            for (Tegel tegel : tegelRow) {
                sb.append(tegel);
                sb.append(" ");
            }
            sb.append("\n");
        }

        return sb.toString();
    }


    // terminal test methodes
    Random random = new Random();


    public int[][] getTegels(){

        int[][] values = new int[4][4];

        for (int i = 0; i < tegels.length; i++) {
            for (int j = 0; j < tegels[i].length; j++) {
                values[i][j]=tegels[i][j].getWaarde();
            }
        }

        return values;
    }


    public void printArray() {
        for (Tegel[] tegel : tegels) {
            System.out.printf("%6d%6d%6d%6d%n",
                    tegel[0].getWaarde(), tegel[1].getWaarde(), tegel[2].getWaarde(), tegel[3].getWaarde());
        }
        System.out.printf("%n");
    }

    public void nieuweGetallenToevoegen() {
        ArrayList<Integer> legePlaatsenX = new ArrayList<>();
        ArrayList<Integer> legePlaatsenY = new ArrayList<>();
        for (Integer x = 0; x < 4; x++) {
            for (Integer y = 0; y < 4; y++) {
                if (tegels[x][y].getWaarde() == 0) {
                    legePlaatsenX.add(new Integer(x));
                    legePlaatsenY.add(new Integer(y));
                }
            }
        }
        //willekeurige startpositie van 0-15
        int keuze = random.nextInt(legePlaatsenX.size()); //16
        int nieuwGetal = random.nextInt(10) == 1 ? 4 : 2;
//      Integer[] coordinates = emptySpaces.get(keuze);
        int X = legePlaatsenX.get(keuze);
        int Y = legePlaatsenY.get(keuze);
        tegels[X][Y].setWaarde(nieuwGetal);
    }
}
