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

    }
}