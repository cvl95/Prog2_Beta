package GameEngine;
import Console.ConsoleUI;
import Console.MoveCommand;
import Console.UI;
import Core.EntityContext;
import Core.FlattenedBoard;
import Movement.XY;


public abstract class Game {

    //variables for game
    UI ui = new ConsoleUI();
    State state;

    Game(){}
    Game(State state){
        this.state = state;
    }
    public void run(){
        while (true){
            render();
            processInput();
            update();
        }
    }

    protected abstract void render();

    protected void update(){
        state.update();
    }

    protected abstract void processInput();

}
