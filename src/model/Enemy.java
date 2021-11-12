package model;

public class Enemy extends Entity {
    
    Enemy() {
        super();
    }

    /**
     * Scans the area for the player within a certain vicinity of the enemy's x and y value.
     * @param x - the player's x coordinate
     * @param y - the player's y coordinate
     * @return
     */
    public boolean scanArea(int x, int y) {
        throw new RuntimeException("Method not implemented");
    }

    /**
     * Moves the enemy based on its speed and direction
     */
    public void move() {
        throw new RuntimeException("Method not implemented");
    }

    /**
     * Decrements the enemies health based on the damage it recieves from the player.
     * @param damage - the damage the player inflicts on the enemy
     */
    public void handleDamage(int damage) {

    }

    /**
     * Calls the players handle damage method if the enemy is in range of the players attack.
     * @param damage - the damage the enemy inflicts
     */
    public void handleAttack(int damage) {
        throw new RuntimeException("Method not implemented");
    }
}