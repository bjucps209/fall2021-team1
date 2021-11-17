package model;

import javafx.beans.property.StringProperty;

public class NPC extends Entity {

    private StringProperty message;

    public NPC(String message) {
        
        // TODO: use actual dimensions
        super(10, 10);

        this.message.set(message);
        this.setInteractable(true);
        
    }

    /// Methods from Entity ///

    @Override
    public void action() {}

    @Override
    public String serialize() { return null; }

    @Override
    public EntityType getType() { return null; }

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
    
}
