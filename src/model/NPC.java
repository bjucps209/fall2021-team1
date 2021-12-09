package model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/** Basic interactable class that displays a text message on interaction. */
public class NPC extends Entity {

    /** The message to display on interaction. */
    private StringProperty message;
    /** A description of the npc. */
    private String description;
    
    /**
     * Basic interactable class that displays a text message on interaction.
     * @param message the message to display on interaction
     * @param description a description of the npc
     */
    public NPC(String message, String description) {
        
        super(10, 10);

        this.setCollidable(false);
        this.message = new SimpleStringProperty(message.replaceAll("█", "\n"));
        this.setInteractable(true);
        this.description = description;
        setPosition(300, 300);
        
    }

    /// Methods from Entity ///
    @Override
    public String serialize() {

        return "" + getType() + "::" + getX() + "::" + getY() + "::" + getMessage().replaceAll("\n", "█") + "\n" + "::" + getDescription();

    }

    @Override
    public EntityType getType() {
        
        return EntityType.NPC;
    
    }

    /// Getters and Setters ///
    /**
     * Get the property for the npc's message string.
     * @return the message property
     */
    public StringProperty messageProperty() {

        return this.message;

    }

    /**
     * Gets the message the npc should display when interacted with.
     * @return the message
     */
    public String getMessage() {

        return this.message.get();

    }

    /**
     * Sets the message the npc should display when interacted with.
     * @param message the new message
     */
    public void setMessage(String message) {

        this.message.set(message);

    }

    /**
     * Gets the description of the npc.
     * @return the description
     */
    public String getDescription() {

        return description;

    }

    /**
     * Sets the npc's description.
     * @param description the new description
     */
    public void setDescription(String description) {

        this.description = description;

    }

}
