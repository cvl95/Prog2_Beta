package Entity;

import Movement.XY;

public class BadPlant extends Entity {

    public BadPlant(int id,XY pos){
        this.updateEnergy(-100);
        this.setPosition(pos);
        this.setId(id);
    }
}
