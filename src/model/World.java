package model;

import java.io.IOException;
import java.util.ArrayList;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class World {

    private ArrayList<Entity> entityList;
    private Zone currentlocation;
    private IntegerProperty score;
    private DifficultyLevel difficulty;
    private Player player;
    private Leaderboard leaderboard;

    // Directional Enum
    public enum mapDirection {

        UP, DOWN, LEFT, RIGHT

    }

    // We want the world class to be a singleton so that other classes in the view
    // can
    // access the same world object.
    private World() {

        entityList = new ArrayList<Entity>();
        player = new Player();
        score = new SimpleIntegerProperty();
        difficulty = DifficultyLevel.EASY;
        currentlocation = ZoneList.instance().getLevels().get(0);

        ArrayList<HighScore> dummy = new ArrayList<HighScore>();
        for (int i = 0; i < 10; ++i) {
            dummy.add(new HighScore("Bob", i + 1));
        }
        

        try {
            this.leaderboard = new Leaderboard(Serialization.loadScores("HIGHSCORES.txt"));
        } catch (IOException e) {
            this.leaderboard = new Leaderboard(dummy);
        }
            

    }

    private static World instance = new World(); // create instance to be referenced

    public static World instance() {

        return instance;

    }

    public static void reset() {

        instance = new World();

    }

    /**
     * Keeps track of all of the current enetities in the world's location. Calls
     * various methods to update the game.
     */
    public void updateWorld() {
        if (entityList.size() > 0) {
            for (Entity entity : entityList) {
                if (entity instanceof Grunt) {
                    Grunt grunt = (Grunt) entity;
                    grunt.handleDeath();
                } else if (entity instanceof Juggernaut) {
                    Juggernaut jugg = (Juggernaut) entity;
                    jugg.handleDeath();
                }
                player.handleDeath();
            }
        }
        if (isGameOver()) {
            leaderboard.process("Fred", getScore());
        }
    }

    /**
     * Places items such as buildings and trees in their appropriate locations.
     */
    public void placeItems() {

    }

    /**
     * Spawns enemies in the current world location of the player.
     * @param enemy - the enemy to be added to the entity list
     */
    public void spawnEnemies(Enemy enemy) {
        entityList.add(enemy);
    }
    

    
    /// Serialization ///

    public String serialize() {

        return "WORLD::" + getDifficulty() + "::" + getCurrentlocation().getZoneName() + "::" + getScore() + "\n";

    }


    /// Getters and Setters ///

    public boolean isGameOver() {

        return player.getHealth() <= 0;

    }

    public Player getPlayer() {

        return player;

    }

    public int getScore() {

        return score.get();

    }

    public void setScore(int score) {

        this.score.set(score);

    }

    public void increaseScore(int value) {

        this.score.set(this.score.get() + value);

    }

    public IntegerProperty scoreProperty() {

        return score;

    }

    public Zone getCurrentlocation() {

        return currentlocation;

    }

    public void setCurrentlocation(Zone currentlocation) {

        this.currentlocation = currentlocation;

    }

    public void setCurrentlocation(String zoneName) {

        this.currentlocation = ZoneList.instance().getZone(zoneName);

    }

    public ArrayList<Entity> getEntityList() {

        return entityList;

    }

    public void setEntityList(ArrayList<Entity> entityList) {

        this.entityList = entityList;

    }

    public DifficultyLevel getDifficulty() {

        return this.difficulty;

    }

    public void setDifficulty(DifficultyLevel level) {

        this.difficulty = level;

    }

}
