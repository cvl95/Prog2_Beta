package Console;

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
    private  int x = 0;
    private  int y = 0;

    MoveCommand() {
        command();
    }

    private void command(){
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
                setX(0);
                setY(-1);
                break;
            case DOWN:
                setX(0);
                setY(1);
                break;
            case LEFT:
                setX(-1);
                setY(0);
                break;
            case RIGHT:
                setX(1);
                setY(0);
                break;
            case UPLEFT:
                setX(-1);
                setY(-1);
                break;
            case UPRIGHT:
                setX(1);
                setY(-1);
                break;
            case DOWNLEFT:
                setX(-1);
                setY(1);
                break;
            case DOWNRIGHT:
                setX(1);
                setY(1);
                break;
            default:
                System.out.println("Please enter correct directions. ");
                printHelp();
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

    private void setX(int x) {
        this.x = x;
    }

    private void setY(int y) {
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
