package CommandFactory;

public class CommandInvoker {
    Command newCommand;

    public CommandInvoker(Command command){
        newCommand = command;
    }

    public void invoke(){
        newCommand.execute();
    }
}

