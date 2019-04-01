package Entity;

import Movement.XY;

import java.util.ArrayList;
import java.util.List;

public class MasterSquirel extends Entity {

    private List<MiniSquirel> miniSquirelList = new ArrayList<>();


    public MasterSquirel(){}
    public MasterSquirel(int id, XY pos){
        this.updateEnergy(1000);
        this.setPosition(pos);
        this.setId(id);
    }

    public MiniSquirel createMinisquirel(int GivenEnergy){
        MiniSquirel miniSquirel = new MiniSquirel();
        miniSquirel.updateEnergy(GivenEnergy);
        this.updateEnergy(-GivenEnergy);
        miniSquirel.setId(this.getId()*2); // need Id generation stuff
        miniSquirel.setPosition(new XY(0, -1));
        //Position of minisquirel how? why? when? diffrent Method??

       this.miniSquirelList.add(miniSquirel);
       return miniSquirel;
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
    public void nextStep(EntitySet entities) {
        this.getPosition().getNewPosition();
        resolveColission(entities);
    }

    private void resolveColission(EntitySet entities){
        for (int i = 0; i<entities.getLENTGH();i++) {
            Entity entity = entities.getEntitySet()[i];
            if(entity instanceof GoodPlant && this.getPosition() == entity.getPosition()){
                this.updateEnergy(entity.getEnergy());
                entities.deleteEntity(entity.getId());
                break;
            }

        }
    }
}
