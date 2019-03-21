package Entity;

import Movement.XY;

public class GoodPlant extends Entity {
    public GoodPlant(int id,XY pos){
        this.updateEnergy(100);
        this.setPosition(pos);
        this.setId(id);
    }
}
