package model;

import java.io.*;
import java.util.ArrayList;

public class Serialization {

    /**
     * Creates a HIGHSCORES.txt file 
     */
    public static void createHSfile() throws IOException {
        var file = new File("HIGHSCORES.txt");
        if (!file.exists()) {
            var writer = new PrintWriter(new FileWriter(file));

            for (int i = 0; i < 10; ++i) {
                writer.print("  -  ::0\n");
            } 
            writer.close();   
        }
    }

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

            try {

                entities.add(Entity.deserialize(line));

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
    /**
     * Gets the world dificulty as represented by a string.
     * @param difficulty the string difficulty
     * @return the difficulty
     * @throws IOException if the difficulty is not recognized
     */
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
    
}
