package Console;
import Core.Boardview;

public class ConsoleUi implements ui {

    @Override
    public MoveCommand getCommand() {
        return new MoveCommand();
    }

    @Override
    public void render(Boardview boardview) {

    }

    //Ein Eingabekommando abholen (zunächst nur nächster Move des Eichhörnchens im Single-Player-Modus)
    // und das Rendern des Boards, was bedeutet, dass der Render-Code einen Blick auf das Board werfen mus
}
