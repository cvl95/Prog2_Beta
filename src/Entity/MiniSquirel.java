package Entity;

import Movement.XY;

public class MiniSquirel extends Squirel {


    public MiniSquirel(int energy, XY pos){
        super(energy,pos);
    }

    @Override
    public void nextStep(EntitySet entities) {
        this.setPosition(this.getPosition().getNewPosition());
    }
}
