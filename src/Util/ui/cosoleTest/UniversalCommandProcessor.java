package Util.ui.cosoleTest;

import Commandos.*;
import Util.ui.cosoleTest.console.Exceptions.ScanExceptions;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.lang.reflect.Executable;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

public class UniversalCommandProcessor {
    private final BufferedReader inputReader = new BufferedReader(new InputStreamReader(System.in));
    private final PrintStream outputStream = System.out;
    private final Object target;
    private final Class<?> interf;

    public UniversalCommandProcessor(Object target, Class<?> interf) {
        this.target = target;
        this.interf = interf;
    }

    public void process() {
        List<ConsoleCommandType> commandTypeInfos = ConsoleCommandType.getCommandTypes(target, interf);
        CommandScanner commandScanner = new CommandScanner(commandTypeInfos, inputReader);

        while (true) {
            prompt();
            ExecutableCommand command;
            try {
                command = commandScanner.next();
            } catch (IOException | ScanExceptions ex) {
                outputStream.println(ex.getMessage());
                continue;
            }

            Object result;
            try {
                result = command.execute();
            }
             catch (InvocationTargetException | IllegalAccessException ex) {
                outputStream.println(ex.getMessage());
                continue;
            }

            if (result != null) {
                outputStream.println(result);
            }
        }
    }

    private void prompt() {
        System.out.println("> ");
    }





















































/*    private PrintStream outputStream = System.out;
    private BufferedReader inputReader = new BufferedReader(new InputStreamReader(System.in));
    private CommandScanner commandScanner = new CommandScanner(MyFavoriteCommandType.values(), inputReader);

    public  static void main(String[]args){

        UniversalCommandProcessor myFavouriteCommandProcessor = new UniversalCommandProcessor();
        try{
            myFavouriteCommandProcessor.process();
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    void process() throws IOException {

        while(true){
            outputStream.println(">> ");
            Command command = commandScanner.next();

            Object[] params = command.getParams();
            Object result;

            MyFavoriteCommandType commandType = (MyFavoriteCommandType) command.getCommandType();

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

    void echo(Object[] input){
        for(int i = 0; i < input.length;i ++){
           outputStream.print(input[i] + " ");
        }

    }*/
}
