package model;

public class Projectile extends Enemy {

    public Projectile(int damage, double speed) {
        
        super(128, 128, 0);
        this.setDamage(damage);
        this.setSpeed(speed);

    }

    /// Methods from Enemy ///

    /** Moves the projectile. */
    @Override
    public void navigate() {
        
        if (move(getSpeed(), getDirection())) setDead(true);

    }

    @Override
    public void attack() {

        double distanceToPlayer = Math.hypot(this.getX() - World.instance().getPlayer().getX(), this.getY() - World.instance().getPlayer().getY());

        if (distanceToPlayer < 30) World.instance().getPlayer().handleDamage(this.getDamage(), this.getDirection());

    }

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