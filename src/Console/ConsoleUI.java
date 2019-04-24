package Console;
import Core.Boardview;
import Entity.*;

public class ConsoleUI implements UI {

    public MoveCommand getCommand() {
        return new MoveCommand();
    }

    @Override
    public void render(Boardview boardview) {
        Entity [][] flattenedBoard = boardview.getGameField();
        System.out.print(flattenedBoard.toString());
    }


}

