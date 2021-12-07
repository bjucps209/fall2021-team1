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
    public Enemy attack(mapDirection direction) {

        List<Entity> list = World.instance().getEntityList().stream().filter(e -> (e instanceof Enemy)).toList();

        if (list.size() > 0) {

            for (Entity enemy : list) {

                Enemy target = (Enemy) enemy;
                double targetDistance = Math.hypot(this.getX() - target.getX(), this.getY() - target.getY());

                // NOTE: Currently assumes that all creatures are the same size.
                if (targetDistance < 128) {

                    switch (direction) {

                        case UP:
                            
                            if (target.getY() - 1 < this.getY() - this.getWidth() / 2) return null;
                            target.handleDamage(getDamage(), 90);
                            break;

                        case DOWN:
                            
                            if (target.getY() + 1 > this.getY() + this.getWidth() / 2) return null;
                            target.handleDamage(getDamage(), 270);
                            break;

                        case LEFT:
                            
                            if (target.getX() + 1 > this.getX() + this.getHeight() / 2) return null;
                            target.handleDamage(getDamage(), 180);
                            break;

                        case RIGHT:
                            
                            if (target.getX() - 1 < this.getX() - this.getHeight() / 2) return null;
                            target.handleDamage(getDamage(), 0);
                            break;
                    
                        default: // Default case so Code will be happy.

                            break;

                    }

                    return target;

                }

            }

        }

        return null;

    }

    @Override
    public void handleDamage(int damage, int direction) {

        this.move(100, direction);
        this.setHealth(this.getHealth() - damage);

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
