package Console;

public enum GameCommandType {
    HELP("help", " * list all commands "),
    EXIT("exit", " * exit programm"),
    ADDI("addi", "<param1>  <param2>   * simple integer add ",int.class, int.class ),
    ADDF("addf", "<param1>  <param2>   * simple float add ",float.class, float.class ),
    ECHO("echo", "<param1>  <param2>   * echos param1 string param2 times ",String.class, int.class )

    GameCommandType(String comand,String helpText,){

    }
}
