package Entity;

import Movement.XY;

public class Plant extends Entity {
    Plant(int energy, XY pos){
        super(energy,pos);
    }
    @Override
    public EntityType getType() {
        return EntityType.GOOD_PLANT;
    }
}
