package model;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Entity {

    private IntegerProperty health;
    private DoubleProperty x, y;
    private IntegerProperty damage;
    private IntegerProperty speed;
    private IntegerProperty direction;

    //private DifficultyLevel difficulty;

    private boolean hasCollision;
    private boolean isInteractable;


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
    public void move() {}

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

    public double getX() {

        return this.x.get();

    }

    public void setX(double x) {

        this.x.setValue(x);

    }

    public DoubleProperty yProperty() {

        return this.y;

    }

    public double getY() {

        return this.y.get();

    }

    public void setY(double y) {

        this.y.setValue(y);

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

}
