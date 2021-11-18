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

    // We want the world class to be a singleton so that other classes in the view can 
    // access the same world object.
    private World() {

        entityList = new ArrayList<Entity>();
        player = new Player();
        score = new SimpleIntegerProperty();
        difficulty = DifficultyLevel.EASY;

    }

    private static World instance = new World(); // create instance to be referenced

    public static World instance() {

        return instance;

    }

    public static void reset() {

        instance = new World();
    
    }

    /**
     * Keeps track of all of the current enetities in the world's location. Calls their move methods if appropriate.
     */
    public void updateWorld() {
        // For every entity in entity list
        // Check what kind of entity it is
        // If the entity is a moveable type enemy, call its move method
        // If the entity has a collision factor, process in regards to its movement.
        // If the entity has died or been removed, update its properties accordingly
        // If the entity has been attacked, update its properties accordingly
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

    /**
     * Saves the game to a text file.
     */
    public void save() {


    }

    /**
     * Loads the game from a text file.
     */
    public void load() {


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
