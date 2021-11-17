package model;

import java.util.Random;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class NPC extends Entity {

    private String message;


    public NPC(String message) {
        Random num = new Random();
        speed = new SimpleDoubleProperty(0);
        x = new SimpleDoubleProperty(num.nextInt(900));
        y = new SimpleDoubleProperty(num.nextInt(400));

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
