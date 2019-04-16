package GameEngine;
import Console.ConsoleUI;
import Console.UI;
import Core.Board;
import Core.BoardConfig;
import Core.FlattenedBoard;
import Entity.EntitySet;


public class Game {

    UI ui = new ConsoleUI();
    EntitySet entitySet = new EntitySet();
    BoardConfig boardConfig;
    Board board;
    State state;
    FlattenedBoard flattenedBoard;

    Game() throws Exception{
        boardConfig = new BoardConfig(30,30,30);
        board = new Board(entitySet,boardConfig);
        state = new State(1000, board);
        flattenedBoard = new FlattenedBoard(board);
    }
    

    public void run(){
        while (true){
            render(ui);
            processInput();
            update();
        }
    }

    public void render(UI ui){
        ui.render(flattenedBoard);
    }

    public void update(){

        //verändert (ggf. unter Berücksichtigung der Eingabe) den aktuellen Spielzustand
        // , bereitet diesen also auf den nächsten Render-Vorgang vor
    }

    public void processInput(){

        //verarbeitet Benutzereingaben
    }


}
