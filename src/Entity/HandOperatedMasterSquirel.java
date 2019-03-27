package Entity;

import Console.MoveCommand;
import Movement.XY;
import org.graalvm.compiler.lir.sparc.SPARCMove;

import java.util.Scanner;

public class HandOperatedMasterSquirel extends MasterSquirel {



    public HandOperatedMasterSquirel(int id, XY pos){
        this.setId(id);
        this.setPosition(pos);
        this.updateEnergy(1000);
    }

    @Override
    public void nextStep(EntitySet entities) {
        MoveCommand moveCommand = new MoveCommand(this.getPosition());
        this.setPosition(moveCommand.command());
        resolveColission(entities);
    }

}
