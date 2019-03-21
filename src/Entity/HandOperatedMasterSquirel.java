package Entity;

import Movement.XY;
import java.util.Scanner;

public class HandOperatedMasterSquirel extends MasterSquirel {

    private static final int UP = 119;
    private static final int DOWN = 115;
    private static final int LEFT = 97;
    private static final int RIGHT = 100;
    private static final int UPLEFT = UP+LEFT;
    private static final int UPRIGHT = UP+RIGHT;
    private static final int DOWNRIGHT = DOWN+RIGHT;
    private static final int DOWNLEFT = DOWN+LEFT;


    public HandOperatedMasterSquirel(int id, XY pos){
        this.setId(id);
        this.setPosition(pos);
        this.updateEnergy(1000);
    }

    @Override
    public void nextStep(EntitySet entities) {
        Scanner scanner = new Scanner(System.in);
        printHelp();
        String input = scanner.nextLine();
        input = input.toLowerCase();

        //check collision later if else or exception
        char ch = input.charAt(0);
        int str = ch;
        if(input.length()>1) {
            char ch2 = input.charAt(1);
            str= ch+ch2;
        }

        switch (str){
            case UP:
                System.out.println("using UP");
                this.getPosition().getUserInputLoc(0,-1);
                break;
            case DOWN:
                this.getPosition().getUserInputLoc(0,1);
                break;
            case LEFT:
                this.getPosition().getUserInputLoc(-1,0);
                break;
            case RIGHT:
                this.getPosition().getUserInputLoc(1,0);
                break;
            case UPLEFT:
                this.getPosition().getUserInputLoc(-1,-1);
                break;
            case UPRIGHT:
                System.out.println("upright");
                this.getPosition().getUserInputLoc(1,-1);
                break;
            case DOWNLEFT:
                this.getPosition().getUserInputLoc(-1,1);
                break;
            case DOWNRIGHT:
                this.getPosition().getUserInputLoc(1,1);
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

}
