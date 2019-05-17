package GameEngine;


import Console.UI;

public abstract class Game {
    protected UI ui;
    private State state;

    Game(State state, UI ui){
        this.state = state;
        this.ui = ui;
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

    public UI getUi() {
        return ui;
    }

    protected abstract void render();
    protected abstract void update();
    protected abstract void processInput();

}
