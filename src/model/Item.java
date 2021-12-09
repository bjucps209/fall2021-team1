package model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

/** Specialized npc that also increases the player's score when interacted with. */
public class Item extends NPC {

    /** The ammount the player's score should increase by on interaction. */
    private IntegerProperty scoreIncrease;

    /**
     * Specialized npc that also increases the player's score when interacted with.
     * @param message the message to display on interaction
     * @param description a description of the npc
     * @param score the score to increase by
     */
    public Item(String message, String description, int score) {

        super(message, description);

        this.setCollidable(true);
        this.scoreIncrease = new SimpleIntegerProperty(score);
        this.setInteractable(false);

    }

    @Override
    public String serialize() {

        return "" + getType() + "::" + getX() + "::" + getY() + "::" + getMessage().replaceAll("\n", "█") + "::" + getDescription() + "::" + scoreIncrease.get() + "\n";

    }

    @Override
    public EntityType getType() {
        
        return EntityType.ITEM;
    
    }

    /// Getters and Setters ///
    /**
     * Gets the property of the item's score increase.
     * @return the score increase property
     */
    public IntegerProperty scoreIncreaseProperty() {

        return this.scoreIncrease;

    }

    /**
     * Gets the value of the item's score increase.
     * @return the score increase value
     */
    public int getScoreIncrease() {

        return this.scoreIncrease.get();

    }

    /**
     * Sets the value of the item's score increase.
     * @param value the new score increase value
     */
    public void setScoreIncrease(int value) {

        this.scoreIncrease.set(value);
        
    }

}
