package model;

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
     * @return nearest interactable entity or null
     */
    public Entity interact() {

        var list = World.instance().getEntityList().stream().filter(e -> e.isInteractable()).toList();
        Entity closest = null;
        double closeDistance = 95;

        for (Entity entity : list) {

            double distance = Math.sqrt((entity.getX() - getX()) * (entity.getX() - getX()) + Math.sqrt((entity.getY() - getY()) * (entity.getY() - getY())));

            if (distance < closeDistance) {

                closest = entity;
                closeDistance = distance;

            }

        }

        return closest;

    }


    /// Methods from Living ///

    @Override
    public void move() {}

    @Override
    public void attack(int damage) {}

    @Override
    public void handleDamage(int damage) {}

    @Override
    public void handleDeath() {}


    /// Methods from Entity ///

    @Override
    public void action() {}

    @Override
    public String serialize() { return null; }

    @Override
    public EntityType getType() {

        return EntityType.PLAYER;

    }
    
}
