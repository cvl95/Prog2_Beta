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
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        input = input.toLowerCase();
        return input;

    }

    public String getCommand() {
        return command;
    }




}
