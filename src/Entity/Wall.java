package Entity;

import Movement.XY;

public class Wall extends Entity {

   public Wall(int energy, XY pos){
        super(energy,pos);
    }
    public Wall( XY pos){
        super(-10,pos);
    }
}
