package model;

import java.util.ArrayList;

class World {
    
    private ArrayList<Entity> entityList;
    private String currentlocation;

    public World() {
        entityList = new ArrayList<Entity>();
    }

    public void updateWorld() {
        // For every entity in entity list
        // Check what kind of entity it is
        // If the entity is a moveable type enemy, call its move method
        // If the entity has a collision factor, process in regards to its movement.
        // If the entity has died or been removed, update its properties accordingly
    }
}