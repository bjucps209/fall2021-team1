package model;

public class GameUtility {

    /**
     * Translate a type to the string used for saving objects of that type.
     * @return the string representing type
     */
    public String typeToString(EntityType type) {

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
