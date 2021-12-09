package model;

/** Stores an individual high score value. */
public class HighScore {

    // Player's Name
    private String playerName;

    // Player's Score
    private int score;

    /**
     * Stores an individual high score value.
     * @param playerName the player's name
     * @param score the player's score
     */
    public HighScore(String playerName, int score) {

        this.playerName = playerName;
        this.score = score;

    }

    /**
     * Returns the score data as it will be represented in the high score save file.
     * @return a string containing the score
     */
    public String serialize() {

        return "" + getPlayerName() + "::" + getScore() + "\n";

    }

    /**
     * Gets the name of the player who achieved this score.
     * @return the player's name
     */
    public String getPlayerName() {

        return playerName;

    }

    /**
     * Sets the name of the player who achieved this score.
     * @param playerName the new name
     */
    public void setPlayerName(String playerName) {

        this.playerName = playerName;

    }

    /**
     * Gets the score value.
     * @return the score value
     */
    public int getScore() {

        return score;

    }

    /**
     * Sets the score value.
     * @param score the new score value
     */
    public void setScore(int score) {

        this.score = score;
        
    }

}