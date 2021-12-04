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

        // Crazy solution
        // highscores.add(new HighScore(name, score));
        // highscores.stream().sorted((s1, s2) -> s1.getScore());
        // highscores = new ArrayList<>(highscores.subList(0, 9));

        // Tame Solution
        if (score > highscores.get(9).getScore()) {
            if (score > highscores.get(0).getScore()) {
                highscores.remove(0);
                highscores.add(0, new HighScore(name, score));
            } else if (score < highscores.get(8).getScore()) {
                highscores.remove(highscores.get(highscores.size() - 1));
                highscores.add(new HighScore(name, score));
            } else {
                for (int position = 8; position > -1; --position) {
                    if (score < highscores.get(position).getScore()) {
                        highscores.remove(position + 1);
                        highscores.add(position + 1, new HighScore(name, score));
                    }    
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
