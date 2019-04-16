package Core;

import Entity.*;
import Movement.XY;

public class FlattenedBoard implements Boardview {

    Entity[][] flattenedBoard;

    public FlattenedBoard(Entity[][] flattenedBoard){
        this.flattenedBoard = flattenedBoard;
    }

    @Override
    public Entity[][] getBoardRep() {
        return flattenedBoard;
    }

    @Override
    public EntityType getEntityType(int x, int y) {

    }

    @Override
    public XY getSize() {
        return null;
    }

}
