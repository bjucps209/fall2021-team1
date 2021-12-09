package model;

public class HighScore {

    // Player's Name
    private String playerName;

    // Player's Score
    private int score;

    public HighScore(String playerName, int score) {

        this.playerName = playerName;
        this.score = score;

    }

    public String serialize() {

        return "" + getPlayerName() + "::" + getScore() + "\n";

    }

    public String getPlayerName() {

        return playerName;

    }

    public void setPlayerName(String playerName) {

        this.playerName = playerName;

    }

    public int getScore() {

        return score;

    }

    public void setScore(int score) {

        this.score = score;
        
    }

}