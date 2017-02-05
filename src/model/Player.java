package model;

/**
 * Created by Jonathan on 05/02/2017.
 */
public class Player {
    private String name;
    private int score;


    public Player(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getScore() {
        return score;
    }
}
