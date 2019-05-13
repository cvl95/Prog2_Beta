package GameEngine;
import Console.ConsoleUI;
import Console.MoveCommand;
import Console.UI;
import Core.EntityContext;
import Core.FlattenedBoard;
import Movement.XY;


public abstract class Game {

    private State state;

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

    public State getState() {
        return state;
    }

    protected abstract void render();
    protected abstract void update();
    protected abstract void processInput();

}
