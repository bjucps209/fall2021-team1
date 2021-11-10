package model;

class Player extends Entity {

    EntityType entityType;

    public Player(EntityType entityType) {
        super(entityType);
    }

    /**
     * Handles the logic when the player dies
     */
    public void handleDeath() {

    }

    /**
     * Decrements the player's health upon an enemy hit.
     * @param damage - the amount the player's health is decremented by
     */
    public void handleDamage(double damage) {

    }

    /**
     * Moves the player based on their direction and speed.
     */
    public void move() {
        throw new RuntimeException("Method not implemented");
    }


    
}