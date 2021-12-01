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
    public void attack(int damage) {}

    @Override
    public void handleDamage(int damage) {
        this.setHealth(this.getHealth() - damage);
    }

    @Override
    public void handleDeath() {
        if (this.getHealth() <= 0) {
            this.setDead(true);
        }
    }

    @Override
    public void navigate() {
        
        
    }


    /// Methods from Enemy ///

    @Override
    public String serialize() {

        return "" + getType() + "::" + getId() + "::" + getX() + "::" + getY() + "::" + state + "\n";

    }

    @Override
    public EntityType getType() {
        
        return EntityType.GRUNT_ENEMY;
    
    }

    /// Getters and Setters ///

    public GruntState getState() {

        return this.state;

    }

    public void setState(GruntState state) {

        this.state = state;
        
    }

    public void setState(String state) {

        switch (state) {

            case "PATROL":
                
                this.state = GruntState.PATROL;
                break;
        
            default:

                this.state = GruntState.ATTACK;
                break;
                
        }

    }
    
}
