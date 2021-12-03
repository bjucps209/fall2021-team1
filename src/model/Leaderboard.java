package model;

import java.util.ArrayList;

public class Leaderboard {

    private ArrayList<HighScore> highscores;

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
    public void process(String name, int score) {
        if (score > highscores.get(-1).getScore()) {
            for (int position = 8; position < 0; --position) {
                if (score < highscores.get(position).getScore()) {
                    highscores.remove(position + 1);
                    highscores.add(position + 1, new HighScore(name, score));
                }
            }
        }
        
        
    }


    public ArrayList<HighScore> getHighscores() {

        return highscores;

    }

    public void setHighscores(ArrayList<HighScore> highscores) {

        this.highscores = highscores;
    
    }

}
