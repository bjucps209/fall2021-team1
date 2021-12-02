package model;

public class Grunt extends Enemy {

    private int count; // Counter so the enemy doesnt attack every 35 miliseconds

    public enum GruntState {

        PATROL, ATTACK

    }

    private GruntState state;

    public Grunt() {
        super(128, 128);
        count = 50;
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
        if (Math.hypot(this.getX() - World.instance().getPlayer().getX(),
                this.getY() - World.instance().getPlayer().getY()) <= 95) {
            World.instance().getPlayer().handleDamage(this.getDamage());
        }
    }

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

    /**
     * Moves the grunt in a certain direction depending on its state.
     */
    @Override
    public boolean move(int direction) {
        if (state != GruntState.ATTACK) {
            determineState();
        }

        double x = this.getX();
        double y = this.getY();

        if (state == GruntState.PATROL) {
            if (x > this.getOriginalX() + 60 || x < this.getOriginalX() - 60) {
                this.setDirection(this.getDirection() + 180);
            }
        } else if (state == GruntState.ATTACK) {
            double theta = Math.atan2(World.instance().getPlayer().getY() - y, World.instance().getPlayer().getX() - x);
            double angle = Math.toDegrees(theta);
            if (angle < 0) {
                angle += 360;
            }
            this.setDirection((int) angle);

            if (count % 50 == 0) {
                this.attack();
            }

        }

        if (this.getDirection() >= 360) {
            this.setDirection(this.getDirection() - 360);
        }

        super.move(direction);
        ++count;
        return true;

    }

    @Override
    public void navigate() {

    }

    public void determineState() {
        if (super.foundPlayer()) {
            this.state = GruntState.ATTACK;
            this.setSpeed(this.getSpeed() + 7);
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
