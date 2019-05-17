package Console;

import Commandos.Command;
import Entity.HandOperatedMasterSquirel;
import Movement.XY;

import java.util.Scanner;

public class MoveCommand extends Command {

    private GameCommandType gameCommandType;
    private Object [] params;

    MoveCommand(GameCommandType commandType, Object[] params){
        this.gameCommandType = commandType;
        this.params = params;
    }

    public Object [] getParams(){
        return params;
    }
    public GameCommandType getCommandType(){
        return gameCommandType;
    }


}
