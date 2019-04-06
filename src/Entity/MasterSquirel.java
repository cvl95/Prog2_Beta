package Entity;

import Movement.XY;

import java.util.ArrayList;
import java.util.List;

public class MasterSquirel extends Entity {

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
        this.setPosition(this.getPosition().getNewPosition());
        resolveColission(entities);
    }

    public void resolveColission(EntitySet entities){
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
