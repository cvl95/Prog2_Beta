package Core;

import Entity.*;

public class FlattenedBoard implements Boardview {

    Entity[][] flattenedBoard;

    public FlattenedBoard(Entity[][] flattenedBoard){
        this.flattenedBoard = flattenedBoard;
    }

    @Override
    public Entity[][] getBoardRep() {
        return flattenedBoard;
    }
}
