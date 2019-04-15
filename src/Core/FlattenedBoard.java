package Core;
import Entity.*;
import Movement.XY;

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

    }

    @Override
    public void tryMove(MiniSquirel miniSquirel, XY moveDirection) {

    }

    @Override
    public void tryMove(GoodBeast goodBeast, XY moveDirection) {

    }

    @Override
    public void tryMove(BadBeast badBeast, XY moveDirection) {

    }

    @Override
    public PlayerEntity nearestPlayerEntity(XY position) {
        return null;
    }

    @Override
    public void kill(Entity entity) {

    }

    @Override
    public void killAndReplace(Entity entity) {

    }
}