package tests;

import org.junit.Test;
import static org.junit.Assert.*;
import Movement.XY;
public class XYtest {
    @Test
    public void testPlus() {
        XY xy = new XY(2, 3).plus(new XY(-1, 1));
        assertEquals(new XY(1, 4), xy);
    }

    @Test
    public void testMinus() {
        XY xy = new XY(2, 3).minus(new XY(-1, 1));
        assertEquals(new XY(3, 2), xy);
    }

    @Test
    public void testTimes() {
        XY xy = new XY(2, 3).times(2);
        assertEquals(new XY(4, 6), xy);
    }

    @Test
    public void testLength() {
        double epsilon = 1.0e-6;
        double length = new XY(3, 4).length();
        assertTrue(Math.abs(length - 5) < epsilon);
    }

    @Test
    public void testDistanceFrom() {
        double epsilon = 1.0e-6;
        double distance = new XY(3, 4).distanceFrom(new XY(0, 0));
        assertTrue(Math.abs(distance - 5) < epsilon);
    }

}
