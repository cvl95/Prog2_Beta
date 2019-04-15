package GameEngine;
import Console.ConsoleUI;
import Console.UI;



public class Game {

    UI ui = new ConsoleUI();
    

    public void run(){
        while (true){
            render(ui);
            processInput();
            update();
        }
    }

    public void render(UI ui){

        ui.render();
    }

    public void update(){

        //verändert (ggf. unter Berücksichtigung der Eingabe) den aktuellen Spielzustand
        // , bereitet diesen also auf den nächsten Render-Vorgang vor
    }

    public void processInput(){

        //verarbeitet Benutzereingaben
    }


}
