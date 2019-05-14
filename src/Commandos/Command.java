package Commandos;

public class Command {

    CommandTypeInfo commandTypeInfo;
    Object [] params;
    Command(CommandTypeInfo commandType, Object[] params){
        this.commandTypeInfo = commandType;
        this.params = params;
    }

    Object [] getParams(){
        return params;
    }
    CommandTypeInfo getCommandType(){
        return commandTypeInfo;
    }
}
