package Entity;

import Movement.XY;

public class GoodPlant extends Plant {
    public static int START_ENERGY = 100;


    public GoodPlant(int energy, XY pos) {
        super(100, pos);
    }

    @Override
    public EntityType getType() {
        return EntityType.GOOD_PLANT;
    }

}
