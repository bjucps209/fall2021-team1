package model;

import javafx.beans.property.IntegerProperty;

public class Entity {
    private IntegerProperty health;
    private IntegerProperty x, y;
    private IntegerProperty damage;
    private IntegerProperty speed;
    private IntegerProperty direction;

    private EntityType entityType;
    private DifficultyLevel difficulty;

    private boolean hasCollision;
    private boolean isInteractable;


    public Entity(EntityType entityType) {
        throw new RuntimeException("Method not implemented");
    }

    public String serialize() {

        return "";

    }

}
