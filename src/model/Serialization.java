package model;

import java.io.*;

public class Serialization {

    /**
     * Loads the game from a text file.
     */
    public void load() {


    }

    /**
     * Saves the game to a text file.
     */
    public void save() throws IOException {



    }

    /**
     * Translate a type to the string used for saving objects of that type.
     * @return the string representing type
     */
    private String typeToString(EntityType type) {

        switch (type) {

            case GRUNT_ENEMY:
                
                return "grunt";

            case PLAYER:
                
                return "player";

            case NPC:
                
                return "npc";
        
            default:

                return "item";

        }

    }
    
}
