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

    @Override
    public void processInput() {

    }

}
    //Ein Eingabekommando abholen (zunächst nur nächster Move des Eichhörnchens im Single-Player-Modus)
    // und das Rendern des Boards, was bedeutet, dass der Render-Code einen Blick auf das Board werfen mus

