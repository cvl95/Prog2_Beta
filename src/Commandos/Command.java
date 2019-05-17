package Commandos;

public class Command {

    private CommandTypeInfo commandTypeInfo;
    private Object [] params;
    Command(CommandTypeInfo commandType, Object[] params){
        this.commandTypeInfo = commandType;
        this.params = params;
    }

    public Object [] getParams(){
        return params;
    }
    public CommandTypeInfo getCommandType(){
        return commandTypeInfo;
    }
}
