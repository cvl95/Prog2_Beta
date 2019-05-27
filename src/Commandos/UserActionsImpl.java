package Commandos;

import Console.ConsoleUtils;
import Core.EntityContext;
import Entity.HandOperatedMasterSquirel;
import Movement.XY;

import java.util.List;

public class UserActionsImpl implements UserActions {
    private HandOperatedMasterSquirel player;
    private EntityContext entityContext;
    private List<ConsoleCommandType> commandTypes;
    private boolean turnFinished;

    public UserActionsImpl(HandOperatedMasterSquirel player, EntityContext context){
        this.player = player;
        this.entityContext = context;
        this.commandTypes = ConsoleCommandType.getCommandTypes(this,UserActions.class);
    }

    public void setTurnFinished(boolean turnFinished) {
        this.turnFinished = turnFinished;
    }

    @Override
    public void help() {
        System.out.println(ConsoleUtils.getFormattedHelp(commandTypes));
        turnFinished = false;
    }

    @Override
    public void exit() {
        System.exit(0);
    }

    @Override
    public void pass() {
        player.setMovementDirection(XY.NONE);
        turnFinished = true;
    }

    @Override
    public void moveUp() {
        player.setMovementDirection(XY.UP);
        turnFinished = true;
    }

    @Override
    public void moveDown() {
        player.setMovementDirection(XY.DOWN);
        turnFinished = true;
    }

    @Override
    public void moveLeft() {
        player.setMovementDirection(XY.LEFT);
        turnFinished = true;
    }

    @Override
    public void moveRight() {
        player.setMovementDirection(XY.RIGHT);
        turnFinished = true;
    }

    @Override
    public void moveLeftUp() {
        player.setMovementDirection(XY.LEFT_UP);
        turnFinished = true;
    }

    @Override
    public void moveRightUp() {
        player.setMovementDirection(XY.RIGHT_UP);
        turnFinished = true;
    }

    @Override
    public void moveLeftDown() {
        player.setMovementDirection(XY.LEFT_DOWN);
        turnFinished = true;
    }

    @Override
    public void moveRightDown() {
        player.setMovementDirection(XY.RIGHT_DOWN);
        turnFinished = true;
    }

    @Override
    public void showEnergy() {
        System.out.println("Player energy: " +player.getEnergy());
        turnFinished = false;
    }
    
    @Override
    public void spawnMiniSquirrel(int energy) {
        player.setSpawn(energy, entityContext);
        turnFinished = true;

    }
    public boolean isTurnFinished(){
        return turnFinished;
    }
}
