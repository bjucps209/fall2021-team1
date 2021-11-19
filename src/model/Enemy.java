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
