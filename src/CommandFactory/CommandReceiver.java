package CommandFactory;

public class CommandReceiver implements MyfavouriteCommands {




    @Override
    public void help() {
        System.out.println("this is help");

    }

    @Override
    public void exit() {
        System.exit(0);
    }

    @Override
    public void addi(int a, int b) {
        System.out.println(a + b);
    }

    @Override
    public void addf(float a, float b) {
        System.out.println(a + b);

    }

    @Override
    public void echo(String s, int a) {
        System.out.println(s + " " + a);
    }
}
