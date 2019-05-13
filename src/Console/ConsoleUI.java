package Console;
import Core.Boardview;
import Entity.*;

public class ConsoleUI implements UI {

    public MoveCommand getCommand() {
        return new MoveCommand();
    }

    @Override
    public void render(Boardview boardview) {
        System.out.println();
        System.out.print(boardview);
        System.out.println();
    }


}

