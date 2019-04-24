package Entity;

import Core.EntityContext;
import Movement.XY;

import java.util.List;

public class MiniSquirel extends MasterSquirel {
    private int referenceFather;

    public MiniSquirel(int energy, XY pos) {
        super(energy, pos);
    }

    public void setReferenceFather(int referenceFather) {
        this.referenceFather = referenceFather;
    }

    public int getReferenceFather() {
        return referenceFather;
    }

    @Override
    public void nextStep(EntityContext context) {

        List surround = context.checkSuroundings(this);
        XY direction = null;
        for (Object entity : surround) {
            
            if (entity instanceof GoodBeast || entity instanceof GoodPlant) {
                int x = entity.getPosition().getX() - this.getPosition().getX();
                int y = entity.getPosition().getY() - this.getPosition().getY();
                direction = this.culcRun(x, y);

            }
            if (entity instanceof BadBeast || entity instanceof BadPlant) {
                int x = this.getPosition().getX() - entity.getPosition().getX();
                int y = this.getPosition().getY() - entity.getPosition().getY();
                direction = this.culcRun(x, y);
            }

        }
        if (direction != null)
            context.tryMove(this, direction);


    }
}