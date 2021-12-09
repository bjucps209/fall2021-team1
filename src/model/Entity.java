package model;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;

public abstract class Entity {

    private DoubleProperty x, y; // location variables
    private IntegerProperty width, height; // size variables

    private boolean isCollidable = true; // determines if an entity collides with the player or enemies in the game
    private boolean isInteractable = false; // determines if the player can interact with the entity

    public Entity(int width, int height) {

        this.x = new SimpleDoubleProperty();
        this.y = new SimpleDoubleProperty();


        this.width = new SimpleIntegerProperty(width);
        this.height = new SimpleIntegerProperty(height);

    }

    public static Entity deserialize(String saveLine) {

        String[] data = saveLine.split("::");

        switch (stringToType(data[0])) {

            case NPC:
                
                return new NPC(data[1], data[2]);

            case ITEM:

                return new Item(data[1], data[2], Integer.parseInt(data[3]));

            case PROJECTILE:

                var project = new Projectile(Integer.parseInt(data[4]), Integer.parseInt(data[5]));

                project.setDirection(Integer.parseInt(data[3]));
                project.setPosition(Double.parseDouble(data[1]), Double.parseDouble(data[2]));

                return project;

            case GRUNT_ENEMY:

                var grunt = new Grunt();

                grunt.setPosition(Double.parseDouble(data[1]), Double.parseDouble(data[2]));
                grunt.setState(data[3]);

                return grunt;

            case JUGGERNAUT_ENEMY:

                var jugg = new Juggernaut();

                jugg.setPosition(Double.parseDouble(data[1]), Double.parseDouble(data[2]));
                jugg.setState(data[3]);

                return jugg;

            case CLOUD_WIZARD:

                var wizard = new Wizard();

                wizard.setPosition(Double.parseDouble(data[1]), Double.parseDouble(data[2]));
                wizard.setState(data[3]);

                return wizard;

            default:

                return null;

        }

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
                
                return true;

            }

        }

        return false;

    }

    /**
     * Translate a string to the EntityType it represents.
     * @param type the type string
     * @return the string representing type
     */
    private static EntityType stringToType(String type) {

        switch (type) {

            case "PLAYER":
                
                return EntityType.PLAYER;

            case "NPC":
                
                return EntityType.NPC;

            case "ITEM":
                
                return EntityType.ITEM;

            case "GRUNT_ENEMY":
                
                return EntityType.GRUNT_ENEMY;

            case "JUGGERNAUT_ENEMY":

                return EntityType.JUGGERNAUT_ENEMY;

            case "CLOUD_WIZARD":

                return EntityType.CLOUD_WIZARD;
        
            case "PROJECTILE":

                return EntityType.PROJECTILE;

        }

        return null;

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
