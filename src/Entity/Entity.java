package Entity;


import Core.EntityContext;
import Movement.XY;

import java.util.ArrayList;
import java.util.List;

public abstract class Entity implements Comparable<Entity>{

    private final int id;
    private int energy;
    private XY position;
    private static List idList = new ArrayList<Integer>();

    Entity(){
        this.id =generateRandomId();
    }
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

    public void setEnergy(int energy) {
        this.energy = energy;
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
    public void nextStep(EntityContext context){

    }
    public abstract EntityType getType();

    public static List getIdList() {
        return idList;
    }

    @Override
    public String toString() {
       String s = String.format(("%s id: %s , energy: %s , position: %s"), getClass().getSimpleName(), this.id,this.energy,this.position);
        return s;
    }
    @Override
    public int compareTo(Entity other) {
        if (Entity.class.getSimpleName().equals(other.getClass().getSimpleName())) {
            return this.getId() - other.getId();
        }
        return other.getId();
    }
}
