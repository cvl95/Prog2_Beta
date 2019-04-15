package Core;
import Entity.*;
import Movement.XY;

public interface Boardview {
    XY getSize();
    EntityType getEntityType(XY position);
}
