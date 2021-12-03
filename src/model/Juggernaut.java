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

        this.setMaxHealth(10);
        this.setHealth(10);
        this.setDamage(3);
        this.setSpeed(1);
        this.setDetectionRadius(125);
        this.state = JuggernautState.PATROL;

    }

    @Override
    public void navigate() {

        if (state != JuggernautState.ATTACK) determineState();

        double x = this.getX();
        double y = this.getY();

        boolean moved = false;

        for (int i = 0; i < 10; ++ i) {

            if (moved) break;

            if (state == JuggernautState.PATROL) {

                if (x > this.getOriginalX() + 60 || x < this.getOriginalX() - 60) {
    
                    this.setDirection(this.getDirection() + 180);
                
                }
    
            } else if (state == JuggernautState.ATTACK) {
    
                double angle = Math.toDegrees(Math.atan2(World.instance().getPlayer().getY() - y, World.instance().getPlayer().getX() - x));
                
                if (angle < 0) angle += 360;
                this.setDirection((int) angle);
    
                if (count < 1) {
    
                    count = state == JuggernautState.ATTACK ? 50 : 10;
                    this.attack();
                
                }
    
                if (this.getDirection() >= 360) this.setDirection(this.getDirection() - 360);
            
            }
    
            moved = move(this.getDirection());

        }

        -- count;

    }

    /**
     * Juggernauts specific attack method
     */
    @Override
    public void attack() {

        if (Math.hypot(this.getX() - World.instance().getPlayer().getX(), this.getY() - World.instance().getPlayer().getY()) <= 95) {
            
            World.instance().getPlayer().handleDamage(this.getDamage());

            if ((getDirection() >= 0 && getDirection() < 90) || (getDirection() > 270 && getDirection() <= 360)) {

                World.instance().getPlayer().setX(World.instance().getPlayer().getX() + 400);
            
            } else if (getDirection() == 90) {
            
                World.instance().getPlayer().setY(World.instance().getPlayer().getY() - 400);
            
            } else if ((getDirection() > 90 && getDirection() <= 180) || (getDirection() > 180 && getDirection() < 270)) {
            
                World.instance().getPlayer().setX(World.instance().getPlayer().getX() + 400);
            
            } else if (getDirection() == 270) {
            
                World.instance().getPlayer().setY(World.instance().getPlayer().getY() + 400);
            
            }

            if (state == JuggernautState.ATTACK && frenzyCooldown < 1) {
            
                frenzyCooldown = 5;
                setState(JuggernautState.FRENZY);
                setSpeed(frenzySpeed);
            
            } else if (frenzyCooldown < 1) {
            
                frenzyCooldown = 10;
                setState(JuggernautState.ATTACK);
                setSpeed(attackSpeed);
            
            }
            
            -- frenzyCooldown;
            hitPlayer = true;
        
        }

    }

    @Override
    public boolean handleDamage(int damage) {
        this.setHealth(this.getHealth() - damage);
        return true;

    }

    @Override
    public void handleDeath() {
        if (this.getHealth() <= 0) {
            this.setDead(true);
        }

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

    public boolean isHitPlayer() {
        return hitPlayer;
    }

    public void setHitPlayer(boolean hitPlayer) {
        this.hitPlayer = hitPlayer;
    }

    

}
