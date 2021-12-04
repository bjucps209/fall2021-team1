package unitTest;

import org.junit.Test;

import model.Entity;
import model.Grunt;
import model.Serialization;
import model.HighScore;

import static org.junit.Assert.*;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.ArrayList;

public class SerializationTest {

    private String saveName = "TEST_SAVE.txt";
    private String scoreName = "TEST_SCORES.txt";

    @Test
    public void testSaveGame() {

        var entities = new ArrayList<Entity>();

        var grunt = new Grunt();
        grunt.setPosition(50, 50);
        entities.add(grunt);

        try {

            Serialization.save(saveName, entities);

        } catch (IOException e) {

            fail();

        }

        assertTrue(true);

    }

    @Test
    public void testSaveAndLoadScores() {

        var scores = new ArrayList<HighScore>();

        scores.add(new HighScore("Bob", 3000));
        scores.add(new HighScore("Fred", 451));
        scores.add(new HighScore("Joey", 13));

        try {

            Serialization.saveScores(scoreName, scores);

            var loaded = Serialization.loadScores(scoreName);

            assertTrue(loaded.get(0).getPlayerName().equals(scores.get(0).getPlayerName()));
            assertTrue(loaded.get(0).getScore() == scores.get(0).getScore());

            assertTrue(loaded.get(1).getPlayerName().equals(scores.get(1).getPlayerName()));
            assertTrue(loaded.get(1).getScore() == scores.get(1).getScore());
            
            assertTrue(loaded.get(2).getPlayerName().equals(scores.get(2).getPlayerName()));
            assertTrue(loaded.get(2).getScore() == scores.get(2).getScore());

        } catch (IOException e) {

            fail();

        }

    }
    
}
