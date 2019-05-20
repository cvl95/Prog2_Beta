package CommandFactory;

public class Exit implements Command {


    MyfavouriteCommands myfavouriteCommands;
    public Exit(MyfavouriteCommands myfavouriteCommands){
        this.myfavouriteCommands = myfavouriteCommands;
    }
    @Override
    public void execute() {
        myfavouriteCommands.exit();
    }
}
