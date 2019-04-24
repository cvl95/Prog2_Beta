package Console;

import Core.Boardview;

public interface UI {
    MoveCommand getCommand();
    void render(Boardview boardview);

}
