package Entity;

import Core.EntityContext;
import Entity.bots.MiniSquirelBot;
import Movement.XY;
import Util.ui.cosoleTest.console.Exceptions.SpawnException;

public class HandOperatedMasterSquirel extends MasterSquirel {
    XY movementDirection;
    private int miniSquirelSpawn = 0;
    MiniSquirel miniSquirel;

    public HandOperatedMasterSquirel(int energy, XY pos){
        super(1000,pos);
    }

    public void setMovementDirection(XY movementDirection) {
        this.movementDirection = movementDirection;
    }

    @Override
    public void nextStep(EntityContext context) {
        if (miniSquirelSpawn != 0){
            miniSquirelSpawn--;
            context.spawnMini(miniSquirel);
        }

        if(this.getStun() == 0){
            if(movementDirection == null){
                return;
            }else {
                context.tryMove(this,movementDirection);
            }
        }else {
            this.setStun(this.getStun()-1);
        }

    }
    @Override
    public EntityType getType() {
        return EntityType.MASTER_SQUIRREL;
    }

    public void setMiniSquirelSpawn() {
        this.miniSquirelSpawn = miniSquirelSpawn++;
    }

    public int getMiniSquirelSpawn() {
        return miniSquirelSpawn;
    }

    public void setSpawn(int energy, EntityContext context) throws SpawnException {
        XY freePosition = context.getFreeSurrounding(getPosition());

        if (freePosition == null) {
            throw new SpawnException("No place to spawn.");
        }
        if (energy < 0) {
            throw new SpawnException("Cannot spawn a mini-squirrel with negative energy.");
        }
        if (energy < MiniSquirel.MINIMUM_SPAWN_ENERGY && MiniSquirel.MINIMUM_SPAWN_ENERGY > getEnergy()){
            this.miniSquirelSpawn = 1;
             this.miniSquirel  = (MiniSquirelBot) createMinisquirel(MiniSquirel.MINIMUM_SPAWN_ENERGY, freePosition);
        }
        if (energy > getEnergy()) {
            throw new SpawnException("Not enough energy to spawn a mini-squirrel. Required: " +  MiniSquirel.MINIMUM_SPAWN_ENERGY);
        }

        miniSquirel = createMinisquirel(energy,freePosition);
    }

}
