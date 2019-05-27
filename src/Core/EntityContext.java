package Core;

import Entity.*;
import Movement.XY;

/**
 * Provides methods that can be called by entities in their nextStep() method
 */
public interface EntityContext {
        /**
         *
         * @return the size of the game board, where x is the width and y is the height
         */
        XY getSize();
        /**
         *
         * @param position the position of the entity
         * @return the type of the entity
         */
        EntityType getEntityType(XY position);

        /**
         *
         * @param position1 of entity
         * @param position2 of entity
         * @return int in amount of distance
         */

        int calculateDistance(XY position1, XY position2);

        /**
         *
         * @param masterSquirel MasterSquirrel instance
         * @param moveDirection direction in which the entity will be moved (one of the constants in the XY class)
         */
        void tryMove( MasterSquirel masterSquirel, XY moveDirection);
        /**
         *
         * @param miniSquirel MiniSquirrel instance
         * @param moveDirection direction in which the entity will be moved (one of the constants in the XY class)
         */
        void tryMove(MiniSquirel miniSquirel, XY moveDirection);
        /**
         *
         * @param goodBeast GoodBeast instance
         * @param moveDirection direction in which the entity will be moved (one of the constants in the XY class)
         */
        void tryMove(GoodBeast goodBeast, XY moveDirection);
        /**
         *
         * @param badBeast BadBeast instance
         * @param moveDirection direction in which the entity will be moved (one of the constants in the XY class)
         */
        void tryMove(BadBeast badBeast, XY moveDirection);
        /**
         *
         * @param position the position of the caller
         * @return the nearest PlayerEntity instance (MasterSquirrel or MiniSquirrel)
         */
        PlayerEntity nearestPlayerEntity(XY position);

        /**
         * Removes the specified entity from the game board
         * @param entity the entity to be removed
         */
        void kill(Entity entity);
        /**
         * Removes the specified entity and creates a new one randomly placing it on the game board
         * @param entity the entity to be removed
         */
        void killAndReplace(Entity entity);

        /**
         *
         * @param entity the point of origin
         * @return a list of free positions around the specified point
         */
        Entity[] checkSuroundings(Entity entity);

        /**
         *
         * @param position
         * @return returns true
         */

        boolean testArrayBounds(XY position);
        /**
         *
         * @param position a position on the game board
         * @return true if the position is occupied by an entity
         */
        boolean isOccupied(XY position);

        /**
         *
         * @param position
         * @return free xy is returned
         */

        XY getFreeSurrounding(XY position);
        /**
         *
         * @param position the position of the entity
         * @return the type of the entity
         */
        Entity getEntityAt(XY position);


}
