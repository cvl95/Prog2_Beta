package Entity;

import Movement.XY;

public class Wall extends Entity {

   public Wall(int id, XY pos){
        this.updateEnergy(-10);
        this.setPosition(pos);
        this.setId(id);
    }
    public Wall( XY pos){
        this.updateEnergy(-10);
        this.setPosition(pos);
    }
}
