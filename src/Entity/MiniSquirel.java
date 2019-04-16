package Entity;

import Core.EntityContext;
import Movement.XY;

public class MiniSquirel extends Entity {


    public MiniSquirel(int energy, XY pos){
        super(energy,pos);
    }

    @Override
    public void nextStep(EntityContext context) {
        this.setPosition(this.getPosition().getNewPosition());
    }
}
