package Commandos;

public class Command {

    CommandTypeInfo commandTypeInfo;
    Object [] parameters;
    Command(CommandTypeInfo commandType, Object[] params){
        this.commandTypeInfo = commandType;
        this.parameters = params;
    }
}
