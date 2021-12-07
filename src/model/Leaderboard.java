package model;

import java.util.ArrayList;

public class Leaderboard {

    private ArrayList<HighScore> highscores;
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

        // Crazy solution
        // highscores.add(new HighScore(name, score));
        // highscores.stream().sorted((s1, s2) -> s1.getScore());
        // highscores = new ArrayList<>(highscores.subList(0, 9));

        // Tame Solution
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
