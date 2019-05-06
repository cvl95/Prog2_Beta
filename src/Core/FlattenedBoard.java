package Core;
import Entity.*;
import Movement.XY;

import java.util.ArrayList;
import java.util.List;

public class FlattenedBoard implements Boardview, EntityContext {
    private final Board board;
    private final Entity[][] gameField;
    private final XY size;

    public FlattenedBoard(Board board) {
        this.board = board;
        size = board.getSize();
        gameField = new Entity[size.getX()][size.getY()];
        for (int i = 0; i < board.getEntitySet().getLENTGH(); i++) {
            gameField[board.getEntitySet().getEntitySet()[i].getPosition().getX()][board.getEntitySet().getEntitySet()[i].getPosition().getY()]
                      = board.getEntitySet().getEntitySet()[i];
        }
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();

        for(int i = 0; i <= this.gameField.length-1; i++){
            for(int j = 0; j<=this.gameField.length-1; j++){
                stringBuilder.append(printHelper(this.gameField[i][j])+" ");
            }stringBuilder.append("\n");
        }
        return stringBuilder.toString();
    }

    @Override
    public XY getSize() {
        return size;
    }

    @Override
    public EntityType getEntityType(XY position) {
        if ((position.getX() < 0 || position.getX() >= size.getX())
                || (position.getY() < 0 || position.getX() >= size.getY())) {
            return EntityType.NONE;
        }
        Entity entity = gameField[position.getX()][position.getY()];
        if (entity instanceof MasterSquirel) {
            return EntityType.MASTER_SQUIRREL;
        }
        else if (entity instanceof MiniSquirel) {
            return EntityType.MINI_SQUIRREL;
        }
        else if (entity instanceof GoodBeast) {
            return EntityType.GOOD_BEAST;
        }
        else if (entity instanceof BadBeast) {
            return EntityType.BAD_BEAST;
        }
        else if (entity instanceof GoodPlant) {
            return EntityType.GOOD_PLANT;
        }
        else if (entity instanceof BadPlant) {
            return EntityType.BAD_PLANT;
        }
        else if (entity instanceof Wall) {
            return EntityType.WALL;
        }
        return EntityType.NONE;
    }

    @Override
    public void tryMove(MasterSquirel masterSquirel, XY moveDirection) {
        Entity entityAtMoveDirection = board.getEntitySet().findEntity(moveDirection);
        if(entityAtMoveDirection == null){
            masterSquirel.setPosition(masterSquirel.getPosition().setNewPosition(moveDirection));
        }else if(entityAtMoveDirection instanceof Wall){
            masterSquirel.updateEnergy(entityAtMoveDirection.getEnergy());
            masterSquirel.setStun(3);
        }else if(entityAtMoveDirection instanceof Plant){
            masterSquirel.updateEnergy(entityAtMoveDirection.getEnergy());
            killAndReplace(entityAtMoveDirection);
            masterSquirel.setPosition(masterSquirel.getPosition().setNewPosition(moveDirection));

        }else if(entityAtMoveDirection instanceof GoodBeast){
            masterSquirel.updateEnergy(entityAtMoveDirection.getEnergy());
            killAndReplace(entityAtMoveDirection);
            masterSquirel.setPosition(masterSquirel.getPosition().setNewPosition(moveDirection));

        }else if(entityAtMoveDirection instanceof BadBeast){
            masterSquirel.updateEnergy(entityAtMoveDirection.getEnergy());
            if(((BadBeast) entityAtMoveDirection).getSnack() == 0){
                killAndReplace(entityAtMoveDirection);
                masterSquirel.setPosition(masterSquirel.getPosition().setNewPosition(moveDirection));
            }else{
                ((BadBeast) entityAtMoveDirection).setSnack();
            }
        }else if(entityAtMoveDirection instanceof MiniSquirel){
            masterSquirel.updateEnergy(entityAtMoveDirection.getEnergy());
            kill(entityAtMoveDirection);
            masterSquirel.setPosition(masterSquirel.getPosition().setNewPosition(moveDirection));

        }else if(entityAtMoveDirection instanceof MasterSquirel){

        }
    }

    @Override
    public void tryMove(MiniSquirel miniSquirel, XY moveDirection) {
        miniSquirel.setEnergy(miniSquirel.getEnergy()-1);
        Entity entityAtMoveDirection = board.getEntitySet().findEntity(moveDirection);
        miniSquirel.updateEnergy(entityAtMoveDirection.getEnergy());

        killAndReplace(entityAtMoveDirection);
        miniSquirel.setPosition(miniSquirel.getPosition().setNewPosition(moveDirection));

        if(miniSquirel.getEnergy()<=0){
            kill(miniSquirel);
        }else {
            if (entityAtMoveDirection == null) {
                miniSquirel.setPosition(miniSquirel.getPosition().setNewPosition(moveDirection));
            } else if (entityAtMoveDirection instanceof Wall) {
                miniSquirel.setStun(3);
            } else if (entityAtMoveDirection instanceof Plant) {
                killAndReplace(entityAtMoveDirection);
                miniSquirel.setPosition(miniSquirel.getPosition().setNewPosition(moveDirection));

            } else if (entityAtMoveDirection instanceof GoodBeast) {
                killAndReplace(entityAtMoveDirection);
                miniSquirel.setPosition(miniSquirel.getPosition().setNewPosition(moveDirection));

            } else if (entityAtMoveDirection instanceof BadBeast) {
                if (((BadBeast) entityAtMoveDirection).getSnack() == 0) {
                    killAndReplace(entityAtMoveDirection);
                    miniSquirel.setPosition(miniSquirel.getPosition().setNewPosition(moveDirection));
                } else {
                    ((BadBeast) entityAtMoveDirection).setSnack();
                }
            } else if (entityAtMoveDirection instanceof MiniSquirel) {
            }else if (entityAtMoveDirection instanceof MasterSquirel){
                entityAtMoveDirection.updateEnergy(miniSquirel.getEnergy());
                if(((MasterSquirel) entityAtMoveDirection).getId() == miniSquirel.getReferenceFather()){
                    entityAtMoveDirection.updateEnergy(miniSquirel.getEnergy());
                    kill(miniSquirel);

                }
            }

        }
    }

    @Override
    public void tryMove(GoodBeast goodBeast, XY moveDirection) {
        Entity entityAtMoveDirection = board.getEntitySet().findEntity(moveDirection);
        if(entityAtMoveDirection == null) {
            goodBeast.setPosition(goodBeast.getPosition().setNewPosition(moveDirection));
        }
    }

    @Override
    public void tryMove(BadBeast badBeast, XY moveDirection) {
        Entity entityAtMoveDirection = board.getEntitySet().findEntity(moveDirection);
            if (entityAtMoveDirection == null) {
                badBeast.setPosition(badBeast.getPosition().setNewPosition(moveDirection));
            } else if (entityAtMoveDirection instanceof Wall) {

            } else if (entityAtMoveDirection instanceof Plant) {

            } else if (entityAtMoveDirection instanceof GoodBeast) {

            } else if (entityAtMoveDirection instanceof BadBeast) {

            } else if (entityAtMoveDirection instanceof Squirel) {
                if(badBeast.getSnack() > 0){
                    badBeast.setSnack();
                    entityAtMoveDirection.updateEnergy(badBeast.getEnergy());
                }else{
                    killAndReplace(badBeast);
                }
                if(entityAtMoveDirection.getEnergy() <= 0){
                    kill(entityAtMoveDirection);
                    badBeast.setPosition(badBeast.getPosition().setNewPosition(moveDirection));
                }
            }
        }

    @Override
    public PlayerEntity nearestPlayerEntity(XY position) {
        return null;
    }

    @Override
    public void kill(Entity entity) {
            board.entitySet.deleteEntity(entity.getId());
    }

    @Override
    public void killAndReplace(Entity entity) {
        Entity newEntity = null;
        XY newXY = board.calculateRandomPosition();
        newXY = board.findFreePlace(newXY);
        if(entity instanceof GoodPlant){
            newEntity = new GoodPlant(100, newXY);
        }else if(entity instanceof BadPlant) {
            newEntity = new BadPlant(-100,newXY);
        }else if(entity instanceof GoodBeast) {
            newEntity = new GoodBeast(200,newXY);
        }else if(entity instanceof BadBeast) {
            newEntity = new BadBeast(-150,newXY);
        }
        kill(entity);
        board.entitySet.addEntity(newEntity);
    }

    public Entity[] checkSuroundings(Entity entity) {
        int counter = 0;
        Entity[] surroundingEntities = new Entity[49];
        for (int i = -6; i < 7; i++) {
            for (int z = -6; z < 7; z++) {
                if (new XY(entity.getPosition().getX() + i, entity.getPosition().getY() + z) == null) {
                    continue;
                } else {
                    surroundingEntities[counter] = board.entitySet.findEntity(new XY(entity.getPosition().getX() + i, entity.getPosition().getY() + z));
                    counter++;
                }
            }

        }
        return surroundingEntities;
    }

    public Entity[][] getGameField() {
        return gameField;
    }

    public Board getBoard() {
        return board;
    }

    private String printHelper(Entity entity){
        if(entity instanceof BadBeast){
            return " BB";
        }else if(entity instanceof GoodBeast){
            return " GB";
        }else if(entity instanceof GoodPlant){
            return " GP";
        }else if(entity instanceof BadPlant){
            return " BP";
        }else if(entity instanceof Wall){
            return " +";
        }else if(entity instanceof MasterSquirel){
            return " M";
        }else{
            return " -";
        }

    }
}