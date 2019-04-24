package Entity;

import Core.EntityContext;
import Movement.XY;


import java.util.ArrayList;
import java.util.List;

public class MasterSquirel extends Squirel {

    int stun= 0;

    public MasterSquirel(int energy, XY pos){

        super(1000, pos);

    }
    //manage entry to entity set of minisquirel, also position
    public MiniSquirel createMinisquirel(int GivenEnergy, XY pos){
        MiniSquirel miniSquirel = new MiniSquirel(GivenEnergy, pos);
        this.updateEnergy(-GivenEnergy);
        miniSquirel.setReferenceFather(this.getId());
        return miniSquirel;
    }

    public void setStun(int stun) {
        this.stun = stun;
    }

    @Override
    public void nextStep(EntityContext context) {

        List surround = context.checkSuroundings(this);
        XY direction = null;
        for(Object entity: surround) {



            if (entity instanceof GoodBeast|| entity instanceof GoodPlant) {
                 int x = entity.getPosition().getX() - this.getPosition().getX();
                 int y = entity.getPosition().getY() - this.getPosition().getY();
                direction = this.culcRun(x,y);

            }
            if (entity instanceof BadBeast || entity instanceof BadPlant) {
                 int x = this.getPosition().getX() - entity.getPosition().getX();
                 int y = this.getPosition().getY() - entity.getPosition().getY();
                direction = this.culcRun(x,y);
            }

        }
        if (direction != null)
            context.tryMove(this,direction);


    }




}
