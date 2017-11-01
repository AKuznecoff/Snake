import org.junit.Test;

import static org.junit.Assert.*;

public class Tests {
    @Test
    public void testSizes(){
        Board b = new Board(10, 10, 1);
        assertEquals(10, b.getHeight());
        assertEquals(10, b.getWidth());
    }

    @Test
    public void testIllegalSizes() throws IllegalArgumentException{
        Board b = new Board(9, 10, 1);
    }
}
