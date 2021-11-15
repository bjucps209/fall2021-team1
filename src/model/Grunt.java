package model;

import java.lang.Thread.State;

public class Grunt extends Enemy {

    private GruntState state;

    public Grunt(EntityType entityType) {
        super();
        //TODO Auto-generated constructor stub
    }


    /**
     * Changes the Grunt's state if the player is within its vicinty.
     * @param x - the player's x coordinate
     * @param y - the player's y coordinate
     */
    public void changeState(int x, int y) {
        super.scanArea(x, y);
    }

    
}
