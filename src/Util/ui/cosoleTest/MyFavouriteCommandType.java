package Util.ui.cosoleTest;

import Commandos.AsConsoleCommand;


public /*enum*/ interface MyFavouriteCommandType /*implements CommandTypeInfo*/ {
    @AsConsoleCommand(name = "help", helpText = "* list all commands", order = 0)
    void help();

    @AsConsoleCommand(name = "exit", helpText = "* exit program", order = 1)
    void exit();

    @AsConsoleCommand(name = "addi", helpText = "<param1>  <param2>  * simple integer add", order = 2)
    int addi(int a, int b);

    @AsConsoleCommand(name = "addf", helpText = "<param1>  <param2>  * simple float add", order = 3)
    float addf(float a, float b);

    @AsConsoleCommand(name = "echo", helpText = "<param1>  <param2>  * echos param1 string param2 times", order = 4)
    void echo(String message, int count);
}
  /*  HELP("help", " * list all commands "),
    EXIT("exit", " * exit programm"),
    ADDI("addi", "<param1>  <param2>   * simple integer add ",int.class, int.class ),
    ADDF("addf", "<param1>  <param2>   * simple float add ",float.class, float.class ),
    ECHO("echo", "<param1>  <param2>   * echos param1 string param2 times ",String.class, int.class );

    private String commandName;
    private String helptext;
    private Class<?>[] parameters;

    MyFavouriteCommandType(String commandName, String helpText, Class<?>... param){
        this.commandName = commandName;
        this.helptext = helpText;
        this.parameters = param;
    }

    @Override
    public String getName() {

        return this.commandName;
    }

    @Override
    public String getHelpText() {
        return this.helptext;
    }

    @Override
    public Class<?>[] getParamTypes() {
        return parameters;
    }
}/*
