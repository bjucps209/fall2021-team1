package model;

public class Juggernaut extends Enemy {

    int count; // cooldown for the basic attack
    int frenzyCooldown; // cooldown before the Juggernaut can enter the frenzy state again

    public enum JuggernautState {

        PATROL, ATTACK, FRENZY

    }

    private JuggernautState state;

    public Juggernaut() {
        super(256, 256);

        count = 50;
        frenzyCooldown = 0;
        this.setMaxHealth(10);
        this.setHealth(10);
        this.setDamage(3);
        this.setSpeed(0.3);
        this.setDetectionRadius(125);
        this.state = JuggernautState.PATROL;
    }

    @Override
    public void navigate() {
        // TODO Auto-generated method stub

    }

    /**
     * Juggernauts specific attack method
     */
    @Override
    public void attack() {
        if (Math.hypot(this.getX() - World.instance().getPlayer().getX(),
                this.getY() - World.instance().getPlayer().getY()) <= 95) {
            World.instance().getPlayer().handleDamage(this.getDamage());

            
            if (getDirection() >= 0 && getDirection() < 90 || getDirection() > 270 && getDirection() <= 360 ) {
                World.instance().getPlayer().setX(World.instance().getPlayer().getX() + 400);
            } else if (getDirection() == 90) {
                World.instance().getPlayer().setY(World.instance().getPlayer().getY() - 400);
            } else if (getDirection() > 90 && getDirection() <= 180 || getDirection() > 180 && getDirection() < 270) {
                World.instance().getPlayer().setX(World.instance().getPlayer().getX() + 400);
            } else if (getDirection() == 270) {
                World.instance().getPlayer().setY(World.instance().getPlayer().getY() + 400);
            }

            if (state == JuggernautState.ATTACK && frenzyCooldown % 5 == 0) {
                setState(JuggernautState.FRENZY);
            } else if (state == JuggernautState.FRENZY) {
                setState(JuggernautState.ATTACK);
            }
            ++frenzyCooldown;
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
        if (state != JuggernautState.ATTACK) {
            determineState();
        }

        double x = this.getX();
        double y = this.getY();

        if (state == JuggernautState.PATROL) {
            if (x > this.getOriginalX() + 60 || x < this.getOriginalX() - 60) {
                this.setDirection(this.getDirection() + 180);
            }

        } else if (state == JuggernautState.ATTACK) {
            double theta = Math.atan2(World.instance().getPlayer().getY() - y, World.instance().getPlayer().getX() - x);
            double angle = Math.toDegrees(theta);
            if (angle < 0) {
                angle += 360;
            }
            this.setDirection((int) angle);

            if (state == JuggernautState.FRENZY) {
                this.setSpeed(this.getSpeed() + 12);
            } else {
                this.setSpeed(0.3);
            }

            if (count % 50 == 0 || state == JuggernautState.FRENZY) {
                this.attack();
            }

            if (this.getDirection() >= 360) {
                this.setDirection(this.getDirection() - 360);
            }


        }
        super.move(direction);
        ++count;
        return true;

    }

    public void determineState() {
        if (super.foundPlayer()) {
            this.state = JuggernautState.ATTACK;
        } else {
            this.state = JuggernautState.PATROL;
        }
    }

    @Override
    public String serialize() {
        return null;
    }

    @Override
    public EntityType getType() {
        return EntityType.JUGGERNAUT_ENEMY;
    }

    public JuggernautState getState() {
        return state;
    }

    public void setState(JuggernautState state) {
        this.state = state;
    }

}
