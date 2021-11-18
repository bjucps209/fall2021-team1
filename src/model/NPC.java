package model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class NPC extends Entity {

    private StringProperty message;

    public NPC(String message) {
        
        // TODO: use actual dimensions
        super(10, 10);

        this.message = new SimpleStringProperty(message);
        this.setInteractable(true);
        setPosition(300, 300);
        
    }

    /// Methods from Entity ///

    @Override
    public String serialize() {

        return "" + getType() + "::" + getMessage() + "\n";

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
    
}
