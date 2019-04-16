package Entity;

import Movement.XY;

public class BadBeast extends Entity {

    public BadBeast(int energy, XY pos) {
        super(-150, pos, EntityType.BADBEAST);
    }

    @Override
    public void nextStep(EntitySet entities) {
       this.setPosition(this.getPosition().getNewPosition());
    }
}
