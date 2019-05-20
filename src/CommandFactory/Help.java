package CommandFactory;

public class Help implements Command {

    MyfavouriteCommands myfavouriteCommands;
    public Help(MyfavouriteCommands myfavouriteCommands){
        this.myfavouriteCommands = myfavouriteCommands;
    }

    @Override
    public void execute() {
        myfavouriteCommands.help();

    }
}
