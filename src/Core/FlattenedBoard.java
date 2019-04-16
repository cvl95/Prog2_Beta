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
        EntitySet entities = board.getEntitySet();
        for (int i = 0; i < entities.getLENTGH(); i++) {
            Entity entity = entities.getEntitySet()[i];
            gameField[entity.getPosition().getX()][entity.getPosition().getY()] = entity;
        }
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < size.getX(); i++) {
            for (int j = 0; j < size.getY(); j++) {
                switch (getEntityType(new XY(i, j))) {
                    case MASTER_SQUIRREL:
                        builder.append('S');
                        break;
                    case MINI_SQUIRREL:
                        builder.append('s');
                        break;
                    case GOOD_BEAST:
                        builder.append('G');
                        break;
                    case BAD_BEAST:
                        builder.append('B');
                        break;
                    case GOOD_PLANT:
                        builder.append('+');
                        break;
                    case BAD_PLANT:
                        builder.append('-');
                        break;
                    case WALL:
                        builder.append('#');
                        break;
                    case NONE:
                    default:
                        builder.append(' ');
                        break;
                }
                if (j < size.getY() - 1) {
                    builder.append(' ');
                }
            }
            builder.append('\n');
        }
        return builder.toString();
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
                if(((MasterSquirel) entityAtMoveDirection).checkOrigin(miniSquirel)){
                    ((MasterSquirel) entityAtMoveDirection).getMiniSquirelList().remove(miniSquirel);
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
        if(entity instanceof MiniSquirel){
            ((MiniSquirel) entity).getSuperMiniSquirelList().remove(entity);
        }else{
            board.entitySet.deleteEntity(entity.getId());
        }
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

    public List checkSuroundings(Entity entity) {
        List<Entity> surroundingEntities = new ArrayList<Entity>();
        for (int i = -6; i < 7; i++) {
            for (int z = -6; z < 7; z++) {
                if (new XY(entity.getPosition().getX() + i, entity.getPosition().getY() + z) == null) {
                    continue;
                } else {
                    surroundingEntities.add(board.entitySet.findEntity(new XY(entity.getPosition().getX() + i, entity.getPosition().getY() + z)));
                }
            }

        }
        return surroundingEntities;
    }

    public Entity[][] getGameField() {
        return gameField;
    }

    public void callNextStep() {
        for (int i = 0; i < this.board.entitySet.getLENTGH(); i++) {
            if(this.board.getEntitySet().getEntitySet()[i] instanceof Charachter ){
                this.board.getEntitySet().getEntitySet()[i].nextStep(this);
            }

        }
    }

}