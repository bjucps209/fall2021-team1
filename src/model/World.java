package model;

import java.util.ArrayList;

import javafx.beans.property.IntegerProperty;

class World {
    
    private ArrayList<Entity> entityList;
    private int currentlocation;
    private IntegerProperty score;

    public World() {
        entityList = new ArrayList<Entity>();
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

    }

    /**
     * Changes the location if the player reaches the edges of the map.
     */
    public void changeLocation() {

    }

    /**
     * Saves the game to a text file.
     */
    private void save() {


    }

    /**
     * Loads the game from a text file.
     */
    private void load() {


    }

    /**
     * Translates a line of save data.
     * @param data the line of data
     * @return a new entity with the saved properties
     */
    private Entity deserialize(String data) {


    }

}