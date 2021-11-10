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


    public Entity() {
        throw new RuntimeException("Method not implemented");
    }

    /**
     * Moves the entity based on its state, speed, and direction. 
     */
    public void move() {

    }

    public String serialize() {

        return "";

    }

}
