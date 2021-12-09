package model;

import java.util.ArrayList;

public class Leaderboard {

    // List of Highscores in order from greatest to least
    private ArrayList<HighScore> highscores;

    // Booelan to tell whether the last score processed was a high score or not
    private boolean isHighScore;

    /**
     * Creates a new leaderboard with the given score list.
     * @param scores a list of HighScore objects
     */
    public Leaderboard(ArrayList<HighScore> scores) {

        this.highscores = scores;

    }

    /**
     * Compares the provided score information to the saved scores and adds it if it is higher.
     * @param name the player name
     * @param score the score
     */
    public ArrayList<HighScore> process(String name, int score) {
        setHighScore(false);
        for (int position = 0; position < 10; ++position) {
            if (score >= highscores.get(position).getScore()) {
                highscores.remove(9);
                highscores.add(position, new HighScore(name, score));
                setHighScore(true);
                return highscores;
            }
        }
        return highscores;
    }


    public ArrayList<HighScore> getHighscores() {

        return highscores;

    }

    public void setHighscores(ArrayList<HighScore> highscores) {

        this.highscores = highscores;
    
    }

    public boolean isHighScore() {
        return isHighScore;
    }

    public void setHighScore(boolean isHighScore) {
        this.isHighScore = isHighScore;
    }

    

}
