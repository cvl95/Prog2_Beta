package Core;

import Entity.*;
import Movement.XY;

import java.util.List;

public interface EntityContext {
        XY getSize();
        EntityType getEntityType(XY position);
        void tryMove( MasterSquirel masterSquirel, XY moveDirection);
        void tryMove(MiniSquirel miniSquirel, XY moveDirection);
        void tryMove(GoodBeast goodBeast, XY moveDirection);
        void tryMove(BadBeast badBeast, XY moveDirection);
        PlayerEntity nearestPlayerEntity(XY position);
        void kill(Entity entity);
        void killAndReplace(Entity entity);
        List checkSuroundings(Entity entity);

}
