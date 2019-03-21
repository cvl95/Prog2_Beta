package Entity;

import Movement.XY;

public class GoodBeast extends Entity {

     public GoodBeast(int id,XY pos){
        this.updateEnergy(200);
        this.setPosition(pos);
        this.setId(id);
    }

    @Override
    public void nextStep(EntitySet entities) {
        this.getPosition().getNewPosition();
    }
}
