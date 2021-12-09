package model;

public class Projectile extends Enemy {

    public Projectile(double speed, int direction, int damage) {
        
        super(64, 64);
        this.setDamage(damage);
        this.setSpeed(speed);
        this.setDirection(direction);

    }

    /// Methods from Enemy ///

    /** Moves the projectile. */
    @Override
    public void navigate() {
        
        // Dead projectiles do nothing.
        if (isDead()) return;

        // Die on first impact
        if (move(getSpeed(), getDirection())) setDead(true);

        // Check hit player
        var player = World.instance().getPlayer();
        if (intersects(player)) {
            
            player.handleDamage(getDamage(), getDirection());
            setDead(true);
        
        }

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

        return "" + getType() + "::" + getX() + "::" + getY() + "::" + getDirection() + "::" + getDamage() + "::" + getSpeed() + "\n";

    }

    @Override
    public EntityType getType() {
        
        return EntityType.PROJECTILE;

    }

}