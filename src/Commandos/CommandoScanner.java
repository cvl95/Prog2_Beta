package Commandos;

import java.io.BufferedReader;
import java.io.PrintStream;

public class CommandoScanner {
    // Kommandozeile einlesen, das zugehörige Kommando ermitteln,
    // die Syntax der Parameter prüfen (Validierung)
    // und von der String-Repräsentation auf die im Backend gebrauchten Datentypen umwandeln (Konvertierung).
    // Um die Eingaben des Benutzers validieren zu können, also entscheiden zu können, welches Kommando der Benutzer ausgewählt hat


    //einer Quellcode-Datei for commandos
    //ECHO("echo", "<param1>  <param2>   * echos param1 string param2 times ",String.class, int.class ),
    // zuruckliefern einem Command-Objekt neben der Aussage, welches Kommando eingegeben wurde auch die Liste der vom Benutzer eingegebenen Parameter zum Kommando


    private CommandTypeInfo[] commandTypeInfos;
    private BufferedReader inputReader;
    private PrintStream outputstream;

    public CommandoScanner(CommandTypeInfo[] commandTypes, BufferedReader inputReader){
        this.commandTypeInfos = commandTypes;
        this.inputReader = inputReader;
    }

    public Command next(){
        return null;
    }

}
