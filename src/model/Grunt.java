package model;

public class Grunt extends Enemy {

    public enum GruntState {

        PATROL, ATTACK

    }

    private GruntState state;

    public Grunt() {

        // TODO: use actual width, height
        super(10, 10);

        // Load base stats
        this.setMaxHealth(3);
        this.setHealth(3);
        this.setDamage(1);
        this.setSpeed(2);
        this.setDetectionRadius(10);

    }


    /// Methods from Living ///
    
    @Override
    public void move() {}

    @Override
    public void attack() {}

    @Override
    public void handleDamage(int damage) {}

    @Override
    public void handleDeath() {}


    /// Methods from Enemy ///

    @Override
    public void action() {}

    @Override
    public String serialize() { return null; }

    @Override
    public EntityType getType() {
        
        return EntityType.GRUNT_ENEMY;
    
    }
    
}
