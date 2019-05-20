package CommandFactory;

public class Echo implements Command {
    MyfavouriteCommands myfavouriteCommands;
    Object[] params;
    public Echo(MyfavouriteCommands myfavouriteCommands,Object[]params){
        this.myfavouriteCommands = myfavouriteCommands;
        this.params = params;
    }

    @Override
    public void execute() {
        System.out.print(myfavouriteCommands.getClass());
        for(int i = 0; i<params.length;i++){
            System.out.print(params[0]);
        }

    }
}
