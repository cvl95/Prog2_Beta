package CommandFactory;

public class Addi implements Command {


    MyfavouriteCommands myfavouriteCommands;
    Object[] params;
    public Addi(MyfavouriteCommands myfavouriteCommands,Object[]params){
        this.myfavouriteCommands = myfavouriteCommands;
        this.params = params;
    }
    @Override
    public void execute() {
        myfavouriteCommands.addi((Integer) params[0], (Integer) params[1]);
    }
}
