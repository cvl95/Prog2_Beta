package Entity;

import Core.EntityContext;
import Movement.XY;

import java.util.List;

public class GoodBeast extends Beast {
    int counter=0;

    public GoodBeast(int energy, XY pos) {
        super(200, pos);
    }

    @Override
    public void nextStep(EntityContext context) {

        if (counter==0) {
            List surround = context.checkSuroundings(this);
            XY direction = null;
            for(Object entity: surround) {

                if (entity instanceof Squirel) {
                    int x = this.getPosition().getX() - entity.getPosition().getX();
                    int y = this.getPosition().getY() - entity.getPosition().getY();
                    direction = this.culcRun(x,y);

                }

            }
            if (direction != null)
                context.tryMove(this,direction);
            else
                context.tryMove(this, this.getPosition().getNewPosition());

        }
        counter++;
        if (counter == 3)
            counter = 0;
    }
}
