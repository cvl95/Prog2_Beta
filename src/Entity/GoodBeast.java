package Entity;

import Core.EntityContext;
import Movement.XY;

public class GoodBeast extends Entity {
    int counter=0;

    public GoodBeast(int energy, XY pos) {
        super(200, pos);
    }

    @Override
    public void nextStep(EntityContext context) {
        if (counter==0)
            this.setPosition(this.getPosition().getNewPosition());

        counter++;
        if (counter ==3)
            counter = 0;
    }
}
