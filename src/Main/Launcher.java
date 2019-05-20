package Main;

import Core.Board;
import Core.BoardConfig;
import GameEngine.Game;
import GameEngine.GameMode;
import GameEngine.State;
import javafx.application.Application;
import javafx.stage.Stage;

public class Launcher extends Application{
    private static GameMode gameMode;
    private static BoardConfig boardConfig;
    private static Board board;
    private static State state;

    public static void main(String[]args)throws Exception{
        if(args.length < 1){
            optionHelp();
            System.exit(1);
        }
        Launcher launcher = new Launcher();
        if(args[0].equals("-singleplayer")){

        }


    }

    @Override
    public void start(Stage stage) throws Exception {

    }
    private static void chooseGameMode(GameMode gameMode){
        
    }

    public static void optionHelp(){
        System.out.println("-singleplayer ( -console | -fx ) | -ai");
    }
}
