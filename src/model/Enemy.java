package model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public abstract class Enemy extends Living {

    /** The maximum distance from which the enemy will 'detect' the player. */
    private IntegerProperty detectionRadius;
    /** The position of the entity on creation. Used for patrolling. */
    private double originalX, originalY;

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

    public IntegerProperty detectionRadiusProperty() {

        return this.detectionRadius;

    }

    public int getDetectionRadius() {

        return this.detectionRadius.get();

    }

    public void setDetectionRadius(int radius) {

        this.detectionRadius.set(radius);

    }

    public double getOriginalX() {

        return originalX;
    
    }

    public void setOriginalX(double originalX) {

        this.originalX = originalX;
    
    }

    public double getOriginalY() {

        return originalY;
    
    }

    public void setOriginalY(double originalY) {

        this.originalY = originalY;
    
    }

}
