package model;

import java.io.IOException;
import java.util.ArrayList;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class World {

    /** The entities currently in the world. */
    private ArrayList<Entity> entityList;
    /** The entities that need to be initialized and added to the world. */
    private ArrayList<Entity> toAdd;
    /** The zone the player is currently in. */
    private Zone currentlocation;
    /** The player's current score. */
    private IntegerProperty score;
    /** The world difficulty. Difficulty level affects enemy speed and spawn rates as well as the player's starting health. */
    private DifficultyLevel difficulty;
    /** The player entity. */
    private Player player;
    /** The leaderboard, used to hold, sort, and save all highscores. */
    private Leaderboard leaderboard;

    /** Indicates a 'cardinal' direction in the world. */
    public enum mapDirection {

        UP, DOWN, LEFT, RIGHT

    }

    /** Singleton class representing the game 'world'. Also contains the player and leaderboard. */
    private World() {

        entityList = new ArrayList<Entity>();
        toAdd = new ArrayList<Entity>();
        player = new Player();
        score = new SimpleIntegerProperty();
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

                if (entity instanceof Living) {

                    var living = (Living) entity;
                    living.handleDeath();

                }

                player.handleDeath();

            }

        }

        if (isGameOver()) {

            leaderboard.process("Fred", getScore());

        }

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

    /**
     * Use this method to safely add projectiles to the world's entity list.
     * @param entity the entity to add
     */
    public void addToPendingList(Entity entity) {

        toAdd.add(entity);

    }

    /**
     * Gets the list of entities that need to be added to the game.
     * @return an entity list containing uninitialized entities
     */
    public ArrayList<Entity> getPending() {

        return this.toAdd;

    }

    /**
     * Get the difficulty level of the world. Difficulty level affects enemy speed and spawn rates as well as the player's starting health.
     * @return the world difficulty enum value
     */
    public DifficultyLevel getDifficulty() {

        return this.difficulty;

    }

    /**
     * Sets the world's difficulty level. Difficulty level affects enemy speed and spawn rates as well as the player's starting health.
     * @param level the desired enum difficulty value
     */
    public void setDifficulty(DifficultyLevel level) {

        this.difficulty = level;

    }

}
