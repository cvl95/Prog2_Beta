package Entity;

import Movement.XY;

public class GoodBeast extends Beast {
    int counter=0;

    public GoodBeast(int energy, XY pos) {
        super(200, pos);
    }

    @Override
    public void nextStep(EntitySet entities) {
        if (counter==0)
            this.setPosition(this.getPosition().getNewPosition());

        counter++;
        if (counter ==3)
            counter = 0;
    }
}
