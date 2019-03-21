package GameEngine;



public class Game {


    public void run(){
        while (true){
            render();
            processInput();
            update();
        }
    }

    public void render(){

        //Methode stellt jeweils den Spielzustand auf dem Ausgabemedium dar
    }

    public void update(){

        //verändert (ggf. unter Berücksichtigung der Eingabe) den aktuellen Spielzustand
        // , bereitet diesen also auf den nächsten Render-Vorgang vor
    }

    public void processInput(){

        //verarbeitet Benutzereingaben
    }


}
