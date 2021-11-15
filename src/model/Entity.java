package model;

import javafx.beans.property.IntegerProperty;

public class Entity {

    private IntegerProperty health;
    private IntegerProperty x, y;
    private IntegerProperty damage;
    private IntegerProperty speed;
    private IntegerProperty direction;

    private DifficultyLevel difficulty;

    private boolean hasCollision;
    private boolean isInteractable;


    public Entity() {}

    /**
     * Moves the entity based on its state, speed, and direction. 
     */
    public void move() {

    }

    /**
     * Creates and returns a string of data representing the entity.
     * @return the string of data
     */
    public String serialize() {

        return "";

    }

    /**
     * Translates a line of save data.
     * @param data the line of data
     * @return a new entity with the saved properties
     */
    public Entity deserialize(String data) {

        throw new RuntimeException("Cannot deserialize.");

    }

    /**
     * @return the string representing type
     */
    private String typeToString(EntityType type) {

        switch (type) {
            case ENEMY:
                
                return "enemy";

            case PLAYER:
                
                return "player";

            case NPC:
                
                return "npc";
        
            default:

                return "item";

        }

    }

    public EntityType getType() {

        return EntityType.ITEM;

    }

}
