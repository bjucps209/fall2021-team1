package model;

public class Juggernaut extends Enemy {

    int count = 50; // cooldown for the basic attack
    int frenzyCooldown = 5; // cooldown before the Juggernaut can enter the frenzy state again
    int attackSpeed = 5;
    int frenzySpeed = 12;
    boolean hitPlayer = false; // check if the player was successfully hit

    public enum JuggernautState {

        PATROL, ATTACK, FRENZY

    }

    private JuggernautState state;

    public Juggernaut() {
        
        super(192, 192);

        frenzyCooldown = 0;
        attackSpeed = 5;
        frenzySpeed = 12;
        hitPlayer = false;

        this.setMaxHealth(10);
        this.setHealth(10);
        this.setDamage(3);
        this.setSpeed(1);
        this.setDetectionRadius(200);
        this.state = JuggernautState.PATROL;

    }

    @Override
    public void navigate() {

        if (iFrames > 0) {
            
            -- iFrames;
            return;

        }

        if (foundPlayer() && state == JuggernautState.PATROL) { 
            state = JuggernautState.ATTACK;
            setSpeed(5);
        }

        double x = this.getX();
        double y = this.getY();

        if (state == JuggernautState.PATROL) {

            if (x > this.getOriginalX() + 60 || x < this.getOriginalX() - 60) {
                
                this.setDirection(this.getDirection() + 180);
                if (this.getDirection() > 180) {
                    this.setDirection(0);
                }
                
            }
    
        } else {
    
            // Calculate angle
            double angle = Math.toDegrees(Math.atan2(World.instance().getPlayer().getY() - y, World.instance().getPlayer().getX() - x));
            
            // Correct angle to fit in correct range
            if (angle < 0) angle += 360;
            if (angle >= 360) angle -= 360;

            // Set direction to angle
            this.setDirection((int) angle);

            // Attack if it should
            if (count < 1) {
    
                // Attacks are more frequent when in FRENZY state
                count = state == JuggernautState.ATTACK ? 50 : 10;
                this.attack();
                
            }

            // Decrement attack timer
            -- count;
        
        }
    
        // Move as far as possible in the chosen direction
        move(this.getSpeed(), this.getDirection());

    }

    /**
     * Juggernauts specific attack method
     */
    @Override
    public void attack() {

        if (Math.hypot(this.getX() - World.instance().getPlayer().getX(), this.getY() - World.instance().getPlayer().getY()) <= 95) {
            
            // Deal damage
            World.instance().getPlayer().handleDamage(this.getDamage(), getDirection());

            // Apply knockback
            World.instance().getPlayer().move(10000, getDirection());

            // Check state
            if (state == JuggernautState.ATTACK && frenzyCooldown < 1) {
            
                frenzyCooldown = 20;
                setState(JuggernautState.FRENZY);
                setSpeed(12);
            
            } else if (frenzyCooldown < 1) {
            
                frenzyCooldown = 50;
                setState(JuggernautState.ATTACK);
                setSpeed(5);
            
            }
            
            -- frenzyCooldown;
            hitPlayer = true;
        
        }

    }

    @Override
    public boolean handleDamage(int damage, int direction) {

        if (iFrames <= 0) {

            this.iFrames = 1;
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

    public void setState(String state) {

        switch (state) {

            case "PATROL":

                this.state = JuggernautState.PATROL;
                break;

            case "ATTACK":

                this.state = JuggernautState.ATTACK;
                break;

            default:

                this.state = JuggernautState.FRENZY;
                break;

        }

    }

    public boolean isHitPlayer() {

        return hitPlayer;

    }

    public void setHitPlayer(boolean hitPlayer) {

        this.hitPlayer = hitPlayer;

    }

    

}
