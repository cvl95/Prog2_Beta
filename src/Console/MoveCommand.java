package Console;

import Entity.HandOperatedMasterSquirel;
import Movement.XY;

import java.util.Scanner;

public class MoveCommand {

    private static final int UP = 119;
    private static final int DOWN = 115;
    private static final int LEFT = 97;
    private static final int RIGHT = 100;
    private static final int UPLEFT = UP+LEFT;
    private static final int UPRIGHT = UP+RIGHT;
    private static final int DOWNRIGHT = DOWN+RIGHT;
    private static final int DOWNLEFT = DOWN+LEFT;
    XY pos;

    public MoveCommand() {
        command();
    }
    public MoveCommand(XY pos){
        this.pos = pos;
    }

    public XY command(){
        Scanner scanner = new Scanner(System.in);
        printHelp();
        String input = scanner.nextLine();
        input = input.toLowerCase();
        char ch = input.charAt(0);
        int str = ch;
        if(input.length()>1) {
            char ch2 = input.charAt(1);
            str= ch+ch2;
        }
        switch (str){
            case UP:
                return pos.getUserInputLoc(0, -1);
            case DOWN:
                return pos.getUserInputLoc(0, 1);
            case LEFT:
                return pos.getUserInputLoc(-1, 0);
            case RIGHT:
                return pos.getUserInputLoc(1, 0);
            case UPLEFT:
                return pos.getUserInputLoc(-1, -1);
            case UPRIGHT:
                return pos.getUserInputLoc(1, -1);
            case DOWNLEFT:
                return pos.getUserInputLoc(0, -1);
            case DOWNRIGHT:
                return pos.getUserInputLoc(1, 1);
            default:
                System.out.println("Please enter correct directions. ");
                printHelp();
                return pos.getUserInputLoc(0, 0);
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

    public XY getPos() {
        return pos;
    }
}
