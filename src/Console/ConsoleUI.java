package Console;
import Commandos.Command;
import Commandos.CommandScanner;
import Commandos.ExecutableCommand;
import Core.Boardview;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class ConsoleUI implements UI {

    private BufferedReader inputreader = new BufferedReader(new InputStreamReader(System.in));
    private CommandScanner commandScanner;

    public ConsoleUI(CommandScanner commandScanner){
        this.commandScanner= commandScanner;
    }

    public CommandScanner getCommandScanner() {
        return commandScanner;
    }

    @Override
    public ExecutableCommand getCommand(){
        try {
            ExecutableCommand command =  commandScanner.next();
          return  command;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void render(Boardview boardview) {
        System.out.println();
        System.out.print(boardview);
        System.out.println();
    }

    @Override
    public void message(String msg) {
        System.out.println(msg);
    }






}

