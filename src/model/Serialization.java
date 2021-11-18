package model;

import java.io.*;
import java.util.ArrayList;

public class Serialization {

    /**
     * Loads the game from a text file.
     */
    public static void load() throws IOException {

        var file = new File("SAVEGAME.txt");

        var reader = new BufferedReader(new FileReader(file));

        String line = reader.readLine();
        String[] data = line.split("::");

        if (!data[0].equals("WORLD")) {

            reader.close();
            throw new IOException("Invalid save");

        }

        // Load World data
        var world = World.instance();

        world.setDifficulty(stringToDifficulty(data[1]));
        world.setCurrentlocation(data[2]);
        world.setScore(Integer.parseInt(data[3]));

        line = reader.readLine();
        data = line.split("::");

        if (!data[0].equals("PLAYER")) {

            reader.close();
            throw new IOException("Invalid save");

        }

        // Load Player data
        var player = world.getPlayer();

        player.setPosition(Double.parseDouble(data[1]), Double.parseDouble(data[2]));
        player.setHealth(Integer.parseInt(data[3]));
        player.setDamage(Integer.parseInt(data[4]));
        player.setSpeed(Double.parseDouble(data[5]));

        line = reader.readLine();

        // Load entity data
        ArrayList<Entity> entities = new ArrayList<>();

        while (line != null) {

            data = line.split("::");
            EntityType type = stringToType(data[0]);

            switch (type) {

                case NPC:
                    
                    entities.add(new NPC(data[1]));
                    break;

                case ITEM:

                    entities.add(new Item(data[1], Integer.parseInt(data[2])));
                    break;

                case PROJECTILE:

                    // TODO: add projectiles
                    break;

                case GRUNT_ENEMY:

                    var grunt = new Grunt();

                    grunt.setPosition(Double.parseDouble(data[1]), Double.parseDouble(data[2]));
                    grunt.setState(data[3]);

                    entities.add(grunt);
                    break;

            }

            line = reader.readLine();

        }

        reader.close();

    }

    /**
     * Saves the game to a text file.
     */
    public static void save(ArrayList<Entity> entities) throws IOException {

        // Clear old save
        var file = new File("SAVEGAME.txt");
        if (file.exists()) { file.delete(); }

        // Open new file
        var writer = new PrintWriter(new FileWriter(file));

        // Save world and player data
        var world = World.instance();
        writer.print(world.serialize());
        writer.print(world.getPlayer().serialize());

        // Save entity data
        for (Entity e : entities) {

            writer.print(e.serialize());

        }

        writer.close();

    }


    /// Utility ///

    private static DifficultyLevel stringToDifficulty(String difficulty) throws IOException {

        switch (difficulty) {

            case "EASY":

                return DifficultyLevel.EASY;

            case "MEDIUM":

                return DifficultyLevel.MEDIUM;

            case "HARD":
                
                return DifficultyLevel.HARD;
        
            default:

                throw new IOException("Invalid save");
        
        }

    }

    /**
     * Translate a string to the EntityType it represents.
     * @param type the type string
     * @return the string representing type
     */
    private static EntityType stringToType(String type) throws IOException {

        switch (type) {

            case "PLAYER":
                
                return EntityType.PLAYER;

            case "NPC":
                
                return EntityType.NPC;

            case "ITEM":
                
                return EntityType.ITEM;

            case "GRUNT_ENEMY":
                
                return EntityType.GRUNT_ENEMY;
        
            case "PROJECTILE":

                return EntityType.PROJECTILE;

            default:

                throw new IOException("Invalid save");

        }

    }
    
}
