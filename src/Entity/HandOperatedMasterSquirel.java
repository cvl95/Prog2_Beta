package Entity;

import Core.EntityContext;
import Movement.XY;
import Util.ui.cosoleTest.console.Exceptions.SpawnException;

public class HandOperatedMasterSquirel extends MasterSquirel {
    XY movementDirection;
    private int miniSquirelSpawn = 0;

    public HandOperatedMasterSquirel(int energy, XY pos){
        super(1000,pos);
    }

    public void setMovementDirection(XY movementDirection) {
        this.movementDirection = movementDirection;
    }

    @Override
    public void nextStep(EntityContext context) {

        if(this.getStun() == 0){
            context.tryMove(this,movementDirection);
        }else {
            this.setStun(this.getStun()-1);
        }

    }

    public void setMiniSquirelSpawn() {
        this.miniSquirelSpawn = miniSquirelSpawn++;
    }

    public int getMiniSquirelSpawn() {
        return miniSquirelSpawn;
    }

    public void setSpawn(int energy, EntityContext context) throws SpawnException {
        if (energy < 0) {
            throw new SpawnException("Cannot spawn a mini-squirrel with negative energy.");
        }
        if (energy < MiniSquirel.MINIMUM_SPAWN_ENERGY) {
            throw new SpawnException("At least " +  MiniSquirel.MINIMUM_SPAWN_ENERGY + " points of energy should be given.");
        }
        if (energy > getEnergy()) {
            throw new SpawnException("Not enough energy to spawn a mini-squirrel. Required: " +  MiniSquirel.MINIMUM_SPAWN_ENERGY);
        }
        XY freePosition = context.getFreeSurrounding(getPosition());
        if (freePosition == null) {
            throw new SpawnException("No place to spawn.");
        }
        createMinisquirel(energy,freePosition);
    }

}
