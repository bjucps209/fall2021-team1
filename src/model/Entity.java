package model;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;

/** Abstract class for all game objects. */
public abstract class Entity {

    /** Position property. */
    private DoubleProperty x, y;
    /** Dimension property. */
    private IntegerProperty width, height;
    /** If the entity can collide with enemies and the player. Only used for stationary entities. */
    private boolean isCollidable = true;
    /** If the entity can be interacted with. Only used for stationary entities. */
    private boolean isInteractable = false;

    /**
     * Abstract class for all game objects.
     * @param width the width of the object
     * @param height the height of the object
     */
    public Entity(int width, int height) {

        // Set up properties
        this.x = new SimpleDoubleProperty();
        this.y = new SimpleDoubleProperty();

        // Set width and height for collision
        this.width = new SimpleIntegerProperty(width);
        this.height = new SimpleIntegerProperty(height);

    }

    /**
     * Deserializes a line of save data containing information about a game object.
     * @param saveLine the string of saved data
     * @return the entity saved, or null if an entity could not be created from the data
     */
    public static Entity deserialize(String saveLine) {

        // Split line into useable data
        String[] data = saveLine.split("::");

        // Generate approprate entity
        switch (stringToType(data[0])) {

            case NPC:
                
                // NPC::_x_::_y_::_message_::_description_
                var npc = new NPC(data[3], data[4]);

                npc.setPosition(Double.parseDouble(data[1]), Double.parseDouble(data[2]));

                return npc;

            case ITEM:

                // ITEM::_x_::_y_::_message_::_description_::_score_
                var item = new Item(data[3], data[4], Integer.parseInt(data[3]));

                item.setPosition(Double.parseDouble(data[1]), Double.parseDouble(data[2]));

                return item;

            case PROJECTILE:

                // PROJECTILE::_x_::_y_::_direction_::_damage_::_speed_
                var project = new Projectile(Double.parseDouble(data[5]), Integer.parseInt(data[3]), Integer.parseInt(data[4]));

                project.setPosition(Double.parseDouble(data[1]), Double.parseDouble(data[2]));

                return project;

            case GRUNT_ENEMY:

                // GRUNT_ENEMY::_original_x_::_original_y_::_x_::_y_::_state_::_health_
                var grunt = new Grunt();

                grunt.setPosition(Double.parseDouble(data[3]), Double.parseDouble(data[4]));
                grunt.setOriginalX(Double.parseDouble(data[1]));
                grunt.setOriginalY(Double.parseDouble(data[2]));
                grunt.setState(data[5]);
                grunt.setHealth(Integer.parseInt(data[6]));

                return grunt;

            case JUGGERNAUT_ENEMY:

                // JUGGERNAUT_ENEMY::_original_x_::_original_y_::_x_::_y_::_state_::_health_
                var jugg = new Juggernaut();

                jugg.setPosition(Double.parseDouble(data[3]), Double.parseDouble(data[4]));
                jugg.setOriginalX(Double.parseDouble(data[1]));
                jugg.setOriginalY(Double.parseDouble(data[2]));
                jugg.setState(data[5]);
                jugg.setHealth(Integer.parseInt(data[6]));

                return jugg;

            case CLOUD_WIZARD:

                // CLOUD_WIZARD::_x_::_y_::_state_::_health_
                var wizard = new Wizard();

                wizard.setPosition(Double.parseDouble(data[1]), Double.parseDouble(data[2]));
                wizard.setState(data[3]);
                wizard.setHealth(Integer.parseInt(data[4]));

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
    /** Abstract method. Each descendant of Entity must implement it to return a string of save data representing itself. */
    public abstract String serialize();
    /** Abstract method. Each descendant of Entity must implement it to return its own EntityType. */
    public abstract EntityType getType();

    
    /// Getters and Setters ///
    /**
     * Set the world position of the entity. To move the entity around the world, the move()
     * method is reccomended instad of setPosition(), which does not consider collision.
     * @param x the new x position
     * @param y the new y position
     */
    public void setPosition(double x, double y) {

        this.x.set(x);
        this.y.set(y);

    }
    
    /**
     * Gets the property for the entity's x postion.
     * @return the x property
     */
    public DoubleProperty xProperty() {

        return this.x;

    }

    /**
     * Gets the value of the entity's x position.
     * @return the x value
     */
    public double getX() {

        return this.x.get();

    }

    /**
     * Sets the value of the entity's x postion.
     * @param x the new x position
     */
    public void setX(double x) {

        this.x.set(x);

    }

    /**
     * Gets the property for the entity's y postion.
     * @return the y property
     */
    public DoubleProperty yProperty() {

        return this.y;

    }

    /**
     * Gets the value of the entity's y position.
     * @return the y value
     */
    public double getY() {

        return this.y.get();

    }

    /**
     * Sets the value of the entity's y postion.
     * @param x the new y position
     */
    public void setY(double y) {

        this.y.set(y);

    }

    /**
     * Sets the dimensions of the entity. Dimensions are used in collision detection.
     * @param width the new width
     * @param height the new height
     */
    public void setDimensions(int width, int height) {

        this.setWidth(width);
        this.setHeight(height);

    }

    /**
     * Gets the property for the entity's width.
     * @return the width property
     */
    public IntegerProperty widthProperty() {

        return this.width;

    }

    /**
     * Gets the entity's width value.
     * @return the width value
     */
    public int getWidth() {

        return this.width.get();

    }

    /**
     * Sets the entity's width value.
     * @param width the new width
     */
    public void setWidth(int width) {

        this.width.set(width);

    }

    /**
     * Gets the property for the entity's height.
     * @return the height property
     */
    public IntegerProperty heightProperty() {

        return this.height;

    }

    /**
     * Gets the entity's height value.
     * @return the height value
     */
    public int getHeight() {

        return this.height.get();

    }

    /**
     * Sets the entity's height value.
     * @param width the new height
     */
    public void setHeight(int height) {

        this.height.set(height);

    }

    /**
     * Whether the entity can collide with the player or enemies.
     * @return true if collidable, false otherwise
     */
    public boolean isCollidable() {

        return this.isCollidable;

    }

    /**
     * Sets the ability of the entity to collide with the player or enemies.
     * @param state true to allow collision, false otherwise
     */
    public void setCollidable(boolean state) {

        this.isCollidable = state;

    }

    /**
     * Whether the player can interact with the entity.
     * @return true if interactable, false otherwise
     */
    public boolean isInteractable() {

        return this.isInteractable;

    }

    /**
     * Sets the ability of the entity to be interacted with by the player.
     * @param state true to allow interactability, false otherwise
     */
    public void setInteractable(boolean state) {

        this.isInteractable = state;

    }
    
}
