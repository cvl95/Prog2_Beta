package Entity;

import Movement.XY;

public class BadBeast extends Entity {

    public BadBeast(int energy, XY pos) {
        super(-150, pos);
    }

    @Override
    public void nextStep(EntitySet entities) {
        this.getPosition().getNewPosition();
    }
}
