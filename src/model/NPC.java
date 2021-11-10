package model;

public class NPC extends Entity {

    private String message;


    public NPC(EntityType entityType) {
        super(entityType);
        //TODO Auto-generated constructor stub
    }
 
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    
}
