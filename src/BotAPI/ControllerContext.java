package BotAPI;

import Entity.EntityType;
import Movement.XY;

public interface ControllerContext {
    XY getViewLowerLeft();
    XY getViewUpperRight();
    XY locate();
    EntityType getEntityAt(XY xy);
    boolean isMine(XY xy);
    void move(XY direction);
    void implode(int impactRadius);
    int getEnergy();
    XY directionOfMaster();
    long getRemainingSteps();
    default void shout(String text){};
}
