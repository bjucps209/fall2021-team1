package model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Item extends NPC {

    private IntegerProperty scoreIncrease; // The score bonus upon picking up the item.

    public Item(String message, String description, int score) {

        super(message, description);

        this.setCollidable(true);
        this.scoreIncrease = new SimpleIntegerProperty(score);
        this.setInteractable(false);

    }

    @Override
    public String serialize() {

        return "" + getType() + "::" + getMessage().replaceAll("\n", "█") + "::" + getDescription() + "::" + scoreIncrease.get() + "\n";

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
