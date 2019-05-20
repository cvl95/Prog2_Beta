package Console;

import Commandos.CommandTypeInfo;

import java.lang.reflect.Method;

public enum GameCommandType implements CommandTypeInfo {
    HELP("help", " * list all commands "),
    EXIT("exit", " * exit programm "),
    ALL("all", " * lists all beasts "),
    UP("w", " * moves up "),
    DOWN("s", " * moves down "),
    LEFT("a", " * moves left "),
    RIGHT("d", " * moves right "),
    UP_RIGHT("wd", " * moves up right "),
    UP_LEFT("wa", " * moves up left "),
    DOWN_RIGHT("sd", " * moves down right "),
    DOWN_LEFT("sa", " * moves down left "),
    MASTER_ENERGY("status", " * shows Master squirel status "),
    SPAWN_MINI("spawn", " * spawns mini squirel ", int.class);

    private String name;
    private String helptext;
    Class<?>[] parameters;


    GameCommandType(String command,String helpText, Class<?>... parameters ){
        this.name = command;
        this.helptext = helpText;
        this.parameters = parameters;
    }


    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getHelpText() {
        return helptext;
    }

    @Override
    public Class<?>[] getParamTypes() {
        return parameters;
    }

    @Override
    public String getMethodName() {
        return null;
    }

    @Override
    public Method getMethod() {
        return null;
    }

    @Override
    public Object getTarget() {
        return null;
    }

}
