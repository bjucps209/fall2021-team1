package model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public abstract class Enemy extends Living {

    private IntegerProperty id;
    private IntegerProperty detectionRadius;
    private double originalX, originalY;

    public Enemy(int width, int height, int id) {

        super(width, height);

        this.id = new SimpleIntegerProperty(id);
        this.detectionRadius = new SimpleIntegerProperty();
        
    }

    // Abstract methods
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

    public IntegerProperty idProperty() {

        return this.id;

    }

    public int getId() {

        return 0;
        //return this.id.get();

    }


    public void setId(int id) {

        this.id.set(id);

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
