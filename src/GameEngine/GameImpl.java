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
    private static final int UP = 119;
    private static final int DOWN = 115;
    private static final int LEFT = 97;
    private static final int RIGHT = 100;
    private static final int UPLEFT = UP+LEFT;
    private static final int UPRIGHT = UP+RIGHT;
    private static final int DOWNRIGHT = DOWN+RIGHT;
    private static final int DOWNLEFT = DOWN+LEFT;

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
        printHelp();
        command = consoleUI.getCommand();
        String input = command.getCommand();
        char ch = input.charAt(0);
        int str = ch;
        if(input.length()>1) {
            char ch2 = input.charAt(1);
            str= ch+ch2;
        }
        switch (str){
            case UP:
                getState().getBoard().getEntitySet().findHandoperated().setMovementDirection(new XY(0,-1));
                break;
            case DOWN:
                getState().getBoard().getEntitySet().findHandoperated().setMovementDirection(new XY(0,1));
                break;
            case LEFT:
                getState().getBoard().getEntitySet().findHandoperated().setMovementDirection(new XY(-1,0));
                break;
            case RIGHT:
                getState().getBoard().getEntitySet().findHandoperated().setMovementDirection(new XY(1,0));
                break;
            case UPLEFT:
                getState().getBoard().getEntitySet().findHandoperated().setMovementDirection(new XY(-1,-1));
                break;
            case UPRIGHT:
                getState().getBoard().getEntitySet().findHandoperated().setMovementDirection(new XY(1,-1));
                break;
            case DOWNLEFT:
                getState().getBoard().getEntitySet().findHandoperated().setMovementDirection(new XY(-1,1));
                break;
            case DOWNRIGHT:
                getState().getBoard().getEntitySet().findHandoperated().setMovementDirection(new XY(1,1));
                break;
            default:
                System.out.println("Please enter correct directions. ");
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
