package Entity;

import GameEngine.Collider;
import Movement.XY;

import java.util.ArrayList;
import java.util.List;

public class Entity {

    private final int id;
    private int energy;
    private XY position;
    private static List idList = new ArrayList<Integer>();
    private Collider colider;


    Entity(int energy, XY pos, int Id){
        this.energy = energy;
        this.position = pos;
        this.id = Id;
    }

    Entity(int energy, XY pos){
        this.energy = energy;
        this.position = pos;
        this.id = assignID();

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

    public int generateRandomId(){
        int id = (int) (System.currentTimeMillis() & 0xfffffff);
        return id;
    }

    public boolean checkLst(int id){
        return idList.contains(id);
    }

    public int assignID() {
        int iD = generateRandomId();
        while(checkLst(iD) == true)
            iD = generateRandomId();
        idList.add(iD);
        return iD;
    }
    public void nextStep(EntitySet entities){

    }


    @Override
    public String toString() {
       String s = String.format(("id: %s , energy: %s , position: %s"),this.id,this.energy,this.position);
        return s;
    }
}
