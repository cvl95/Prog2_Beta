package Entity;

import Core.EntityContext;
import Movement.XY;
import Movement.XYSupport;

import java.util.List;

public class GoodBeast extends Beast {
    public int ACTION_TURN = 4;
    public static int VIEW_RANGE = 6;
    private int COUNTER=0;
    public static int START_ENERGY = 200;


    public GoodBeast(int energy, XY pos) {
        super(200, pos);
    }

    @Override
    public EntityType getType() {
        return EntityType.GOOD_BEAST;
    }

    @Override
    public void nextStep(EntityContext context) {
        COUNTER++;
        if (COUNTER == ACTION_TURN) {
            COUNTER = 0;
            PlayerEntity playerEntity = context.nearestPlayerEntity(getPosition());
            if (playerEntity == null) {
                return;
            }
            int distance = context.calculateDistance(getPosition(), playerEntity.getPosition());
            if (distance > GoodBeast.VIEW_RANGE) {
                return;
            }
            XY[] direction = XYSupport.getDirections();
            for (int i = 0; i < direction.length; i++) {
                XY newPosition = getPosition().plus(direction[i]);
                if (context.testArrayBounds(newPosition) && !context.isOccupied(newPosition)
                        && (context.calculateDistance(newPosition, playerEntity.getPosition()) > distance)) {
                    context.tryMove(this, direction[i]);
                    break;
                }
            }
        }
    }
}
