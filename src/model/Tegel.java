package model;

import java.io.Serializable;

public class Tegel implements Serializable{ //serializable voor opslagen en laden van 2D Tegel array
    private int waarde;

    // tegel initialiseren met een waarde van 0
    public Tegel() {
        this(0);
    }


    // tegel met een specifieke waarde initialiseren
    public Tegel(int waarde) {
        this.waarde = waarde;
    }


    public void setWaarde(int waarde) {
        this.waarde = waarde;
    }

    public int getWaarde() {
        return waarde;
    }

    // twee tegels zijn hetzelfde wanneer ze dezelfde waarde hebben, dit moeten we weten om tegels te kunnen samenvoegen
    // return true als twee tegels gelijk zijn
    public boolean equals(Tegel tegel) {
        return tegel.getWaarde() == this.getWaarde();
    }

    // voeg de waarde van deze tegel samen met de waarde van de tegel in de parameter
    public void merge(Tegel tegel) {
        this.waarde += tegel.getWaarde();
    }

    // stel de waarde in op 0 (tegel verwijderen)
    public void clear() {
        this.setWaarde(0);
    }
}