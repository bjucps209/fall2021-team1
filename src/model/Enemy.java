package model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public abstract class Enemy extends Living {

    private IntegerProperty id;
    private IntegerProperty detectionRadius;

    public Enemy(int width, int height) {

        super(width, height);

        this.id = new SimpleIntegerProperty();
        this.detectionRadius = new SimpleIntegerProperty();
        
    }


    /**
     * Attempts to move the entity.
     * @param direction the direction to move in
     * @return true if the move was successful, false otherwise
     */
    public boolean move(int direction) {
        double x = this.getX();
        double y = this.getY();
        this.setX(x += this.getSpeed() * Math.cos(this.getDirection() * Math.PI / 180));
        this.setY(y += this.getSpeed() * Math.sin(this.getDirection() * Math.PI / 180));
        return true;

    }


    public abstract void navigate();

    /**
     * Whether or not the player is within detection range.
     * @return true if player is in detection range
     */

    public boolean foundPlayer() {

        // TODO: implement

        return true;

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

        return this.id.get();

    }


    public void setId(int id) {

        this.id.set(id);

    }

}
