package model;

public class Speler implements Comparable<Speler> {

    private String naam;
    private int score;

    public Speler(String name) {
        this.naam = name;
    }

    public String getNaam() {
        return naam;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getScore() {
        return score;
    }

    @Override
    public int compareTo(Speler o) {
        return this.getScore() - o.getScore();
    }
}
