package Console;

import Core.Boardview;

public interface ui {

    MoveCommand getCommand();
    void render(Boardview boardview);
}
