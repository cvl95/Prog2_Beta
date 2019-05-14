package Commandos;

public enum MyFavouriteCommandType implements CommandTypeInfo {
    HELP("help", " * list all commands "),
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
}
