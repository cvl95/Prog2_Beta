package Commandos;

import Commandos.Exceptions.ScanExceptions;
import com.sun.tools.jdeprscan.scan.Scan;

import javax.print.DocFlavor;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintStream;

public class CommandScanner {

    private CommandTypeInfo[] commandTypeInfos;
    private BufferedReader inputReader;
    private PrintStream outputstream;

    public CommandScanner(CommandTypeInfo[] commandTypes, BufferedReader inputReader){
        this.commandTypeInfos = commandTypes;
        this.inputReader = inputReader;
    }

    public Command next() throws IOException {
        String input = inputReader.readLine().toLowerCase();
        return parseInput(input);
    }

    public Command parseInput(String input){
        String[] parts = input.split(" ");
        for(int i = 0; i < commandTypeInfos.length; i++){
            if(commandTypeInfos[i].getName().equals(parts[0])){
                Class<?>[] parameters = commandTypeInfos[i].getParamTypes();
                if(parameters.length != parts.length-1){
                    throw new ScanExceptions("Expected parameter number is: " + parameters.length);
                }
                Object[] convertedParameters = new Object[parameters.length];
                for(int j = 0; j < convertedParameters.length; j++){
                    convertedParameters[j] = convert(parameters[j], parts[j+1]);
                }
                return new Command(commandTypeInfos[i], convertedParameters);

            }
        }
        throw new ScanExceptions("No command exists");

    }
    private Object convert(Class<?> object, String value){
        if(object.equals(int.class)){
            try{
                return Integer.parseInt(value);
            }catch (ScanExceptions ex){
                throw  new ScanExceptions(value + " wrong Integer format");
            }
        }else if(object.equals(float.class)){
            try {
                return Float.parseFloat(value);
            }catch (ScanExceptions ex){
                throw new ScanExceptions(value + " wrong float value.");
            }
        }else if (object.equals(String.class)){
            return value;
        }
        return null;

    }

    public CommandTypeInfo[] getCommandTypeInfos() {
        return commandTypeInfos;
    }
}
