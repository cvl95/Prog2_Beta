package Entity;

import Movement.XY;

public class BadPlant extends Plant {
    public static int START_ENERGY = -100;


    public BadPlant(int energy, XY pos) {
       super(-100, pos);
   }

    @Override
    public EntityType getType() {
        return EntityType.BAD_PLANT;
    }
}

