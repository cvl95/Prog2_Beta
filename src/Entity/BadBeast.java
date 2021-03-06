package Entity;

        import Core.EntityContext;
        import Movement.XY;
        import Movement.XYSupport;

public class BadBeast extends Beast {
   private int ACTION_TURN = 4;
   private static int VIEW_RANGE = 6;
   private int COUNTER = 0;
   private int SNACK = 7;
   public static int START_ENERGY = -150;


    public BadBeast(int energy, XY pos) {
        super(-150, pos);
    }

    public void setSnack() {
        this.SNACK = this.SNACK - 1;
    }

    public int getSNACK() {
        return SNACK;
    }

    @Override
    public EntityType getType() {
        return EntityType.BAD_BEAST;
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
            if (distance > BadBeast.VIEW_RANGE) {
                return;
            }
            XY[] direction = XYSupport.getDirections();
            for (int i = 0; i < direction.length; i++) {
                XY newPosition = getPosition().plus(direction[i]);
                if (context.testArrayBounds(newPosition) && !context.isOccupied(newPosition)
                        && (context.calculateDistance(newPosition, playerEntity.getPosition()) < distance)) {
                    context.tryMove(this, direction[i]);
                    break;
                }
            }
        }
    }
}
