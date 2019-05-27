package tests;

import org.junit.Test;
import Movement.XY;
import static org.junit.Assert.*;

public class XYtest {

    @Test
    public void testIncrease(){
        XY xy = new XY(2,3).increase(new XY(2,-1));
        assertTrue(new XY(4,2).equals(xy));

    }

    @Test
    public void testNewVectordirection(){
        XY xy = new XY(1,1).setNewVectorPosition(new XY(1,1));
        assertTrue(new XY(2,2).equals(xy));
    }

}
