package model;

import java.util.List;

public class Player extends Living {

    public Player() {

        // TODO: use actual width, height of player
        super(10, 10);

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

        var list = World.instance().getEntityList().stream().filter(e -> e.isInteractable()).toList();
        Entity closest = null;
        double closeDistance = 95;

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

    @Override
    public void move() {
    }

    @Override
    public void attack(int damage) {
        List<Entity> list = World.instance().getEntityList().stream().filter(e -> e.getType() == EntityType.GRUNT_ENEMY)
                .toList();
        double distance = 0;
        double smallestDistance = 1000000000;

        if (list.size() > 0) {
            for (Entity enemy : list) {

                Grunt grunt = (Grunt) enemy;

                distance = Math.sqrt((grunt.getX() - getX()) * (grunt.getX() - getX())
                        + Math.sqrt((grunt.getY() - getY()) * (grunt.getY() - getY())));

                if (distance < smallestDistance) {

                    smallestDistance = distance;
                    double theta = Math.atan2(grunt.getY() - getY(), grunt.getX() - getX());
                    double angle = Math.toDegrees(theta);

                    if (angle < 0) {
                        angle += 360;
                    }
                    if ((angle <= (getDirection() + 90)) || (angle >= (getDirection() - 90)) && smallestDistance <= 100) {
                        grunt.handleDamage(damage);
                        if (grunt.isDead()) {
                            World.instance().setScore(World.instance().getScore() + 100);
                        }
                    }
                }

            }
        }
    }

    @Override
    public void handleDamage(int damage) {
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
        return null;
    }

    @Override
    public EntityType getType() {

        return EntityType.PLAYER;

    }

}
