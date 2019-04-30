package GameEngine;

import Core.Board;
import Core.BoardConfig;
import Entity.EntitySet;

public class GameImpl extends Game {
    BoardConfig boardConfig;
    EntitySet entitySet;
    Board board;
    State state;
    Game game;

    public GameImpl()throws Exception{

        boardConfig = new BoardConfig(50,50,30);
        entitySet = new EntitySet();
        board = new Board(entitySet,boardConfig);
        state = new State(1000,board);
        game = new Game(state);
    }


    public Game getGame() {
        return game;
    }


}
