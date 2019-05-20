package Util.ui.cosoleTest;


import Commandos.ConsoleCommandType;
import Console.ConsoleUtils;

import java.io.PrintStream;
import java.util.List;

public class MyFavoriteCommandsImpl implements MyFavoriteCommandType {
    private final List<ConsoleCommandType> commandTypes = ConsoleCommandType.getCommandTypes(this, MyFavoriteCommandType.class);
    private final PrintStream outputStream = System.out;

    @Override
    public void help() {
        outputStream.println(ConsoleUtils.getFormattedHelp(commandTypes));
    }

    @Override
    public void exit(){
        System.exit(0);
    }

    @Override
    public int addi(int a, int b){
        return a + b;
    }

    @Override
    public float addf(float a, float b){
        return a + b;
    }

    @Override
    public void echo(String message, int count){
        for (int i = 0; i < count; i++) {
            outputStream.println(message);
        }
    }
}