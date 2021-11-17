package model;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Player extends Entity {


    public Player() {
        health = new SimpleIntegerProperty(5);
        speed = new SimpleIntegerProperty(2);
        damage = new SimpleIntegerProperty(1);
        direction = new SimpleIntegerProperty();
        x = new SimpleDoubleProperty();
        y = new SimpleDoubleProperty();

    }

    /**
     * Handles the logic when the player dies
     */
    public void handleDeath() {

    }

    /**
     * Decrements the player's health upon an enemy hit.
     * @param damage - the amount the player's health is decremented by
     */
    public void handleDamage(int damage) {

    }

    /**
     * Calls an enemies handle damage method if the enemy is in range of the players attack.
     * @param damage - the damage the player inflicts
     */
    public void handleAttack(int damage) {
        throw new RuntimeException("Method not implemented");
    }

    /**
     * Changes the location if the player reaches the edges of the map.
     */
    public void changeLocation() {

    }

    // Property getters and setters *****************************************
    public int getHealth() {
        return health.get();
    }

    public void setHealth(int health) {
        this.health.set(health);
    }

    public IntegerProperty health() {
        return health;
    }

    public double getX() {
        return x.get();
    }

    public void setX(int x) {
        this.x.set(x);
    }

    public DoubleProperty x() {
        return x;
    }

    public double getY() {
        return y.get();
    }

    public void setY(int y) {
        this.y.set(y);
    }

    public DoubleProperty y() {
        return y;
    }

    public int getSpeed() {
        return speed.get();
    }

    public void setSpeed(int speed) {
        this.speed.set(speed);
    }

    public IntegerProperty speed() {
        return speed;
    }

    public int getDirection() {
        return direction.get();
    }

    public void setDirection(int direction) {
        this.direction.set(direction);
    }

    public IntegerProperty direction() {
        return direction;
    }
    // ***************************************************************

    
}