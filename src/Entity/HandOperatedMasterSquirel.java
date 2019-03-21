package Entity;

import Movement.XY;
import java.util.Scanner;

public class HandOperatedMasterSquirel extends MasterSquirel {



    public HandOperatedMasterSquirel(int id, XY pos){
        this.setId(id);
        this.setPosition(pos);
        this.updateEnergy(1000);
    }

    @Override
    public void nextStep(EntitySet entities) {

    }

}
