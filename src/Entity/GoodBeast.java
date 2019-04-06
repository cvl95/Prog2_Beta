package Entity;

import Movement.XY;

public class GoodBeast extends Entity {

    public GoodBeast(int energy, XY pos) {
        super(200, pos);
    }

    @Override
    public void nextStep(EntitySet entities) {
        this.setPosition(this.getPosition().getNewPosition());
    }
}
