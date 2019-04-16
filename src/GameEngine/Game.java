package GameEngine;
import Console.ConsoleUI;
import Console.UI;



public abstract class Game {

    UI ui = new ConsoleUI();


    public void run(){
        while (true){
            render(ui);
            processInput(ui);
            update();
        }
    }

    public abstract void render(UI ui);

    public void update(){

    }

    public abstract void processInput(UI ui);


}
