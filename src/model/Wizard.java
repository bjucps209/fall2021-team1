package model;

public class Wizard extends Enemy {

    /** Controls the frequency of attacks. */
    private int count = 40;

    public enum WizardState {

        MEDITATE, ATTACK

    }

    private WizardState state;

    public Wizard(int id) {

        super(128, 128, id);
        // Load base stats
        this.setMaxHealth(2);
        this.setHealth(2);
        this.setDamage(2);
        this.setSpeed(1);
        this.setDetectionRadius(300);
        state = WizardState.MEDITATE;

    }

    /// Methods from Living ///

    @Override
    public void attack() {

        // TODO: Throw Lightning

    }

    @Override
    public void handleDamage(int damage, int direction) {

        if (iFrames <= 0) {

            this.iFrames = 5;
            this.move(10, direction);
            this.setHealth(this.getHealth() - damage);

        }

    }

    /**
     * Moves the wizard in a certain direction depending on its state.
     */
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
        if (distanceToPlayer < 200) angle -= 180;
    
        if (angle < 0) angle += 360;
        if (angle >= 360) angle -= 360;
    
        setDirection((int) angle);
    
        if (count < 1) {
                    
            count = 60;
            attack();

        }

        -- count;

        move(getSpeed(), getDirection());

    }

    /// Methods from Enemy ///

    @Override
    public String serialize() {

        return "" + getType() + "::" + getId() + "::" + getX() + "::" + getY() + "::" + state + "\n";

    }

    @Override
    public EntityType getType() {

        return EntityType.CLOUD_WIZARD;

    }

    /// Getters and Setters ///

    public WizardState getState() {

        return this.state;

    }

    public void setState(WizardState state) {

        this.state = state;

    }

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
