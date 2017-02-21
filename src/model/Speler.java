package model;

public class Speler implements Comparable<Speler> {

    private String naam;
    private static int score = 0;

    public Speler(String name) {
        this.naam = name;
    }

    public Speler() {
        // constructor voor bord model, zodat we score kunnen instellen
    }

    public String getNaam() {
        return naam;
    }

    public void setScore(int score) {
        Speler.score += score;
    }

    public int getScore() {
        return score;
    }

    // om te kunnen sorteren bij topscores
    @Override
    public int compareTo(Speler o) {
        return this.getScore() - o.getScore();
    }

    // toString om te kunnen schrijven/lezen van het "highscores.txt" bestand
    @Override
    public String toString() {
        return String.format("%-40s %d", this.getNaam(), this.getScore());
    }
}
