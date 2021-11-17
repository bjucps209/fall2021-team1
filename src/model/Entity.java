package model;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Entity {

    protected IntegerProperty health;
    protected DoubleProperty x, y;
    protected IntegerProperty damage;
    protected IntegerProperty speed;
    protected IntegerProperty direction;
    protected boolean hasCollision;
    protected boolean isInteractable;

    public Entity() {

        this.health = new SimpleIntegerProperty(0);
        this.x = new SimpleDoubleProperty();
        this.y = new SimpleDoubleProperty();
        this.damage = new SimpleIntegerProperty(0);
        this.speed = new SimpleIntegerProperty(0);
        this.direction = new SimpleIntegerProperty();

        this.hasCollision = true;
        this.isInteractable = false;

    }

    /**
     * Moves the entity based on its state, speed, and direction. 
     */
    public void move() {

        this.x.setValue(x.get() + (int) (speed.get() * Math.cos(direction.get() * Math.PI / 180)));
        this.y.setValue(y.get() + (int) (speed.get() * Math.cos(direction.get() * Math.PI / 180)));

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

    /// Getters and Setters ///

    public EntityType getType() {

        return EntityType.ITEM;

    }

    public IntegerProperty healthProperty() {

        return this.health;

    }

    public int getHealth() {

        return this.health.get();

    }

    public void setHealth(int health) {

        this.health.setValue(health);

    }

    public DoubleProperty xProperty() {

        return this.x;

    }


    public void setPosition(double x, double y) {

        this.x.setValue(x);
        this.y.setValue(y);

    }

    public IntegerProperty damageProperty() {

        return this.damage;

    }

    public int getDamage() {

        return this.damage.get();

    }

    public void setDamage(int damage) {

        this.damage.setValue(damage);

    }

    public IntegerProperty speedProperty() {

        return this.speed;

    }

    public int getSpeed() {

        return this.speed.get();

    }

    public void setSpeed(int speed) {

        this.speed.setValue(speed);

    }

    public IntegerProperty directionProperty() {

        return this.direction;

    }

    public int getDirection() {

        return this.direction.get();

    }

    public void setDirection(int direction) {

        this.direction.setValue(direction);

    }

    public boolean canCollide() {

        return this.hasCollision;

    }

    public void setCollision(boolean state) {

        this.hasCollision = state;

    }

    public boolean isInteractable() {

        return this.isInteractable;

    }

    public void setInteractable(boolean state) {

        this.isInteractable = state;

    }

    
    public double getX() {
        return x.get();
    }

    public void setX(double x) {
        this.x.set(x);
    }

    public DoubleProperty x() {
        return x;
    }

    public double getY() {
        return y.get();
    }

    public void setY(double y) {
        this.y.set(y);
    }

    public DoubleProperty y() {
        return y;
    }

}
