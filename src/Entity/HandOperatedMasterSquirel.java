package Entity;

import Console.MoveCommand;
import Movement.XY;

import java.util.Scanner;

public class HandOperatedMasterSquirel extends MasterSquirel {



    public HandOperatedMasterSquirel(int energy, XY pos){
        super(1000 ,pos, EntityType.HANDOPERATEDMASTERQUIRREL);
    }

    @Override
    public void nextStep(EntitySet entities) {

        MoveCommand moveCommand = new MoveCommand(this.getPosition());
        this.setPosition(moveCommand.command());
        resolveColission(entities);
    }

}
