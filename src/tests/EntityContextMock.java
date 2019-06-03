package tests;

import Core.EntityContext;
import Entity.*;
import Movement.XY;
import org.junit.Test;

import java.util.logging.Level;
import java.util.logging.Logger;

import static org.junit.Assert.*;

public class EntityContextMock implements EntityContext {

    private static final Logger logger = Logger.getLogger(EntityContextMock.class.getName());

    @Override
    public XY getSize() {
        logger.log(Level.INFO, "getSize");
        return new XY(0, 0);
    }

    @Override
    public EntityType getEntityType(XY position) {
        logger.log(Level.INFO, "getEntityType" + " " + position);
        return EntityType.NONE;
    }

    @Override
    public int calculateDistance(XY position1, XY position2) {
        logger.log(Level.INFO, "getDistance" + " " + position1 + " " + position2);
        return 0;
    }

    @Override
    public void tryMove(MasterSquirel masterSquirel, XY moveDirection) {
        logger.log(Level.INFO, "tryMove" + " " + masterSquirel + " " + moveDirection);
    }

    @Override
    public void tryMove(MiniSquirel miniSquirel, XY moveDirection) {
        logger.log(Level.INFO, "tryMove" + " " + miniSquirel + " " + moveDirection);
    }

    @Override
    public void tryMove(GoodBeast goodBeast, XY moveDirection) {
        logger.log(Level.INFO, "tryMove" + " " + goodBeast + " " + moveDirection);
    }

    @Override
    public void tryMove(BadBeast badBeast, XY moveDirection) {
        logger.log(Level.INFO, "tryMove" + " " + badBeast + " " + moveDirection);
    }

    @Override
    public PlayerEntity nearestPlayerEntity(XY position) {
        logger.log(Level.INFO, "nearestPlayerEntity" + " " + position);
        return null;
    }

    @Override
    public boolean testArrayBounds(XY position) {
        logger.log(Level.INFO, "testArrayBounds " + position);
        return false;
    }

    @Override
    public boolean isOccupied(XY position) {
        logger.log(Level.INFO, "isOccupied" + position);
        return false;
    }

    @Override
    public XY getFreeSurrounding(XY position) {
        logger.log(Level.INFO, "getFreeSurrounding " + position);
        return null;
    }

    @Override
    public Entity getEntityAt(XY position) {
        logger.log(Level.INFO, "getEntityAt" + " " + position);
        return null;
    }

    @Override
    public Entity getEntityByID(int id) {
        logger.log(Level.INFO, "getEntityByID" + id);
        return null;
    }

    @Override
    public Entity[] checkSuroundings(Entity entity) {
        logger.log(Level.INFO, "getFreePositionsAround " + entity);
        return new Entity[0];
    }

    @Override
    public void killAndReplace(Entity entity) {
        logger.log(Level.INFO, "killAndReplace" + " " + entity);
    }

    @Override
    public void kill(Entity entity) {
        logger.log(Level.INFO, "kill" + " " + entity);
    }
}
