package Entity;

import Console.MoveCommand;
import Core.EntityContext;
import Movement.XY;

import java.util.Scanner;

public class HandOperatedMasterSquirel extends MasterSquirel {
    XY movementDirection;

    public HandOperatedMasterSquirel(int energy, XY pos){
        super(1000,pos);
    }

    public void setMovementDirection(XY movementDirection) {
        this.movementDirection = movementDirection;
    }

    @Override
    public void nextStep(EntityContext context) {
        System.out.println(this);
        if(this.getStun() == 0){
            context.tryMove(this,movementDirection);
        }else {
            this.setStun(this.getStun()-1);
        }


    }

}
