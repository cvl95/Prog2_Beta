package Console;

import Entity.HandOperatedMasterSquirel;
import Movement.XY;

import java.util.Scanner;

public class MoveCommand {

    private String command;

    public MoveCommand() {
         command = command();
    }
    public String command() {
        System.out.print(">> ");
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine().trim().toLowerCase();
        return input;

    }

    public String getCommand() {
        return command;
    }




}
