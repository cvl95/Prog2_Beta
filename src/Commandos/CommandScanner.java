package Commandos;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintStream;

public class CommandScanner {

    // und von der String-Repräsentation auf die im Backend gebrauchten Datentypen umwandeln (Konvertierung).

    // wurde auch die Liste der vom Benutzer eingegebenen Parameter zum Kommando
    
    // Um die Eingaben des Benutzers validieren zu können, also entscheiden zu können, welches Kommando der Benutzer ausgewählt hat
    // zuruckliefern einem Command-Objekt neben der Aussage, welches Kommando eingegeben wurde auch die Liste der vom Benutzer eingegebenen Parameter zum Kommando


    private CommandTypeInfo[] commandTypeInfos;
    private BufferedReader inputReader;
    private PrintStream outputstream;

    public CommandScanner(CommandTypeInfo[] commandTypes, BufferedReader inputReader){
        this.commandTypeInfos = commandTypes;
        this.inputReader = inputReader;
    }

    public Command next() throws IOException {//wie genau ist das hier laufen
        String input = inputReader.readLine().toLowerCase();
        for(int i = 0; i < commandTypeInfos.length; i++){
            if(input.equals(commandTypeInfos[i].getName())){
             /*   Class<?>[] paramarray=  commandTypeInfos[i].getParamTypes();
                Class<?> param= paramarray[0];
                if(param == int.class){
                    int parmType = (int)param.get;
                }*/
                return new Command(commandTypeInfos[i],commandTypeInfos[i].getParamTypes());
            }
        }
        return null;
    }

    public CommandTypeInfo[] getCommandTypeInfos() {
        return commandTypeInfos;
    }
}
