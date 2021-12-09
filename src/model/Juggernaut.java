package model;

public class Juggernaut extends Enemy {

    /** The cooldown for attacks. Becomes smaller when in the frenzy state. */
    private int count = 50;
    /** The cooldown before the frenzy state can be entered again. */
    private int frenzyCooldown = 5;
    /** The normal movement speed of the juggernaut. */
    private double attackSpeed;
    /** The movement speed of the juggernaut during frenzy. */
    private double frenzySpeed;
    /** Wether the juggernaut has hit the player. */
    private boolean hitPlayer = false;

    public enum JuggernautState {

        PATROL, ATTACK, FRENZY

    }

    private JuggernautState state;

    public Juggernaut() {
        
        super(192, 192);

        frenzyCooldown = 0;

        switch (World.instance().getDifficulty()) {

            case EASY:
                attackSpeed = 5;
                break;

            case MEDIUM:
                attackSpeed = 7;
                break;

            case HARD:
                attackSpeed = 9; 
                break;

            default:
                break;

        }

        frenzySpeed = 12;
        hitPlayer = false;

        this.setMaxHealth(10);
        this.setHealth(10);
        this.setDamage(3);
        this.setSpeed(attackSpeed);
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
            setSpeed(attackSpeed);
        
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

        if (Math.hypot(this.getX() - World.instance().getPlayer().getX(), this.getY() - World.instance().getPlayer().getY()) <= 120) {
            
            if (state == JuggernautState.FRENZY) {
                hitPlayer = true;
            } else {
                hitPlayer = false;
            }


            // Deal damage
            World.instance().getPlayer().handleDamage(this.getDamage(), getDirection());

            // Apply knockback
            World.instance().getPlayer().move(10000, getDirection());

            // Check state
            if (state == JuggernautState.ATTACK && frenzyCooldown < 1 ) {
            
                frenzyCooldown = 10;
                setState(JuggernautState.FRENZY);
                setSpeed(frenzySpeed);
            
            } else if (frenzyCooldown < 1 || hitPlayer) {
            
                frenzyCooldown = 50;
                setState(JuggernautState.ATTACK);
                setSpeed(attackSpeed);
            
            }
            
            -- frenzyCooldown;

        
        }

    }

    @Override
    public void handleDamage(int damage, int direction) {

        if (damage > 0) {
            this.setHealth(this.getHealth() - damage);

        }

    }

    @Override
    public String serialize() {

        return "" + getType() + "::" + getX() + "::" + getY() + "::" + state + "\n";
    
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
