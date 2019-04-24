package GameEngine;
import Console.ConsoleUI;
import Console.MoveCommand;
import Console.UI;
import Core.Board;
import Core.BoardConfig;
import Core.EntityContext;
import Core.FlattenedBoard;
import Entity.EntitySet;
import Movement.XY;


public class Game {
    //command results
    private static final int UP = 119;
    private static final int DOWN = 115;
    private static final int LEFT = 97;
    private static final int RIGHT = 100;
    private static final int UPLEFT = UP+LEFT;
    private static final int UPRIGHT = UP+RIGHT;
    private static final int DOWNRIGHT = DOWN+RIGHT;
    private static final int DOWNLEFT = DOWN+LEFT;
    XY pos;

    //variables for game
    UI ui = new ConsoleUI();
    EntitySet entitySet = new EntitySet();
    BoardConfig boardConfig;
    Board board;
    State state;

    Game(State state) throws Exception{
        boardConfig = new BoardConfig(30,30,30);
        board = new Board(entitySet,boardConfig);
        this.state = state;

    }
    

    public void run(){
        while (true){
            render();
            processInput();
            update();
        }
    }

    public void render(){
        ui.render(state.flattenedBoard());
    }

    public void update(){

        board.callNextStep();
        EntityContext newFlattenedboard = board.flatten();
        this.board = ((FlattenedBoard) newFlattenedboard).getBoard();
        this.entitySet = ((FlattenedBoard) newFlattenedboard).getBoard().getEntitySet();
        state.update();
    }

    public void processInput(){
        printHelp();
        MoveCommand moveCommand = new MoveCommand();
        String input = moveCommand.getCommand();
        char ch = input.charAt(0);
        int str = ch;
        if(input.length()>1) {
            char ch2 = input.charAt(1);
            str= ch+ch2;
        }
        switch (str){
            case UP:
                pos = new XY(0,-1);
                break;
            case DOWN:
                pos = new XY(0,1);
                break;
            case LEFT:
                pos = new XY(-1,0);
                break;
            case RIGHT:
                pos = new XY(1,0);
                break;
            case UPLEFT:
                pos = new XY(-1,-1);
                break;
            case UPRIGHT:
                pos = new XY(1,-1);
                break;
            case DOWNLEFT:
                pos = new XY(-1,1);
                break;
            case DOWNRIGHT:
                pos = new XY(1,1);
                break;
            default:
                System.out.println("Please enter correct directions. ");
                printHelp();
                pos = new XY(0,0);
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
