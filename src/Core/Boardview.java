package Core;
import Entity.EntityType;
import Entity.Entity;
import Movement.XY;

public interface Boardview {
    public Entity[][] getBoardRep();

    public EntityType getEntityType(int x, int y);

    public XY getSize();


}
