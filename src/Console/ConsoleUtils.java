package Console;


import Commandos.ConsoleCommandType;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.*;
import java.util.stream.Collectors;

public class ConsoleUtils {
    public static String getFormattedHelp(List<ConsoleCommandType> commandTypes) {
        if (commandTypes.size() == 0) {
            return "";
        }

        int maxNameLength = Collections.max(commandTypes.stream()
                .map(command -> command.getName().length())
                .collect(Collectors.toList())
        );
//        List<Integer> nameLengths = new ArrayList<>(commandTypes.size());
//        for (ConsoleCommandType command : commandTypes) {
//            nameLengths.add(command.getName().length());
//        }
//        int maxNameLength = Collections.max(nameLengths);
        String helpFormat = String.format("%%-%ds   %%s", maxNameLength);

        StringBuilder builder = new StringBuilder();
        for (ConsoleCommandType command : commandTypes) {
            String name = command.getName();
            String helpText = command.getHelpText();
            builder.append(String.format(helpFormat, name, helpText) + '\n');
        }
        return builder.toString();
    }
}