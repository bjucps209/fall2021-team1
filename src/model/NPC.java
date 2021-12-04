package model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;


public class NPC extends Entity {

    private StringProperty message;
    private String description;
    

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

        return "" + getType() + "::" + getMessage().replaceAll("\n", "█") + "\n";

    }

    @Override
    public EntityType getType() {
        
        return EntityType.NPC;
    
    }

    /// Getters and Setters ///

    public StringProperty messageProperty() {

        return this.message;

    }

    public String getMessage() {

        return this.message.get();

    }

    public void setMessage(String message) {

        this.message.set(message);

    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    

    
    
}
