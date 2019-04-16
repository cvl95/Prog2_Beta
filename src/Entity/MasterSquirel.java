package Entity;

import Core.EntityContext;
import Movement.XY;
import com.sun.java.util.jar.pack.Instruction;

import java.util.ArrayList;
import java.util.List;

public class MasterSquirel extends Squirel {

    int stun= 0;
    private List<MiniSquirel> miniSquirelList = new ArrayList<>();

    public MasterSquirel(int energy, XY pos){

        super(1000, pos);

    }

    public MiniSquirel createMinisquirel(int GivenEnergy, XY pos){
        MiniSquirel miniSquirel = new MiniSquirel(GivenEnergy, pos);
        this.updateEnergy(-GivenEnergy);
        this.miniSquirelList.add(miniSquirel);
        return miniSquirel;
    }

    public void setStun(int stun) {
        this.stun = stun;
    }

    public List<MiniSquirel> getMiniSquirelList() {
        return miniSquirelList;
    }

    // how to move to minisquirel
    public boolean checkOrigin(MiniSquirel miniSquirel){

        if(this.miniSquirelList.contains(miniSquirel)){
            System.out.println("this squirel is a child of this squirel");
            return true;
        }
        return false;

    }

    @Override
    public void nextStep(EntityContext context) {
        if (stun>0)
            stun--;
        else {
            XY newPos = this.getPosition().getNewPosition();
            Entity fighter = entities.findEntity(newPos);
                if (fighter instanceof Wall) {
                    stun = 3;
                    this.updateEnergy(fighter.getEnergy());
                }
                if (fighter instanceof BadPlant){
                    this.setPosition(newPos);
                    updateEnergy(fighter.getEnergy());
                    entities.deleteEntity(fighter);
                    entities.addEntity();
                }
                        }
    }




}
