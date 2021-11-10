package model;

class Enemy extends Entity {
    
    EntityType entityType;
  
    Enemy(EntityType entityType) {
        super(entityType);
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
}