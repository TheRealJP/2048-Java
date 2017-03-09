package model;

import java.io.Serializable;

/**
 * Serializable om het weg te schrijven met een ObjectOutputStream,
 * Comparable om de Arraylist met scores te sorteren
 */
public class Speler implements Serializable, Comparable<Speler> {

    private String naam;
    private int score;

    public Speler() {
        // constructor voor bord model, vanuit het bord model roepen we setNaam en setScore op!
        this.score = 0;
    }

    public String getNaam() {
        return naam;
    }

    public void setScore(int score) {
        this.score += score;
    }

    public void scoreOpNul() {
        score = 0;
    }

    public int getScore() {
        return score;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

    // om te kunnen sorteren bij topscores
    @Override
    public int compareTo(Speler o) {
        return this.getScore() - o.getScore();
    }
}
