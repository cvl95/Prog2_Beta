package Commandos;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;

public class MyFavouriteCommandProcessor {
    private PrintStream outputStream = System.out;
    private BufferedReader inputReader = new BufferedReader(new InputStreamReader(System.in));
    private CommandScanner commandScanner = new CommandScanner(MyFavouriteCommandType.values(), inputReader);


    void process() throws IOException {

        while(true){

            Command command = commandScanner.next();

            Object[] params = command.getParams();
            Object result;

            MyFavouriteCommandType commandType = (MyFavouriteCommandType) command.getCommandType();

            switch (commandType){
                case EXIT:
                    System.exit(0);
                    break;
                case HELP:
                    help();
                    break;
                case ADDI:
                    result = (Integer) params[0] + (Integer) params[1];
                    break;
                case ADDF:
                    result = (Float) params[0] + (Float) params[1];
                    break;
                case ECHO:
                    System.out.println();
            }

        }
    }
    void help(){
        String helpText = null;
        for(CommandTypeInfo help: commandScanner.getCommandTypeInfos()){
            if (helpText== null){
                helpText = help.getName();
            }
            else
                helpText = helpText + help.getName();
        }
        outputStream.append(helpText);
    }
}
