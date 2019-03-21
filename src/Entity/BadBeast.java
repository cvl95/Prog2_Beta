package Entity;

import Movement.XY;

public class BadBeast extends Entity {

    public BadBeast(int id,XY pos){
        this.updateEnergy(-150);
        this.setPosition(pos);
        this.setId(id);
    }

    @Override
    public void nextStep(EntitySet entities) {
        this.getPosition().getNewPosition();
    }
}
