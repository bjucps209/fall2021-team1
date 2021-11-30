package model;

import java.util.ArrayList;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class World {

    private ArrayList<Entity> entityList;
    private String currentlocation;
    private IntegerProperty score;
    private DifficultyLevel difficulty;
    private Player player;

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
        currentlocation = ZoneList.instance().getCurrentZone().getZoneName();

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
                }
                player.handleDeath();
            }
        }
    }

    /**
     * Places items such as buildings and trees in their appropriate locations.
     */
    public void placeItems() {

    }

    /**
     * Spawns enemies in the current world location of the player.
     */
    public void spawnEnemies() {
        entityList.add(new Grunt());
    }

    
    /// Serialization ///

    public String serialize() {

        return "WORLD::" + getDifficulty() + "::" + getCurrentlocation() + "::" + getScore() + "\n";

    }


    /// Getters and Setters ///

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

    public String getCurrentlocation() {

        return currentlocation;

    }

    public void setCurrentlocation(String currentlocation) {

        this.currentlocation = currentlocation;

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
