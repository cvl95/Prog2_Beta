package CommandFactory;

public class Addf implements Command {

    MyfavouriteCommands myfavouriteCommands;
    Object[] params;
    public Addf(MyfavouriteCommands myfavouriteCommands,Object[]params) {
        this.myfavouriteCommands = myfavouriteCommands;
        this.params = params;
    }
    @Override
    public void execute(){
        myfavouriteCommands.addf((Float)params[0],(Float)params[1]);
    }
}
