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
        this.setSpeed(1.5);
        this.setDetectionRadius(10);
        state = GruntState.PATROL;
        setPosition(900, 300);

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


    /// Methods from Enemy ///

    @Override
    public String serialize() {

        return "" + getType() + "::" + getX() + "::" + getY() + "::" + state + "\n";

    }

    @Override
    public EntityType getType() {
        
        return EntityType.GRUNT_ENEMY;
    
    }
    
}
