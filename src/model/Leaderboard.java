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
        if (score >= highscores.get(9).getScore()) {
            if (score > highscores.get(0).getScore()) { // Bigger than #1 -> add to top
                highscores.remove(9);
                highscores.add(0, new HighScore(name, score));
                setHighScore(true);
                return highscores;
            } else if (score < highscores.get(8).getScore() && score > highscores.get(9).getScore()) { // Bigger than #10 and smaller than #9 -> remove last score and add new score to list
                highscores.remove(9);
                highscores.add(new HighScore(name, score));
                setHighScore(true);
                return highscores;
            } else if (score == highscores.get(9).getScore()) { // Equal to #10 score -> remove #10 score and add new score to list
                highscores.remove(9);
                highscores.add(new HighScore(name, score));
                setHighScore(true);
                return highscores;
            } else {
                for (int position = 8; position > -1; --position) {
                    if (score == highscores.get(position).getScore()) { // Equal to #i -> remove last score and put new score in #i place
                        highscores.remove(9);
                        setHighScore(true);
                        return highscores;
                    } else if (score < highscores.get(position).getScore()) { // Smaller than #i -> remove last score and put new score in #(i + 1)'s place 
                        highscores.remove(9);
                        highscores.add(position + 1, new HighScore(name, score));
                        setHighScore(true);
                        return highscores;
                    }
                }
            }
        }
        setHighScore(false);
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
