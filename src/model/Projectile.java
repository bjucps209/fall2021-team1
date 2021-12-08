package model;

public class Projectile extends Enemy {

    public Projectile(int damage, double speed) {
        
        super(64, 64, 0);
        this.setDamage(damage);
        this.setSpeed(speed);

    }

    /// Methods from Enemy ///

    /** Moves the projectile. */
    @Override
    public void navigate() {
        
        // Die on first impact
        if (move(getSpeed(), getDirection())) setDead(true);

        // Check hit player
        var player = World.instance().getPlayer();
        if (intersects(player)) player.handleDamage(getDamage(), getDirection());

    }

    /** Unused inherited method. */
    @Override
    public void attack() {}

    @Override
    public void handleDamage(int damage, int direction) {

        setDead(true);
    
    }

    @Override
    public String serialize() {

        return "" + getType() + "::" + getId() + "::" + getX() + "::" + getY() + "::" + getDirection() + "::" + getDamage() + "::" + getSpeed() + "\n";

    }

    @Override
    public EntityType getType() {
        
        return EntityType.PROJECTILE;

    }

}