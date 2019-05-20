package CommandFactory;

public class Receiver {

    public static CommandReceiver getReceiver(){
        return new CommandReceiver();
    }
}
