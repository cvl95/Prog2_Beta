package Core;

import Entity.*;

public class FlattenedBoard {

    Entity[][] flattenedBoard;

    public FlattenedBoard(Entity[][] flattenedBoard){
        this.flattenedBoard = flattenedBoard;
    }

    public Entity[][] getFlattenedBoard() {
        return flattenedBoard;
    }

}
