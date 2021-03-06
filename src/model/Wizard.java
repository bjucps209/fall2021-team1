package model;

// Great and powerful Oz
/** Enemy that spawns only on hard difficulty. Throws projectiles at the player and runs away when they get too close. */
public class Wizard extends Enemy {

    /** Controls the frequency of attacks. */
    private int count = 40;

    

    /** Possible navigation states of wizard enemies. */
    public enum WizardState {

        MEDITATE, ATTACK

    }

    /** The navigation state of the wizard. */
    private WizardState state;

    /** Enemy that spawns only on hard difficulty. Throws projectiles at the player and runs away when they get too close. */
    public Wizard() {

        super(128, 128);
        // Load base stats

        switch (World.instance().getDifficulty()) {

            case EASY:
                this.setSpeed(1.3);
                break;

            case MEDIUM:
                this.setSpeed(2.3);
                break;

            case HARD:
                this.setSpeed(3.3);
                break;

            default:
                break;

        }

        this.setMaxHealth(2);
        this.setHealth(2);
        this.setDamage(2);
        
        this.setDetectionRadius(300);
        state = WizardState.MEDITATE;

    }

    /// Methods from Living ///

    @Override
    public void attack() {

        // Get direction to player
        int angleToPlayer = (int) Math.toDegrees(Math.atan2(World.instance().getPlayer().getY() - getY(), World.instance().getPlayer().getX() - getX()));
        if (angleToPlayer < 0) angleToPlayer += 360;
        if (angleToPlayer >= 360) angleToPlayer -= 360;

        // Throw lightning

        double lightningSpeed = 0;

        switch (World.instance().getDifficulty()) {

            case EASY:
                lightningSpeed = 1.8;
                break;

            case MEDIUM:
                lightningSpeed = 5.8;
                break;

            case HARD:
                lightningSpeed = 9.8; 
                break;

            default:
                break;

        }
        
        var lightning = new Projectile(lightningSpeed, angleToPlayer, getDamage());
        lightning.setPosition(getX(), getY());

        World.instance().addToPendingList(lightning);

    }

    @Override
    public void handleDamage(int damage, int direction) {

        if (iFrames <= 0) {

            this.iFrames = 5;
            this.move(10, direction);
            this.setHealth(this.getHealth() - damage);

        }

    }

    @Override
    public void navigate() {

        // Immunity after hit
        if (iFrames > 0) {
            
            -- iFrames;
            return;

        }

        // Check state
        if (foundPlayer()) state = WizardState.ATTACK;

        // Meditate means don't move
        if (state == WizardState.MEDITATE) return;

        double x = this.getX();
        double y = this.getY();

        double angle = Math.toDegrees(Math.atan2(World.instance().getPlayer().getY() - y, World.instance().getPlayer().getX() - x));
        double distanceToPlayer = Math.hypot(this.getX() - World.instance().getPlayer().getX(), this.getY() - World.instance().getPlayer().getY());

        // Run away if too close
        if (distanceToPlayer < 200 && distanceToPlayer < 210) angle -= 180;
    
        if (angle < 0) angle += 360;
        if (angle >= 360) angle -= 360;
    
        setDirection((int) angle);
    
        if (count < 1) {
                    
            count = 150;
            attack();

        }

        -- count;

        move(getSpeed(), getDirection());

    }

    /// Methods from Enemy ///

    @Override
    public String serialize() {

        return "" + getType() + "::" + getX() + "::" + getY() + "::" + state + "::" + getHealth() + "\n";

    }

    @Override
    public EntityType getType() {

        return EntityType.CLOUD_WIZARD;

    }

    /// Getters and Setters ///
    /**
     * Gets the navigation state of the wizard.
     * @return the navigation state
     */
    public WizardState getState() {

        return this.state;

    }

    /**
     * Sets the navigation state of the wizard.
     * @param state the new state
     */
    public void setState(WizardState state) {

        this.state = state;

    }

    /**
     * Sets the navigation state of the wizard from a string representation.
     * @param state the new state
     */
    public void setState(String state) {

        switch (state) {

            case "MEDITATE":

                this.state = WizardState.MEDITATE;
                break;

            default:

                this.state = WizardState.ATTACK;
                break;

        }

    }

}
