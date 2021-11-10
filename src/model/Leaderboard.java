package model;

import java.util.ArrayList;

public class Leaderboard {
    private ArrayList<HighScore> highscores = new ArrayList<HighScore>();
    private HighScore currentScore;

    public Leaderboard(String name, int score) {
        // calls load()
        // calls process()
        // setCurrentScore(HighScore(name, score))

    }

    public void process() {
        // compares currentScore to lowest score in highscores
        // If currrentScore is bigger, add it appropriately to highscores
        // If currentScore is lower, do nothing
    }

    public void load() {
        // loads data from text file into two variables
        // make new HighScore object with the two variables
        // add the Highscore object to highscores
    }

    public void save() {
        // saves the current highscores to text file
    }

    public ArrayList<HighScore> getHighscores() {
        return highscores;
    }

    public void setHighscores(ArrayList<HighScore> highscores) {
        this.highscores = highscores;
    }

    public HighScore getCurrentScore() {
        return currentScore;
    }

    public void setCurrentScore(HighScore currentScore) {
        this.currentScore = currentScore;
    }

}
