package Commandos;

import Util.ui.cosoleTest.console.Exceptions.ParameterCountException;
import Util.ui.cosoleTest.console.Exceptions.ParameterFormatException;
import Util.ui.cosoleTest.console.Exceptions.ScanExceptions;
import Util.ui.cosoleTest.console.Exceptions.UnknownCommandException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.List;

public class CommandScanner {

    private final List<ConsoleCommandType> commandTypeInfos;
    private BufferedReader inputReader;
    private PrintStream outputstream;

    public CommandScanner(List<ConsoleCommandType> commandTypes, BufferedReader inputReader){
        this.commandTypeInfos = commandTypes;
        this.inputReader = inputReader;
    }

    public ExecutableCommand next() throws IOException  {
        String input = inputReader.readLine();
        return parseInput(input);
    }

    private ExecutableCommand parseInput(String input) {
        String[] tokens = input.split(" ");
        for (ConsoleCommandType commandInfo : commandTypeInfos){
            if (commandInfo.getName().equals(tokens[0])) {
                Class<?>[] parameterTypes = commandInfo.getParamTypes();
                if (parameterTypes.length != tokens.length - 1){
                    throw new ParameterCountException("Expected parameter count: " + parameterTypes.length);
                }
                Object[] parameters = new Object[parameterTypes.length];
                for (int j = 0; j < parameterTypes.length; j++) {
                    parameters[j] = createObject(parameterTypes[j], tokens[j + 1]);
                }
                return new ExecutableCommand(commandInfo, parameters);
            }
        }
        throw new UnknownCommandException("Unknown command");
    }
    private Object createObject(Class<?> type, String value) {
        if (type.equals(int.class)) {
            try {
                return Integer.parseInt(value);
            } catch (NumberFormatException ex) {
                throw new ParameterFormatException(value + " - invalid integer format");
            }
        }
        else if (type.equals(float.class)) {
            try {
                return Float.parseFloat(value);
            } catch (NumberFormatException ex) {
                throw new ScanExceptions(value + "- invalid float format");
            }
        }
        else if (type.equals(String.class)) {
            return value;
        }
        return null;
    }





}
