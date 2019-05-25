package Entity;

import Core.EntityContext;
import Movement.XY;


public class MasterSquirel extends Squirel {

    int stun= 0;

    public MasterSquirel(int energy, XY pos){

        super(1000, pos);

    }
    public MasterSquirel(){}

    public MiniSquirel createMinisquirel(int GivenEnergy, XY pos){
        MiniSquirel miniSquirel = new MiniSquirel(GivenEnergy, pos);
        this.updateEnergy(-GivenEnergy);
        miniSquirel.setReferenceFather(this.getId());
        return miniSquirel;
    }

    public Boolean ownsMini(MiniSquirel miniSquirel){
        return miniSquirel.getReferenceFather() == getId();
    }

    public void setStun(int stun) {
        this.stun = stun;
    }

    public int getStun() {
        return stun;
    }

    @Override
    public void nextStep(EntityContext context) {

        Entity[] surround = context.checkSuroundings(this);
        XY direction = null;
        for(Entity entity: surround) {



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
