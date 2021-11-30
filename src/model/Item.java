package model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Item extends NPC {

    private IntegerProperty scoreIncrease;

    public Item(String message, String description, int score) {

        super(message, description);

        this.scoreIncrease = new SimpleIntegerProperty(score);

    }

    @Override
    public String serialize() {

        return "" + getType() + "::" + getMessage().replaceAll("\n", "█") + "::" + scoreIncrease.get() + "\n";

    }

    @Override
    public EntityType getType() {
        
        return EntityType.ITEM;
    
    }

    /// Getters and Setters ///

    public IntegerProperty scoreIncreaseProperty() {

        return this.scoreIncrease;

    }

    public int getScoreIncrease() {

        return this.scoreIncrease.get();

    }

    public void setScoreIncrease(int value) {

        this.scoreIncrease.set(value);
        
    }

}
