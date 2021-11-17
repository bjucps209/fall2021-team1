package model;

import java.util.Random;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class NPC extends Entity {

    private String message;


    public NPC(String message) {
        Random num = new Random();
        speed = new SimpleIntegerProperty(0);
        x = new SimpleDoubleProperty(num.nextInt(1300));
        y = new SimpleDoubleProperty(num.nextInt(800));

        isInteractable = true;
        this.message = message;
    }
 
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    
}
