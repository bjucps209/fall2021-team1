package model;

class Player extends Entity {


    public Player() {
        super();
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
    public void handleDamage(int damage) {

    }

    /**
     * Moves the player based on their direction and speed.
     */
    public void move() {
        throw new RuntimeException("Method not implemented");
    }

    /**
     * Calls an enemies handle damage method if the enemy is in range of the players attack.
     * @param damage - the damage the player inflicts
     */
    public void handleAttack(int damage) {
        throw new RuntimeException("Method not implemented");
    }

    /**
     * Changes the location if the player reaches the edges of the map.
     */
    public void changeLocation() {

    }


    
}