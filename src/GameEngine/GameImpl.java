package GameEngine;

import Console.ConsoleUI;
import Console.MoveCommand;
import Console.UI;
import Core.Board;
import Core.BoardConfig;
import Entity.EntitySet;
import Entity.HandOperatedMasterSquirel;
import Movement.XY;

public class GameImpl extends Game {
    //command results
    private static final String UP = "w";
    private static final String DOWN = "s";
    private static final String LEFT = "a";
    private static final String RIGHT = "d";
    private static final String UPLEFT = "wa";
    private static final String UPRIGHT = "wd";
    private static final String DOWNRIGHT = "sd";
    private static final String DOWNLEFT = "sa";

    private final UI consoleUI = new ConsoleUI();
    private MoveCommand command;

    public GameImpl(State state){
        super(state);
    }
    @Override
    protected void render() {
        consoleUI.render(getState().getFlattenedBoard());
    }

    @Override
    protected void update() {
        getState().update();
    }

    @Override
    protected void processInput() {
        //printHelp();
        String input =consoleUI.getCommand().getCommand();
        switch (input){
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
            case UPLEFT:
                getState().getBoard().getEntitySet().findHandoperated().setMovementDirection(new XY(-1,-1));
                break;
            case UPRIGHT:
                getState().getBoard().getEntitySet().findHandoperated().setMovementDirection(new XY(-1,1));
                break;
            case DOWNLEFT:
                getState().getBoard().getEntitySet().findHandoperated().setMovementDirection(new XY(1,-1));
                break;
            case DOWNRIGHT:
                getState().getBoard().getEntitySet().findHandoperated().setMovementDirection(new XY(1,1));
                break;
            default:
                printHelp();
                getState().getBoard().getEntitySet().findHandoperated().setMovementDirection(new XY(0,0));
                break;
        }
    }
    private void printHelp(){
        System.out.println("Please enter a direction you want move: \n" +
                "up         = w \n" +
                "down       = s \n" +
                "left       = a \n" +
                "right      = d \n" +
                "up left    = wa \n" +
                "down left  = sa \n" +
                "up right   = wd \n"+
                "down right = sd ");
    }

}
