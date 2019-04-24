package Entity;

import Console.MoveCommand;
import Core.EntityContext;
import Movement.XY;

import java.util.Scanner;

public class HandOperatedMasterSquirel extends MasterSquirel {



    public HandOperatedMasterSquirel(int energy, XY pos){
        super(1000,pos);
    }

    @Override
    public void nextStep(EntityContext context) {


    }

}
