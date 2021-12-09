package model;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;

/** Basic class for all entities with health or movement properties. */
public abstract class Living extends Entity {

    /** The health of the Entity. */
    private IntegerProperty health;
    /** The maximum health of the Entity. */
    private IntegerProperty maxHealth;
    /** The damage the Entity will do */
    private IntegerProperty damage;
    /** The rate at which the Entity will move. */
    private DoubleProperty speed;
    /** The direction the Entity is facing, in degrees. */
    private IntegerProperty direction;
    /** Invincibility frames on hit. */
    protected int iFrames;
    /** Is the Entity dead? */
    private boolean isDead = false;

    /**
     * Basic class for all entities with health or movement properties.
     * @param width the width of the entity
     * @param height the height of the entity
     */
    public Living(int width, int height) {

        super(width, height);

        this.setCollidable(false);
        this.health = new SimpleIntegerProperty(3);
        this.maxHealth = new SimpleIntegerProperty(5);
        this.damage = new SimpleIntegerProperty(1);
        this.speed = new SimpleDoubleProperty(1);
        this.direction = new SimpleIntegerProperty();

    }

    /**
     * Attempts to move the entity.
     * @param speed the speed to move at
     * @param direction the direction to move in
     * @return true if there is a collision, false otherwise
     */
    public boolean move(double speed, int direction) {

        var obstacles = World.instance().getCurrentlocation().getObjectList().stream().filter(e -> (e.isCollidable())).toList();

        for (double i = 0; i < getSpeed(); i += 0.1) {

            double oldX = getX();
            double oldY = getY();

            double newX = oldX + 0.1 * Math.cos(direction * Math.PI / 180);
            double newY = oldY + 0.1 * Math.sin(direction * Math.PI / 180);

            setPosition(newX, newY);

            for (Entity obstacle : obstacles) {

                if (intersects(obstacle)) {

                    setPosition(oldX, oldY);
                    return true;

                }

            }

        }

        return false;

    }

    /** Make dead if applicable. */
    public void handleDeath() {

        if (this.getHealth() <= 0) {

            this.setDead(true);

        }

    }

    // Override these methods and any methods from Entity
    /** Attempt to do harm. */
    public abstract void attack();
    
    /** Take damage and make temporarily immune. */
    public abstract void handleDamage(int damage, int direction);

    /// Getters and Setters ///
    /**
     * Gets the property for the entity's health.
     * @return the health property
     */
    public IntegerProperty healthProperty() {

        return this.health;

    }

    /**
     * Gets the entity's health value.
     * @return the health value
     */
    public int getHealth() {

        return this.health.get();

    }

    /**
     * Sets the entity's health value.
     * @param health the new health value
     */
    public void setHealth(int health) {

        this.health.set(health);

    }

    /**
     * Gets the property of the entity's maximum health.
     * @return the max health property
     */
    public IntegerProperty maxHealthProperty() {

        return this.maxHealth;

    }

    /**
     * Gets the value of the entity's maximum health.
     * @return the max health value
     */
    public int getMaxHealth() {

        return this.maxHealth.get();

    }

    /**
     * Sets the value of the entity's maximum health.
     * @param health the new max health value
     */
    public void setMaxHealth(int health) {

        this.maxHealth.set(health);

    }

    /**
     * Gets the property for the damage the entity does.
     * @return the damage property
     */
    public IntegerProperty damageProperty() {

        return this.damage;

    }

    /**
     * Gets the value of the damage the entity does.
     * @return the damage value
     */
    public int getDamage() {

        return this.damage.get();

    }

    /**
     * Sets the value of the damage the entity does.
     * @param damage the new damage value
     */
    public void setDamage(int damage) {

        this.damage.set(damage);

    }

    /**
     * Gets the property for the entity's speed.
     * @return the speed property
     */
    public DoubleProperty speedProperty() {

        return this.speed;

    }

    /**
     * Gets the speed value of the entity.
     * @return the speed value
     */
    public double getSpeed() {

        return this.speed.get();

    }

    /**
     * Sets the speed value of the entity.
     * @param speed the speed value
     */
    public void setSpeed(double speed) {

        this.speed.set(speed);

    }

    /**
     * Gets the property of the entity's direction.
     * @return the direction property
     */
    public IntegerProperty directionProperty() {

        return this.direction;

    }

    /**
     * Gets the direction value of the entity.
     * @return the direction value
     */
    public int getDirection() {

        return this.direction.get();

    }

    /**
     * Sets the direction value of the entity.
     * @param direction the new direction value
     */
    public void setDirection(int direction) {

        this.direction.set(direction);

    }

    /**
     * Whether the entity has died.
     * @return true if dead, false otherwise
     */
    public boolean isDead() {
    
        return isDead;
    
    }

    /**
     * Sets whether the entity is dead. Also removes the entity from the screen and prevents it from moving.
     * @param isDead true for dead, false otherwise
     */
    public void setDead(boolean isDead) {

        this.isDead = isDead;
        this.setSpeed(0);
        this.setPosition(-500, -500);
    
    }
    
}
