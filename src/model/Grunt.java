package model;

public class Grunt extends Enemy {

    /** Controls the frequency of attacks. */
    private int count = 50;

    public enum GruntState {

        PATROL, ATTACK

    }

    private GruntState state;

    public Grunt() {
        super(128, 128);
        // Load base stats
        this.setMaxHealth(3);
        this.setHealth(3);
        this.setDamage(1);
        this.setSpeed(1.3);
        this.setDetectionRadius(300);
        state = GruntState.PATROL;

    }

    /// Methods from Living ///

    @Override
    public void attack() {

        if (Math.hypot(this.getX() - World.instance().getPlayer().getX(), this.getY() - World.instance().getPlayer().getY()) <= 95) {

            World.instance().getPlayer().handleDamage(this.getDamage(), getDirection());

        }

    }

    @Override
    public boolean handleDamage(int damage, int direction) {

        if (iFrames <= 0) {

            this.iFrames = 10;
            this.move(100, direction);
            this.setHealth(this.getHealth() - damage);
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

    /**
     * Moves the grunt in a certain direction depending on its state.
     */
    @Override
    public void navigate() {

        if (iFrames > 0) {
            
            -- iFrames;
            return;

        }

        if (state != GruntState.ATTACK) determineState();

        double x = this.getX();
        double y = this.getY();

        if (state == GruntState.PATROL) {

            if (x > this.getOriginalX() + 60 || x < this.getOriginalX() - 60) this.setDirection(this.getDirection() + 180);
    
        } else {
    
            double angle = Math.toDegrees(Math.atan2(World.instance().getPlayer().getY() - y, World.instance().getPlayer().getX() - x));
    
            if (angle < 0) angle += 360;
            if (angle >= 360) angle -= 360;
    
            this.setDirection((int) angle);
    
            if (count < 1) {
                    
                count = 50;
                this.attack();

            }

            -- count;

        }

        move(getSpeed(), getDirection());

    }

    public void determineState() {

        if (super.foundPlayer()) {

            this.state = GruntState.ATTACK;
            this.setSpeed(2.3);

        } else {

            this.state = GruntState.PATROL;

        }

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
