package Entity;

import Movement.XY;

public class Wall extends Entity {
    public static int START_ENERGY = -10;


    public Wall(int energy, XY pos){
        super(energy,pos);
    }
    public Wall( XY pos){
        super(-10,pos);
    }

    @Override
    public EntityType getType() {
        return EntityType.WALL;
    }
}
