package Core;
import Entity.*;
import Movement.XY;

import java.util.logging.Logger;

public class FlattenedBoard implements Boardview, EntityContext {
    private final Board board;
    private final Entity[][] gameField;
    private final XY size;
    private final Logger logger = Logger.getLogger(FlattenedBoard.class.getName());


    public FlattenedBoard(Board board) {

        this.board = board;
        this.size = new XY(board.getSize().getX(),board.getSize().getY());
        this.gameField = new Entity[size.getX()][size.getY()];

        EntitySet entitySet = board.getEntitySet();
        for (int i = 0; i < entitySet.getSizeOfArray(); i++) {
            Entity entity = entitySet.get(i);
            if(entity == null){
                continue;
            }else{
                setPlace(entity.getPosition(),entity);
            }

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
        XY newPosition = masterSquirel.getPosition().setNewVectorPosition(moveDirection);
        Entity entityAtMoveDirection = getFieldValue(newPosition);

        switch (getEntityType(newPosition)){
            case MASTER_SQUIRREL:
                logger.info(masterSquirel +  " collided with " + entityAtMoveDirection);
                break;
            case MINI_SQUIRREL:
                logger.info(entityAtMoveDirection + " was consumed by " + masterSquirel);
                masterSquirel.updateEnergy(entityAtMoveDirection.getEnergy());
                logger.info(masterSquirel + " has new energy " + masterSquirel.getEnergy());
                kill(entityAtMoveDirection);
                move(newPosition,masterSquirel);
                break;
            case GOOD_PLANT:
                masterSquirel.updateEnergy(entityAtMoveDirection.getEnergy());
                logger.info(entityAtMoveDirection + " was consumed by " + masterSquirel);
                logger.info(masterSquirel + " has new energy " + masterSquirel.getEnergy());
                killAndReplace(entityAtMoveDirection);
                move(newPosition,masterSquirel);
                break;
            case GOOD_BEAST:
                masterSquirel.updateEnergy(entityAtMoveDirection.getEnergy());
                logger.info(entityAtMoveDirection + " was consumed by " + masterSquirel);
                logger.info(masterSquirel + " has new energy " + masterSquirel.getEnergy());
                killAndReplace(entityAtMoveDirection);
                move(newPosition,masterSquirel);
                break;
            case BAD_PLANT:
                masterSquirel.updateEnergy(entityAtMoveDirection.getEnergy());
                killAndReplace(entityAtMoveDirection);
                logger.info(entityAtMoveDirection + " was consumed by " + masterSquirel);
                logger.info(masterSquirel + " has new energy " + masterSquirel.getEnergy());
                move(newPosition,masterSquirel);
                if(masterSquirel.getEnergy() <= 0){
                    kill(masterSquirel);
                    logger.info(masterSquirel + " has died");
                    System.exit(1);
                }
                break;
            case BAD_BEAST:
                ((BadBeast) entityAtMoveDirection).setSnack();
                logger.info(entityAtMoveDirection + " took a bite of " + masterSquirel);
                if(((BadBeast) entityAtMoveDirection).getSNACK() ==0 ) {
                    killAndReplace(entityAtMoveDirection);
                    move(newPosition,masterSquirel);
                }
                masterSquirel.updateEnergy(entityAtMoveDirection.getEnergy());
                if(masterSquirel.getEnergy() <= 0){
                    kill(masterSquirel);
                    System.exit(1);
                }
                break;
            case WALL:
                masterSquirel.updateEnergy(entityAtMoveDirection.getEnergy());                logger.info(masterSquirel +  " collided with " + entityAtMoveDirection);
                logger.info(masterSquirel +  " collided with " + entityAtMoveDirection);
                masterSquirel.setStun(3);
                break;
            case NONE:
                move(newPosition,masterSquirel);
                break;
            default:
                break;
        }
    }

    @Override
    public void tryMove(MiniSquirel miniSquirel, XY moveDirection) {
        miniSquirel.setEnergy(miniSquirel.getEnergy() - 1);
        if (miniSquirel.getEnergy() <= 0) {
            kill(miniSquirel);
        }
        XY newPosition = miniSquirel.getPosition().setNewVectorPosition(moveDirection);
        Entity entityAtMoveDirection = getFieldValue(newPosition);

        switch (getEntityType(newPosition)) {
            case MASTER_SQUIRREL:
                if (((MasterSquirel) entityAtMoveDirection).getId() == miniSquirel.getReferenceFather()) {
                    entityAtMoveDirection.updateEnergy(miniSquirel.getEnergy());
                    logger.info(miniSquirel +  " was eaten " + entityAtMoveDirection);
                    kill(miniSquirel);
                }
                break;
            case MINI_SQUIRREL:
                logger.info(miniSquirel +  " collided with " + entityAtMoveDirection);
                break;
            case GOOD_PLANT:
                break;
            case GOOD_BEAST:
                miniSquirel.updateEnergy(entityAtMoveDirection.getEnergy());
                logger.info(miniSquirel +  " collided with " + entityAtMoveDirection);
                killAndReplace(entityAtMoveDirection);
                move(newPosition,miniSquirel);
                break;
            case BAD_PLANT:
                miniSquirel.updateEnergy(entityAtMoveDirection.getEnergy());
                logger.info(miniSquirel +  " collided with " + entityAtMoveDirection);
                killAndReplace(entityAtMoveDirection);
                if (miniSquirel.getEnergy() <= 0) {
                    kill(miniSquirel);
                } else {
                    move(newPosition,miniSquirel);
                }
                break;
            case BAD_BEAST:
                ((BadBeast) entityAtMoveDirection).setSnack();
                miniSquirel.updateEnergy(entityAtMoveDirection.getEnergy());
                if (miniSquirel.getEnergy() <= 0) {
                    logger.info(miniSquirel +  " died");
                    kill(miniSquirel);
                } else if (((BadBeast) entityAtMoveDirection).getSNACK() == 0) {
                    logger.info(miniSquirel +  " collided with " + entityAtMoveDirection);

                    killAndReplace(entityAtMoveDirection);
                    move(newPosition,miniSquirel);
                }
                break;
            case WALL:
                miniSquirel.updateEnergy(entityAtMoveDirection.getEnergy());
                logger.info(miniSquirel +  " collided with " + entityAtMoveDirection);
                miniSquirel.setStun(3);
                break;
            case NONE:
                move(newPosition,miniSquirel);
                break;
            default:
                break;
        }
    }

    @Override
    public void tryMove(GoodBeast goodBeast, XY moveDirection) {
        XY newPosition = goodBeast.getPosition().setNewVectorPosition(moveDirection);
        Entity entityAtMoveDirection =getFieldValue(newPosition);
        switch (getEntityType(newPosition)){
            case MASTER_SQUIRREL:
            case MINI_SQUIRREL:
            case GOOD_PLANT:
            case GOOD_BEAST:
            case BAD_PLANT:
            case BAD_BEAST:
                break;
            case WALL:
            case NONE:
                move(newPosition,goodBeast);
                break;
            default:
                break;

        }
    }

    @Override
    public void tryMove(BadBeast badBeast, XY moveDirection) {
        XY newPosition = badBeast.getPosition().setNewVectorPosition(moveDirection);
        Entity entityAtMoveDirection = getFieldValue(newPosition);
        switch (getEntityType(newPosition)){
            case MASTER_SQUIRREL:
                if(badBeast.getSNACK() > 0){
                    badBeast.setSnack();
                    entityAtMoveDirection.updateEnergy(badBeast.getEnergy());
                }else{
                    killAndReplace(badBeast);
                }
                if(entityAtMoveDirection.getEnergy() <= 0){
                    kill(entityAtMoveDirection);
                    move(newPosition,badBeast);
                }
                break;
            case MINI_SQUIRREL:
                if(badBeast.getSNACK() > 0){
                    badBeast.setSnack();
                    entityAtMoveDirection.updateEnergy(badBeast.getEnergy());
                }else{
                    killAndReplace(badBeast);
                }
                if(entityAtMoveDirection.getEnergy() <= 0){
                    kill(entityAtMoveDirection);
                    move(newPosition,badBeast);
                }
                break;
            case GOOD_PLANT:
            case GOOD_BEAST:
            case BAD_PLANT:
            case BAD_BEAST:
            case WALL:
            case NONE:
                move(newPosition,badBeast);
                break;
            default:
                break;

        }
    }

    @Override
    public PlayerEntity nearestPlayerEntity(XY position) {
        PlayerEntity nearestPlayerEntity = null;
        int minDistance = Integer.MAX_VALUE;
        for (int i = 0; i < board.getEntitySet().getSizeOfArray(); i++) {
            Entity entity = board.getEntitySet().get(i);
            if (!(entity instanceof PlayerEntity)) {
                continue;
            }
            int distance = calculateDistance(position, entity.getPosition());
            if (distance < minDistance) {
                minDistance = distance;
                nearestPlayerEntity = (PlayerEntity) entity;
                break;
            }
        }
        return nearestPlayerEntity;
    }

    @Override
    public void kill(Entity entity) {
            board.getEntitySet().deleteEntity(entity.getId());
            setPlace(entity.getPosition(),null);
            logger.info(entity + " was removed.");
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
        board.getEntitySet().addEntity(newEntity);
        setPlace(newEntity.getPosition(),newEntity);
        logger.info(newEntity + " newly added");

    }


    @Override
    public int calculateDistance(XY position1, XY position2) {

        return Math.max(Math.abs(position1.getX() - position2.getX()),
                Math.abs(position1.getY() - position2.getY()));
    }

    @Override
    public boolean testArrayBounds(XY cell) {
        if ((cell.getX() >= 0 && cell.getX() < size.getX())
                && (cell.getY() >= 0 && cell.getY() < size.getY())) {
            return true;
        }
        return false;
    }


    @Override
    public boolean isOccupied(XY cell) {
        if(gameField[cell.getX()][cell.getY()] != null){
            return true;
        }
        return false ;
    }


    public Entity[] checkSuroundings(Entity entity) {
        int counter = 0;
        int entity_X = entity.getPosition().getX();
        int entity_Y = entity.getPosition().getY();
        Entity[] surroundingEntities = new Entity[100];
        for (int i = -6; i < 7; i++) {
            for (int z = -6; z < 7; z++) {
                if((entity_Y+z < getSize().getY() && entity_Y >= 0) && (entity_X+i <getSize().getX() && entity_X >= 0)){
                    if (gameField[entity_Y+z][entity_X+i] == null) {
                        continue;
                    } else {
                        surroundingEntities[counter] = gameField[entity_Y+z][entity_X+i];
                        counter++;
                    }
                }

            }

        }
        return surroundingEntities;
    }

    private void setPlace(XY position, Entity entity){
        gameField[position.getX()][position.getY()] = entity;
        logger.info("Position is set for " + entity);
    }
    private Entity getFieldValue(XY position){
        return gameField[position.getX()][position.getY()];
    }
    private void move(XY position,Entity entity){
        setPlace(entity.getPosition(),null);
        entity.setPosition(position);
        setPlace(entity.getPosition(),entity);
        logger.info(entity + " moved to new postion: " + position);
    }

    public Entity[][] getGameField() {
        return gameField;
    }

    public Board getBoard() {
        return board;
    }
    public XY getFreeSurrounding(XY postion){
        for(int i = -1; i < 3; i++){
            for(int j = -1; j < 3; j++){
                XY xy = new XY(postion.getY()+i,postion.getX() + j);
                if(!isOccupied(xy)){
                    return xy;
                }
            }
        }
        return null;
    }

    @Override
    public Entity getEntityAt(XY position) {
        return gameField[position.getY()][ position.getX()];
    }

    @Override
    public Entity getEntityByID(int id) {
        return this.board.getEntitySet().findEntity(id);
    }

    private String printHelper(Entity entity){
        if(entity instanceof BadBeast){
            return " B";
        }else if(entity instanceof GoodBeast){
            return " G";
        }else if(entity instanceof GoodPlant){
            return " g";
        }else if(entity instanceof BadPlant){
            return " b";
        }else if(entity instanceof Wall){
            return " +";
        }else if(entity instanceof MasterSquirel){
            return " M";
        }else{
            return " -";
        }

    }

}