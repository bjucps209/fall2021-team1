package model;

import java.util.List;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;

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
    /** Is the Entity dead? */
    private boolean isDead = false;

    public Living(int width, int height) {

        super(width, height);

        this.health = new SimpleIntegerProperty(3);
        this.maxHealth = new SimpleIntegerProperty(5);
        this.damage = new SimpleIntegerProperty(1);
        this.speed = new SimpleDoubleProperty(1);
        this.direction = new SimpleIntegerProperty();

    }

    /**
     * Attempts to move the entity.
     * @param direction the direction to move in
     * @return true if the move was successful, false otherwise
     */
    public boolean move(int direction) {

        double oldX = getX();
        double oldY = getY();

        double newX = getX() + getSpeed() * Math.cos(direction * Math.PI / 180);
        double newY = getY() + getSpeed() * Math.sin(direction * Math.PI / 180);

        /* Bounds unimplemented
        int w = World.instance().getWidth();
        int h = World.instance().getHeight();
        
        if (newX < 0 || newX > w || newY < 0 || newY > h) return false;
        */

        setPosition(newX, newY);

        List<Entity> obstacles = World.instance().getEntityList().stream().filter(e -> (e.isCollidable())).toList();
        if (obstacles.size() < 1) return true;

        for (Entity obstacle : obstacles) {

            if (intersects(obstacle)) {
                
                setPosition(oldX, oldY);
                return false;

            }

        }
        
        return true;

    }

    // Override these methods and any methods from Entity
    public abstract void attack();
    public abstract boolean handleDamage(int damage);
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

    public boolean isDead() {
    
        return isDead;
    
    }

    public void setDead(boolean isDead) {

        this.isDead = isDead;
    
    }

    
    
}
