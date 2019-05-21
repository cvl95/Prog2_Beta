package Console;

import Commandos.Command;
import Commandos.CommandScanner;
import Commandos.ExecutableCommand;
import Core.Boardview;

public interface UI {
    ExecutableCommand getCommand();
    void render(Boardview boardview);
    void message(String msg);
    CommandScanner getCommandScanner();

}
