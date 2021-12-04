package model;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;

public abstract class Entity {

    private DoubleProperty x, y;
    private IntegerProperty width, height;

    private boolean isCollidable = true;
    private boolean isInteractable = false;

    public Entity(int width, int height) {

        this.x = new SimpleDoubleProperty();
        this.y = new SimpleDoubleProperty();


        this.width = new SimpleIntegerProperty(width);
        this.height = new SimpleIntegerProperty(height);

    }

    /**
     * Checks to see if another entity overlaps with this entity.
     * @param other the other entity
     * @return true if overlapping
     */
    public boolean intersects(Entity other) {

        // Check horizontal collision
        if (this.getX() + this.getWidth() > other.getX() && this.getX() < other.getX() + other.getWidth()) {

            // Check vertical collision
            if (this.getY() + this.getHeight() > other.getY() && this.getY() < other.getY() + other.getHeight()) {

                System.out.println("Intersection");
                return true;

            }

        }
        
        return false;

    }

    // Override these methods
    public abstract String serialize();
    public abstract EntityType getType();

    /// Getters and Setters ///

    public void setPosition(double x, double y) {

        this.x.set(x);
        this.y.set(y);

    }
    
    public DoubleProperty xProperty() {

        return this.x;

    }

    public double getX() {

        return this.x.get();

    }

    public void setX(double x) {

        this.x.set(x);

    }

    public DoubleProperty yProperty() {

        return this.y;

    }

    public double getY() {

        return this.y.get();

    }

    public void setY(double y) {

        this.y.set(y);

    }

    public void setDimensions(int width, int height) {

        this.setWidth(width);
        this.setHeight(height);

    }

    public IntegerProperty widthProperty() {

        return this.width;

    }

    public int getWidth() {

        return this.width.get();

    }

    public void setWidth(int width) {

        this.width.set(width);

    }

    public IntegerProperty heightProperty() {

        return this.height;

    }

    public int getHeight() {

        return this.height.get();

    }

    public void setHeight(int height) {

        this.height.set(height);

    }

    public boolean isCollidable() {

        return this.isCollidable;

    }

    public void setCollidable(boolean state) {

        this.isCollidable = state;

    }

    public boolean isInteractable() {

        return this.isInteractable;

    }

    public void setInteractable(boolean state) {

        this.isInteractable = state;

    }
    
}
