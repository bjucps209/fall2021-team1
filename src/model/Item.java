package model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Item extends NPC {

    private IntegerProperty scoreIncrease;

    public Item(String message, int score) {

        super(message);

        this.scoreIncrease = new SimpleIntegerProperty(score);

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
