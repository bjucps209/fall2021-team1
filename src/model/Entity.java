package model;

import javafx.beans.property.IntergerProperty;

public class Entity extends GameObject {
    private IntegerProperty health;
    private IntegerProperty x, y;
    private IntegerProperty damage;
    private IntegerProperty speed;
    private IntegerProperty direction;
    private EntityType entityType;


    public Entity(EntityType entityType) {
        throw new RuntimeException("Method not implemented");
    }


    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

}
