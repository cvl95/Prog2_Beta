package GameEngine;

import Console.MoveCommand;
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

    public GameImpl(State state){
        super(state);
    }
    @Override
    protected void render() {
        super.ui.render(state.flattenedBoard());
    }
    @Override
    protected void processInput() {
        printHelp();
        MoveCommand moveCommand = ui.getCommand();
        String input = moveCommand.getCommand();
        char ch = input.charAt(0);
        int str = ch;
        if(input.length()>1) {
            char ch2 = input.charAt(1);
            str= ch+ch2;
        }
        switch (str){
            case UP:
                state.getBoard().getEntitySet().findHandoperated().setMovementDirection(new XY(0,-1));
                break;
            case DOWN:
                state.getBoard().getEntitySet().findHandoperated().setMovementDirection(new XY(0,1));
                break;
            case LEFT:
                state.getBoard().getEntitySet().findHandoperated().setMovementDirection(new XY(-1,0));
                break;
            case RIGHT:
                state.getBoard().getEntitySet().findHandoperated().setMovementDirection(new XY(1,0));
                break;
            case UPLEFT:
                state.getBoard().getEntitySet().findHandoperated().setMovementDirection(new XY(-1,-1));
                break;
            case UPRIGHT:
                state.getBoard().getEntitySet().findHandoperated().setMovementDirection(new XY(1,-1));
                break;
            case DOWNLEFT:
                state.getBoard().getEntitySet().findHandoperated().setMovementDirection(new XY(-1,1));
                break;
            case DOWNRIGHT:
                state.getBoard().getEntitySet().findHandoperated().setMovementDirection(new XY(1,1));
                break;
            default:
                System.out.println("Please enter correct directions. ");
                printHelp();
                state.getBoard().getEntitySet().findHandoperated().setMovementDirection(new XY(0,0));
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
