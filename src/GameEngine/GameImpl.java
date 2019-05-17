package GameEngine;

import Commandos.Command;
import Commandos.CommandScanner;
import Console.GameCommandType;
import Console.UI;
import Movement.XY;

public class GameImpl extends Game {
    //command results
    /*private static final String UP = "w";
    private static final String DOWN = "s";
    private static final String LEFT = "a";
    private static final String RIGHT = "d";
    private static final String UPLEFT = "wa";
    private static final String UPRIGHT = "wd";
    private static final String DOWNRIGHT = "sd";*/
   // private final UI consoleUI = new ConsoleUI();
    private Command command;

    public GameImpl(State state, UI ui){
        super(state,ui);
    }
    @Override
    protected void render() {
        ui.render(getState().getFlattenedBoard());
        printLegend();
    }

    @Override
    protected void update() {
        getState().update();
    }

    @Override
    protected void processInput() {
        command =  ui.getCommand();
        GameCommandType gameCommandType = (GameCommandType) command.getCommandType();
        switch (gameCommandType){
            case EXIT:
                System.exit(0);
                break;
            case HELP:
                printCommandos();
                break;
            case ALL:
                getState().getFlattenedBoard().getBoard().getEntitySet().toString();
                break;
            case SPAWN_MINI:
                int energy = (Integer) command.getParams()[0];
                getState().getFlattenedBoard().getBoard().getEntitySet().findHandoperated().setSpawn(energy,getState().getFlattenedBoard());
                break;
            case MASTER_ENERGY:
                getState().getFlattenedBoard().getBoard().getEntitySet().findHandoperated().toString();
                break;
            case UP:
                getState().getBoard().getEntitySet().findHandoperated().setMovementDirection(new XY(-1,0));
                break;
            case DOWN:
                getState().getBoard().getEntitySet().findHandoperated().setMovementDirection(new XY(1,0));
                break;
            case LEFT:
                getState().getBoard().getEntitySet().findHandoperated().setMovementDirection(new XY(0,-1));
                break;
            case RIGHT:
                getState().getBoard().getEntitySet().findHandoperated().setMovementDirection(new XY(0,1));
                break;
            case UP_LEFT:
                getState().getBoard().getEntitySet().findHandoperated().setMovementDirection(new XY(-1,-1));
                break;
            case UP_RIGHT:
                getState().getBoard().getEntitySet().findHandoperated().setMovementDirection(new XY(-1,1));
                break;
            case DOWN_LEFT:
                getState().getBoard().getEntitySet().findHandoperated().setMovementDirection(new XY(1,-1));
                break;
            case DOWN_RIGHT:
                getState().getBoard().getEntitySet().findHandoperated().setMovementDirection(new XY(1,1));
                break;
            default:
                getState().getBoard().getEntitySet().findHandoperated().setMovementDirection(new XY(0,0));
                break;
        }
    }
    // is this correct way
    public void printCommandos(){
        StringBuilder stringBuilder = new StringBuilder();
        CommandScanner commandScanner = ui.getCommandScanner();
        for(int i = 0; i<commandScanner.getCommandTypeInfos().length; i++){
            stringBuilder.append(commandScanner.getCommandTypeInfos()[i].getName() + commandScanner.getCommandTypeInfos()[i].getHelpText() +"\n");

        }
        System.out.println(stringBuilder);
    }

    private void printLegend(){
        System.out.println("LEGEND TO CREEPS: \n" +
                            "  M - Master squirel\n" +
                            "  B - Bad Beast\n" +
                            "  G - Good Beast\n" +
                            "  b - Bad Plant\n" +
                            "  g - Good Plant\n" +
                            "  + - Wall\n" +
                            "  - - Empty");
    }

}
