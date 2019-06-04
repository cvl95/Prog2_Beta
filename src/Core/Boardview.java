package Core;
import Entity.*;
import Movement.XY;

/**
 * provides methods that help by rendering
 */

public interface Boardview {
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
     *
     * @return gamefield from game
     */
    Entity[][] getGameField();
}
