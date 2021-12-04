package unitTest;

import org.junit.Test;

import model.Entity;
import model.Grunt;
import model.Serialization;

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
    
}
