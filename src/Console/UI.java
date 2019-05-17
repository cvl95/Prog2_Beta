package Console;

import Commandos.Command;
import Commandos.CommandScanner;
import Core.Boardview;

public interface UI {
    Command getCommand();
    void render(Boardview boardview);
    void message(String msg);
    CommandScanner getCommandScanner();

}
