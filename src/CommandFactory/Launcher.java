package CommandFactory;

public class Launcher {
    public static void main(String[]args){
        CommandReceiver commandReceiver = Receiver.getReceiver();
        Help help = new Help(commandReceiver);
        CommandInvoker commandInvoker = new CommandInvoker(help);

        commandInvoker.invoke();

    }
}
