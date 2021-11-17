package model;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;

public abstract class Living extends Entity {

    private IntegerProperty health;
    private IntegerProperty maxHealth;
    private IntegerProperty damage;
    private DoubleProperty speed;
    private IntegerProperty direction;

    public Living(int width, int height) {

        super(width, height);

        this.health = new SimpleIntegerProperty(3);
        this.maxHealth = new SimpleIntegerProperty(5);
        this.damage = new SimpleIntegerProperty(1);
        this.speed = new SimpleDoubleProperty(1);
        this.direction = new SimpleIntegerProperty();

    }

    // Override these methods and any methods from Entity
    public abstract void move();
    public abstract void attack();
    public abstract void handleDamage(int damage);
    public abstract void handleDeath();

    /// Getters and Setters ///

    public IntegerProperty healthProperty() {

        return this.health;

    }

    public int getHealth() {

        return this.health.get();

    }

    public void setHealth(int health) {

        this.health.set(health);

    }

    public IntegerProperty maxHealthProperty() {

        return this.maxHealth;

    }

    public int getMaxHealth() {

        return this.maxHealth.get();

    }

    public void setMaxHealth(int health) {

        this.maxHealth.set(health);

    }

    public IntegerProperty damageProperty() {

        return this.damage;

    }

    public int getDamage() {

        return this.damage.get();

    }

    public void setDamage(int damage) {

        this.damage.set(damage);

    }

    public DoubleProperty speedProperty() {

        return this.speed;

    }

    public double getSpeed() {

        return this.speed.get();

    }

    public void setSpeed(double speed) {

        this.speed.set(speed);

    }

    public IntegerProperty directionProperty() {

        return this.direction;

    }

    public int getDirection() {

        return this.direction.get();

    }

    public void setDirection(int direction) {

        this.direction.set(direction);

    }
    
}
