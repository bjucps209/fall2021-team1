package model;

public class Item extends NPC {

    public Item(EntityType entityType) {
        super(entityType);
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    
    
}
