package Entity;

import Movement.XY;

public class BadBeast extends Entity {
    int counter = 0;
    public BadBeast(int energy, XY pos) {
        super(-150, pos);
    }

    @Override
    public void nextStep(EntitySet entities) {
        if (counter==0)
            if (colider.checkSurrondings().contains(MasterSquirel) ||  colider.checkSurrondings().contains(MiniSquirel))
            this.setPosition(this.getPosition().getNewPosition());

        counter++;
        if (counter == 3)
            counter = 0;
    }
}
