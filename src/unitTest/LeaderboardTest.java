package unitTest;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import model.HighScore;
import model.Leaderboard;


public class LeaderboardTest{
    private ArrayList<HighScore> dummy = new ArrayList<HighScore>();

    @Test
    public void testConstructor() {
        for (int i = 10; i > 0; --i) {
            this.dummy.add(new HighScore("Bob" + i, i));
        }
        Leaderboard lb = new Leaderboard(dummy);
        assertNotNull(lb);
        assertEquals(10, lb.getHighscores().size());
        assertEquals(1, lb.getHighscores().get(9).getScore());
        assertEquals("Bob9", lb.getHighscores().get(1).getPlayerName());
    }

    @Test
    public void testProcess() {
        for (int i = 10; i > 0; --i) {
            this.dummy.add(new HighScore("Bob" + i, i));
        }
        Leaderboard lb = new Leaderboard(dummy);

        lb.process("David", 100);
        assertEquals("David", lb.getHighscores().get(0).getPlayerName());
        assertEquals(100, lb.getHighscores().get(0).getScore());

        lb.process("Joshua", 15);
        assertEquals("Joshua", lb.getHighscores().get(1).getPlayerName());
        assertEquals(15, lb.getHighscores().get(1).getScore());

        lb.getHighscores().get(8).setScore(3);
        lb.process("Andrew", 2);
        assertEquals("Andrew", lb.getHighscores().get(9).getPlayerName());
        assertEquals(2, lb.getHighscores().get(9).getScore());
    }


}

