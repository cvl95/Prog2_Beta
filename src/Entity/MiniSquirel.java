package Entity;

import Core.EntityContext;
import Movement.XY;

import java.util.List;

public class MiniSquirel extends MasterSquirel {


    public MiniSquirel(int energy, XY pos){
        super(energy,pos);
    }

    public List<MiniSquirel> getSuperMiniSquirelList() {
         return super.getMiniSquirelList();
    }

    @Override
    public void nextStep(EntityContext context) {
        this.setPosition(this.getPosition().getNewPosition());
    }
}
