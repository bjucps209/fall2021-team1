package model;

import java.io.*;
import java.util.ArrayList;

public class Serialization {

    /**
     * Loads the game from a text file.
     */
    public static void load(String filename) throws IOException {

        var file = new File(filename);
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

            try {

                switch (type) {

                    case NPC:
                        
                        // needs fix
                        entities.add(new NPC(data[1], line));
                        break;
    
                    case ITEM:
    
                        // needs fix
                        entities.add(new Item(data[1], line, Integer.parseInt(data[2])));
                        break;
    
                    case PROJECTILE:
    
                        // TODO: add projectiles
                        break;
    
                    case GRUNT_ENEMY:
    
                        var grunt = new Grunt();
    
                        grunt.setId(Integer.parseInt(data[1]));
                        grunt.setPosition(Double.parseDouble(data[2]), Double.parseDouble(data[3]));
                        grunt.setState(data[4]);
    
                        entities.add(grunt);
                        break;

                    case JUGGERNAUT_ENEMY:

                        var jugg = new Juggernaut();

                        jugg.setId(Integer.parseInt(data[1]));
                        jugg.setPosition(Double.parseDouble(data[2]), Double.parseDouble(data[3]));
                        jugg.setState(data[4]);

                        break;
    
                }

            } catch (Exception e) {

                reader.close();
                throw new IOException("Invalid save");

            }

            line = reader.readLine();

        }

        world.setEntityList(entities);

        reader.close();

    }

    /**
     * Saves the game to a text file.
     */
    public static void save(String filename, ArrayList<Entity> entities) throws IOException {

        // Clear old save
        var file = new File(filename);
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

    /** Loads the saved highscores. */
    public static ArrayList<HighScore> loadScores(String filename) throws IOException {

        var file = new File(filename);
        var reader = new BufferedReader(new FileReader(file));

        String line = reader.readLine();
        String[] data;

        ArrayList<HighScore> scores = new ArrayList<>();

        while (line != null) {

            data = line.split("::");

            try {

                scores.add(new HighScore(data[0], Integer.parseInt(data[1])));

            } catch (Exception e) {

                reader.close();
                throw new IOException("Invalid save");

            }
            
            line = reader.readLine();

        }

        reader.close();

        return scores;

    }

    /**
     * Saves the game to a text file.
     */
    public static void saveScores(String filename, ArrayList<HighScore> scores) throws IOException {

        // Clear old save
        var file = new File(filename);
        if (file.exists()) file.delete();

        // Open new file
        var writer = new PrintWriter(new FileWriter(file));

        // Save entity data
        for (HighScore s : scores) {

            writer.print(s.serialize());

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
