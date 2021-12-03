package model;

import java.util.ArrayList;

public class Leaderboard {

    private Leaderboard instance;
    private ArrayList<HighScore> highscores;

    /**
     * Creates a new leaderboard with the given score list.
     * @param scores a list of HighScore objects
     */
    private Leaderboard(ArrayList<HighScore> scores) {

        this.highscores = scores;

    }

    /**
     * Compares the provided score information to the saved scores and adds it if it is higher.
     * @param name the player name
     * @param score the score
     */
    public void process(String name, int score) {

        highscores.add(new HighScore(name, score));
        highscores.stream().sorted((s1, s2) -> s1.getScore());
        highscores = new ArrayList<>(highscores.subList(0, 9));
        
    }

    /**
     * Returns the single instance of the leaderboard.
     * @return the leaderboard
     */
    public Leaderboard getInstance() {

        return this.instance;

    }

    public ArrayList<HighScore> getHighscores() {

        return highscores;

    }

    public void setHighscores(ArrayList<HighScore> highscores) {

        this.highscores = highscores;
    
    }

}
