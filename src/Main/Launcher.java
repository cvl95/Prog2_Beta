package Main;

import Commandos.ExecutableCommand;
import Commandos.KeyCommandType;
import Commandos.UserActions;
import Commandos.UserActionsImpl;
import Core.Board;
import Core.BoardConfig;
import Entity.Entity;
import Entity.HandOperatedMasterSquirel;
import GameEngine.*;
import Util.ui.cosoleTest.FxUI;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.util.List;

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
        if(args[0].equals("-singleplayer")) {
            chooseGameMode(gameMode.SINGLE_PLAYER);
            if (args.length < 2) {
                optionHelp();
                System.exit(1);
            }

            if (args[1].equals("-console")) {
                launcher.launchConsole(args);
            } else if (args[1].equals("-fx")) {
                Application.launch(args);
            } else {
                optionHelp();
                System.exit(1)
            }

        }else if(args[0].equals("-ai")){
            chooseGameMode(GameMode.AI);
            Application.launch(args);
        }
        else{
            optionHelp();
            System.exit(1);
        }
    }

    @Override
    public void start(Stage stage) throws Exception {
        Game game = null;
        FxUI fxUI =null;
        if(gameMode == GameMode.SINGLE_PLAYER){
            HandOperatedMasterSquirel player = getPlayerEntity();
            UserActionsImpl userActions = new UserActionsImpl(player, state.getFlattenedBoard())
            List<KeyCommandType> commandTypes = KeyCommandType.getCommandTypes(userActions, UserActions.class);
            fxUI = FxUI.createInstance(boardConfig.getSize(), commandTypes);
            game = new FxGameImpl(state, fxUI, player);
        }
        else{
            fxUI = FxUI.createInstance(boardConfig.getSize());
            game = new AIGameImpl(state, fxUI);
        }
        stage.setScene(fxUI);
        stage.sizeToScene();
        stage.setTitle("Dilligent Squirrel");
        stage.setResizable(false);
        fxUI.getWindow().setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent windowEvent) {
                System.exit(1);
            }
        });
        stage.show();
        startGame(game);
    }
    private static void chooseGameMode(GameMode mode){
        gameMode = mode;
        boardConfig = new BoardConfig();
        board = new Board(boardConfig,gameMode);
        state = new State(board);

    }
    private void startGame(Game game){
        if(game instanceof ConsoleGameImpl){

        }
    }

    private void launchConsole(String[]args){
        //dont forget to implement

    }
    private HandOperatedMasterSquirel getPlayerEntity() {
        return state.getBoard().getEntitySet().findHandoperated();
    }
    public static void optionHelp(){
        System.out.println("-singleplayer ( -console | -fx ) | -ai");
    }
}
