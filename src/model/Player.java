package model;

import java.util.List;

import model.World.mapDirection;

public class Player extends Living {

    public Player() {

        // TODO: use actual width, height of player
        super(72, 116);

        // Load base stats
        this.setMaxHealth(5);
        this.setHealth(5);
        this.setDamage(1);
        this.setSpeed(2.3);

    }

    /**
     * Finds the nearest interactable entity. Returns it if in range.
     * 
     * @return nearest interactable entity or null
     */
    public Entity interact() {

        Zone zone = World.instance().getCurrentlocation();

        var list = zone.getObjectList().stream().filter(e -> e.isInteractable()).toList();
        Entity closest = null;
        double closeDistance = 150;

        for (Entity entity : list) {

            double distance = Math.sqrt((entity.getX() - getX()) * (entity.getX() - getX())
                    + Math.sqrt((entity.getY() - getY()) * (entity.getY() - getY())));

            if (distance < closeDistance) {

                closest = entity;
                closeDistance = distance;

            }

        }

        return closest;

    }

    /// Methods from Living ///

    /**
     * This method is not to be used. It only exists because it must be inherited from Living.
     * Instead, please use the other attack method.
     */
    @Override
    public void attack() {}

    /**
     * Checks if an enemy is within a semicircle of the player's direction, and if the enemy is within a certain distance of the player.
     * If the enemy meets these conditions, the player's damage is passed to the corresponding enemies.
     */
    public void attack(mapDirection direction) {

        List<Entity> list = World.instance().getEntityList().stream().filter(e -> (e instanceof Enemy)).toList();

        if (list.size() > 0) {

            for (Entity enemy : list) {

                Enemy target = (Enemy) enemy;
                boolean hit = false;

                // NOTE: Currently assumes that all creatures are the same size.
                if (Math.hypot(this.getX() - target.getX(), this.getY() - target.getY()) < 128) {

                    switch (direction) {

                        case UP:
                            
                            if (target.getY() < this.getY() - this.getWidth() / 2) return;
                            hit = true;
                            break;

                        case DOWN:
                            
                            if (target.getY() > this.getY() + this.getWidth() / 2) return;
                            hit = true;
                            break;

                        case LEFT:
                            
                            if (target.getX() > this.getX() + this.getHeight() / 2) return;
                            hit = true;
                            break;

                        case RIGHT:
                            
                            if (target.getX() < this.getX() - this.getHeight() / 2) return;
                            hit = true;
                            break;
                    
                        default: // Default case so Code will be happy.

                            break;

                    }

                }

                if (hit == true) {

                    target.handleDamage(getDamage());
                    
                    if (target instanceof Grunt) {

                        switch (direction) {
                            
                            case UP:
                                target.move(100, 90);
                                break;

                            case LEFT:
                                target.move(100, 180);
                                break;
                            
                            case DOWN:
                                target.move(100, 270);
                                break;
                            
                            case RIGHT:
                                target.move(100, 0);
                                break;

                        }
                    }

                }

            }

        }

    }

    @Override
    public boolean handleDamage(int damage) {
        this.setHealth(this.getHealth() - damage);
        if (damage > 0) {
            return true;
        }
        return false;
    }

    @Override
    public void handleDeath() {
        if (this.getHealth() <= 0) {
            this.setDead(true);
        }
    }

    /// Methods from Entity ///

    @Override
    public String serialize() {

        return "" + getType() + "::" + getX() + "::" + getY() + "::" + getHealth() + "::" + getDamage() + "::" + getSpeed() + "\n";

    }

    @Override
    public EntityType getType() {

        return EntityType.PLAYER;

    }

}
