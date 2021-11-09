package model;

import javafx.beans.property.IntegerProperty;

public class Entity extends GameObject {
    private IntegerProperty health;
    private IntegerProperty x, y;
    private IntegerProperty damage;
    private IntegerProperty speed;
    private IntegerProperty direction;
    private EntityType entityType;
    private boolean hasCollision;


    public Entity(EntityType entityType) {
        throw new RuntimeException("Method not implemented");
    }

    public void move() {
        throw new RuntimeException("Method not implemented");
    }

}
