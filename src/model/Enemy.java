package model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

/** Descendant class from Living that is hostile towards the player. */
public abstract class Enemy extends Living {

    /** The maximum distance from which the enemy will 'detect' the player. */
    private IntegerProperty detectionRadius;
    /** The position of the entity on creation. Used for patrolling. */
    private double originalX, originalY;

    /**
     * Descendant class from Living that is hostile towards the player.
     * @param width the width of the enemy
     * @param height the height of the enemy
     */
    public Enemy(int width, int height) {

        super(width, height);

        this.detectionRadius = new SimpleIntegerProperty();
        
    }

    // Abstract methods
    /** Choose the direction for the entity to move in, and when to attack. Pretty much the ai. */
    public abstract void navigate();

    /**
     * Whether or not the player is within detection range.
     * @return true if player is in detection range
     */
    public boolean foundPlayer() {

        return Math.hypot(this.getX() - World.instance().getPlayer().getX(), this.getY() - World.instance().getPlayer().getY()) <= this.getDetectionRadius();

    }

    /// Getters and Setters ///
    /**
     * Gets the property for the enemy's detection radius.
     * @return the detection radius property
     */
    public IntegerProperty detectionRadiusProperty() {

        return this.detectionRadius;

    }

    /**
     * Gets the value of the enemy's detection radius.
     * @return the detection radius value
     */
    public int getDetectionRadius() {

        return this.detectionRadius.get();

    }

    /**
     * Sets the value of the enemy's detection radius.
     * @param radius the new detection radius value
     */
    public void setDetectionRadius(int radius) {

        this.detectionRadius.set(radius);

    }

    /**
     * Gets the original x position of the enemy.
     * @return the original x value
     */
    public double getOriginalX() {

        return originalX;
    
    }

    /**
     * Sets the original x position of the enemy.
     * @param originalX the new x value
     */
    public void setOriginalX(double originalX) {

        this.originalX = originalX;
    
    }

    /**
     * Gets the original y position of the enemy.
     * @return the original y value
     */
    public double getOriginalY() {

        return originalY;
    
    }

    /**
     * Sets the original y position of the enemy.
     * @param originalY the new y value
     */
    public void setOriginalY(double originalY) {

        this.originalY = originalY;
    
    }

}
