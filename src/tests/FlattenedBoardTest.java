package tests;


import Core.*;
import Entity.*;
import GameEngine.GameMode;
import Movement.XY;
import org.junit.Before;
import org.junit.Test;

import static Entity.EntityType.*;
import static org.junit.Assert.*;

public class FlattenedBoardTest {
    private Board board;

    @Before
    public void setUp() throws InvalidBoardSize {
        BoardConfig config = new BoardConfig(new XY(5, 5), 1, 0, 1, 0, 0, 0,  new String[] {}, new String[] {});
        board = new Board(config, GameMode.SINGLE_PLAYER);
        for (Entity entity : board.getEntitySet()) {
            switch (entity.getType()) {
                case MASTER_SQUIRREL:
                    entity.setPosition(new XY(2, 2));
                    break;
                case GOOD_BEAST:
                    entity.setPosition(new XY(3, 2));
                    break;
                case BAD_BEAST:
                    entity.setPosition(new XY(1, 2));
                    break;
                default:
                    break;
            }
        }
    }

    @Test
    public void testTryMoveMasterSquirrel() {
        EntityContext context = board.flatten();
        MasterSquirel squirrel = (MasterSquirel) context.getEntityAt(new XY(2, 2));
        context.tryMove(squirrel, XY.RIGHT);
        assertEquals(squirrel.getPosition(), new XY(3, 2));
        assertEquals(squirrel.getEnergy(), MasterSquirel.START_ENERGY + GoodBeast.START_ENERGY);
        context.tryMove(squirrel, XY.RIGHT);
        squirrel.setStun(3);
        context.tryMove(squirrel, XY.LEFT);
        assertEquals(squirrel.getPosition(), new XY(3, 2));
    }

    @Test
    public void testTryMoveBadBeast() {
        EntityContext context = board.flatten();
        BadBeast badBeast = (BadBeast) context.getEntityAt(new XY(2, 1));
        context.tryMove(badBeast, XY.RIGHT);
        assertEquals(badBeast.getSNACK(), 6);
        assertEquals(badBeast.getPosition(), new XY(1, 2));
        assertEquals(context.getEntityAt(new XY(2, 2)).getEnergy(), MasterSquirel.START_ENERGY + BadBeast.START_ENERGY);
    }

    @Test
    public void testTryMoveGoodBeast() {
        EntityContext context = board.flatten();
        GoodBeast goodBeast = (GoodBeast) context.getEntityAt(new XY(2, 3));
        context.tryMove(goodBeast, XY.UP);
        assertEquals(context.getEntityAt(new XY(1, 3)).getType(), EntityType.GOOD_BEAST);
    }
}
