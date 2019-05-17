package Entity;

import Core.EntityContext;
import Movement.XY;

public class MiniSquirel extends MasterSquirel {
    private int referenceFather;
    static final int MINIMUM_SPAWN_ENERGY = 100;
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

    }
}