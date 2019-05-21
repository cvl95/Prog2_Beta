package Core;

import Entity.*;
import Movement.XY;

import java.util.List;

public interface EntityContext {
        XY getSize();
        EntityType getEntityType(XY position);
        int calculateDistance(XY position1, XY position2);
        void tryMove( MasterSquirel masterSquirel, XY moveDirection);
        void tryMove(MiniSquirel miniSquirel, XY moveDirection);
        void tryMove(GoodBeast goodBeast, XY moveDirection);
        void tryMove(BadBeast badBeast, XY moveDirection);
        PlayerEntity nearestPlayerEntity(XY position);
        void kill(Entity entity);
        void killAndReplace(Entity entity);
        Entity[] checkSuroundings(Entity entity);
        boolean testArrayBounds(XY position);
        boolean isOccupied(XY position);
        XY getFreeSurrounding(XY postion);
        Entity getEntityAt(XY position);


}
