import org.junit.Test;

import java.util.LinkedList;

import static org.junit.Assert.*;


public class BoardTest {
    @Test
    public void getHeight(){
        Board b = new Board(10, 10, 1);
        int expected = 10;
        int result = b.getHeight();
        assertEquals(expected, result);
        result = b.getWidth();
        assertEquals(expected, result);
    }

    @Test
    public void getWidth(){
        Board b = new Board(10, 10, 1);
        int expected = 10;
        int result = b.getWidth();
        assertEquals(expected, result);
    }

    @Test
    public void getDirection() throws Exception {
        Board b = new Board(10, 10, 1);
        Directions expected = Directions.RIGHT;
        Directions result = b.getDirection();
        assertEquals(expected, result);
    }

    @Test
    public void setDirection() {
        Board b = new Board(10, 10, 1);
        b.setDirection(Directions.DOWN);
        Directions expected = Directions.DOWN;
        assertEquals(expected, b.getDirection());
    }

    @Test
    public void nextStep() throws Exception {
        Board b = new Board(10, 10, 1);
        LinkedList<SnakeSegment> expected = new LinkedList<>();
        for (int x = 4; x < 8; x++) {
            expected.add(new SnakeSegment(new Point(x, 5)));
        }
        b.nextStep();
        assertEquals(expected, b.getSnake());
    }

    @Test
    public void eatFood() {
        Board b = new Board(10, 10, 1);
        b.addObject(new Point(7, 5), new Food(new Point(7, 5)));
        b.nextStep();
        assertEquals(1, b.getScores());
        assertFalse(b.getGameObjects().containsKey(new Point(7, 5)));
    }

    @Test
    public void collisionWithWall() {
        Board b = new Board(10, 10, 1);
        b.addObject(new Point(7, 5), new Wall(new Point(7, 5)));
        LinkedList<SnakeSegment> expected = (LinkedList<SnakeSegment>)b.getSnake().clone();
        b.nextStep();
        assertEquals(expected, b.getSnake());
        assertTrue(b.checkFinished());
    }

    @Test
    public void collisionWithSnake() {
        Board b = new Board(10, 10, 1);
        b.setDirection(Directions.UP);
        b.nextStep();
        b.setDirection(Directions.LEFT);
        b.nextStep();
        b.setDirection(Directions.DOWN);
        b.nextStep();
        assertTrue(b.checkFinished());
    }
}