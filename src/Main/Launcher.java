package Main;

import Commandos.*;
import Console.ConsoleUI;
import Core.Board;
import Core.BoardConfig;
import Entity.HandOperatedMasterSquirel;
import GameEngine.*;
import Util.ui.cosoleTest.FxUI;
import Util.ui.cosoleTest.json.BotScoreWriter;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Launcher extends Application {
    private static Logger logger = Logger.getLogger(Launcher.class.getName());
    private static String BOARD_CONFIG = "C://Users/agnet/IdeaProjects/Prog2_Beta/src/resources/board_config.properties";
    private static String SCORES_FILE_LOCATION = "scores.json";
    private static GameMode gameMode;
    private static BoardConfig boardConfig;
    private static Board board;
    private static State state;

    public static void main(String[] args){
        optionHelp();
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        String[] inputSplit = input.split(" ");

        if (inputSplit.length < 1) {
            optionHelp();
            System.exit(1);
        }
        Launcher launcher = new Launcher();

        if (inputSplit[0].equals("-singleplayer")) {
            chooseGameMode(gameMode.SINGLE_PLAYER);
            if (inputSplit.length < 2) {
                optionHelp();
                System.exit(1);
            }

            if (inputSplit[1].equals("-console")) {
                launcher.launchConsole(args);
            } else if (inputSplit[1].equals("-fx")) {
                Application.launch();
            } else {
                optionHelp();
                System.exit(1);
            }

        } else if (inputSplit[0].equals("-ai")) {
            chooseGameMode(GameMode.AI);
            Application.launch();


        } else if(inputSplit[0].equals("-file")){
            startWithFile(GameMode.AI);
            Application.launch();

        }else {
            optionHelp();
            System.exit(1);
        }


    }
    @Override
    public void start(Stage stage){
        Game game = null;
        FxUI fxUI = null;
        if (gameMode == GameMode.SINGLE_PLAYER) {
            HandOperatedMasterSquirel player = board.getMasterSquirel();
            UserActionsImpl userActions = new UserActionsImpl(player, state.getFlattenedBoard());
            List<KeyCommandType> commandTypes = KeyCommandType.getCommandTypes(userActions, UserActions.class);
            fxUI = FxUI.createInstance(boardConfig.getSize(), commandTypes);
            game = new FxGameImpl(state, fxUI, player);
        } else {
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
                if (gameMode == GameMode.AI) {
                    try {
                        BotScoreWriter.write(state.getSortedBotScores(), SCORES_FILE_LOCATION);
                    } catch (IOException ex) {
                        logger.log(Level.SEVERE, ex.getMessage(), ex);
                    }
                }
                System.exit(1);
            }
        });
        stage.show();
        startGame(game);
    }

    private static void chooseGameMode(GameMode mode) {
        gameMode = mode;
        boardConfig = new BoardConfig();
        board = new Board(boardConfig, gameMode);
        state = new State(board);

    }
    private static void startWithFile(GameMode mode){
        gameMode = mode;
        boardConfig = BoardConfig.load(BOARD_CONFIG);
        board = new Board(boardConfig,gameMode);
        state =new State(board);
    }

    private void startGame(Game game) {
        if (game instanceof ConsoleGameImpl) {
            ConsoleGameImpl consoleGame = (ConsoleGameImpl) game;
            consoleGame.getUserActions().help();
            consoleGame.run();
        } else if (game instanceof FxGameImpl || game instanceof AIGameImpl) {
            new Timer().schedule(new TimerTask() {
                @Override
                public void run() {
                    game.run();
                }
            }, 500);
        }
    }
    private void launchConsole(String[]args){
        UserActionsImpl userActions = new UserActionsImpl(board.getMasterSquirel(),state.getFlattenedBoard());
        List<ConsoleCommandType> commandTypes = ConsoleCommandType.getCommandTypes(userActions,UserActions.class);
        CommandScanner scanner = new CommandScanner(commandTypes, new BufferedReader(new InputStreamReader(System.in)));
        Game game;
        ConsoleUI consoleUI = new ConsoleUI(scanner);
        game = new ConsoleGameImpl(state,consoleUI,userActions);
        startGame(game);
    }
    public static void optionHelp(){
        System.out.println("-singleplayer ( -console | -fx ) | -ai | -file");
    }
}

