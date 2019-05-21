package GameEngine;


import Console.UI;

public abstract class Game {
    private static int FPS = 10;
    private State state;

    Game(State state){
        this.state = state;
    }
    public void run() {
        while (true){
            render();
            processInput();
            update();

            try {
                Thread.sleep(1000/FPS);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        }


    public State getState() {
        return state;
    }

    protected abstract void render();
    protected abstract void update();
    protected abstract void processInput();

}
