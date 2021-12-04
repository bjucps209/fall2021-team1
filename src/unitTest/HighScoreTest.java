package unitTest;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import model.HighScore;

public class HighScoreTest {
    
    @Test
    public void testConstructor() {
        HighScore hs = new HighScore("David", 28);
        assertEquals("David", hs.getPlayerName());
        assertEquals(28, hs.getScore());
    }

    @Test
    public void testSerialize() {
        HighScore hs = new HighScore("David", 28);
        assertEquals("David::28\n", hs.serialize());
    }
}
