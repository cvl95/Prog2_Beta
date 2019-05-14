package Core;
import Entity.*;
import Movement.XY;

public class FlattenedBoard implements Boardview, EntityContext {
    private final Board board;
    private final Entity[][] gameField;
    private final XY size;

    public FlattenedBoard(Board board) {
        this.board = board;
        this.size = board.getSize();
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
                break;
            case MINI_SQUIRREL:
                masterSquirel.updateEnergy(entityAtMoveDirection.getEnergy());
                kill(entityAtMoveDirection);
                move(newPosition,masterSquirel);
                break;
            case GOOD_PLANT:
                masterSquirel.updateEnergy(entityAtMoveDirection.getEnergy());
                killAndReplace(entityAtMoveDirection);
                move(newPosition,masterSquirel);
                break;
            case GOOD_BEAST:
                masterSquirel.updateEnergy(entityAtMoveDirection.getEnergy());
                killAndReplace(entityAtMoveDirection);
                move(newPosition,masterSquirel);
                break;
            case BAD_PLANT:
                masterSquirel.updateEnergy(entityAtMoveDirection.getEnergy());
                killAndReplace(entityAtMoveDirection);
                move(newPosition,masterSquirel);
                if(masterSquirel.getEnergy() <= 0){
                    kill(masterSquirel);
                    System.exit(1);
                }
                break;
            case BAD_BEAST:
                ((BadBeast) entityAtMoveDirection).setSnack();
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
                masterSquirel.updateEnergy(entityAtMoveDirection.getEnergy());
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
                    kill(miniSquirel);
                }
                break;
            case MINI_SQUIRREL:
                break;
            case GOOD_PLANT:
                break;
            case GOOD_BEAST:
                miniSquirel.updateEnergy(entityAtMoveDirection.getEnergy());
                killAndReplace(entityAtMoveDirection);
                move(newPosition,miniSquirel);
                break;
            case BAD_PLANT:
                miniSquirel.updateEnergy(entityAtMoveDirection.getEnergy());
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
                    kill(miniSquirel);
                } else if (((BadBeast) entityAtMoveDirection).getSNACK() == 0) {
                    killAndReplace(entityAtMoveDirection);
                    move(newPosition,miniSquirel);
                }
                break;
            case WALL:
                miniSquirel.updateEnergy(entityAtMoveDirection.getEnergy());
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
    }
    private Entity getFieldValue(XY position){
        return gameField[position.getX()][position.getY()];
    }
    private void move(XY position,Entity entity){
        setPlace(entity.getPosition(),null);
        entity.setPosition(position);
        setPlace(entity.getPosition(),entity);
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