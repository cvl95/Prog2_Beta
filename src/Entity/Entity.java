package Entity;

import Movement.XY;

public class Entity {

    private int id;
    private int energy;
    private XY position;

    Entity(){}
    Entity(int energy, XY pos, int Id){
        this.energy = energy;
        this.position = pos;
        this.id = Id;
    }
    Entity(XY pos){
        this.position = pos;
    }

    public int getEnergy() {
        return energy;
    }

    public int getId() {
        return id;
    }

    public XY getPosition() {
        return position;
    }

    public void setPosition(XY position) {
        this.position = position;
    }

    public void updateEnergy(int Delta){
        this.energy += Delta;
    }

    public void setId(int id) {
        this.id = id;
    }


    public void nextStep(EntitySet entities){

    }

    @Override
    public String toString() {
       String s = String.format(("id: %s , energy: %s , position: %s"),this.id,this.energy,this.position);
        return s;
    }
}
