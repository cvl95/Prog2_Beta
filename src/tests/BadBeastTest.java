package tests;

import Core.EntityContext;
import Entity.BadBeast;
import Movement.XY;
import org.junit.Test;

public class BadBeastTest {

    @Test
    public void testNextStep() {
        BadBeast badBeast = new BadBeast(0, new XY(0, 0));
        EntityContext context = new EntityContextMock();
        for (int i = 0; i < 10; i++) {
            badBeast.nextStep(context);
        }
    }
}
