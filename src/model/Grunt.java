package model;

/** The weakest enemy, which spawns most often. */
public class Grunt extends Enemy {

    /** Controls the frequency of attacks. */
    private int count = 25;
    /** The regular speed of the grunt. */
    private int attackSpeed;

    /** The possible navigation states of the grunt. */
    public enum GruntState {

        PATROL, ATTACK

    }

    /** The navigation state of the grunt. */
    private GruntState state;

    /**
     * The weakest enemy, which spawns most often.
     */
    public Grunt() {

        super(128, 128);
        // Load base stats
        
        switch (World.instance().getDifficulty()) {

            case EASY:
                attackSpeed = 4;
                break;

            case MEDIUM:
                attackSpeed = 6;
                break;

            case HARD:
                attackSpeed = 8; 
                break;

            default:
                break;

        }

        this.setMaxHealth(3);
        this.setHealth(3);
        this.setDamage(1);
        this.setSpeed(attackSpeed);
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
    public void handleDamage(int damage, int direction) {

        if (iFrames <= 0) {

            this.iFrames = 10;
            this.move(100, direction);
            this.setHealth(this.getHealth() - damage);

        }

    }

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

            if (x > this.getOriginalX() + 60 || x < this.getOriginalX() - 60) { 
                this.setDirection(this.getDirection() + 180);
                if (this.getDirection() > 180) {
                    this.setDirection(0);
                }
            }
    
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

    /**
     * Determines the state of the Grunt based on if the player has been detected.
     */
    public void determineState() {

        if (super.foundPlayer()) {

            this.state = GruntState.ATTACK;
            this.setSpeed(attackSpeed);

        } else {

            this.state = GruntState.PATROL;

        }

    }

    /// Methods from Enemy ///

    @Override
    public String serialize() {

        return "" + getType() + "::" + getOriginalX() + "::" + getOriginalY() + "::" + getX() + "::" + getY() + "::" + state + "::" + getHealth() + "\n";

    }

    @Override
    public EntityType getType() {

        return EntityType.GRUNT_ENEMY;

    }

    /// Getters and Setters ///
    /**
     * Gets the navigation state the grunt is in.
     * @return the grunt state
     */
    public GruntState getState() {

        return this.state;

    }

    /**
     * Sets the navigation state of the grunt.
     * @param state the new state
     */
    public void setState(GruntState state) {

        this.state = state;

    }

    /**
     * Sets the navigation state of the grunt from a string representation of the state.
     * @param state the new state
     */
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
