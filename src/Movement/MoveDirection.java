package Movement;

public enum MoveDirection {

    NONE(XY.NONE),
    RIGHT(XY.RIGHT),
    LEFT(XY.LEFT),
    UP(XY.UP),
    DOWN(XY.DOWN),
    RIGHT_UP(XY.RIGHT_UP),
    RIGHT_DOWN(XY.RIGHT_DOWN),
    LEFT_UP(XY.LEFT_UP),
    LEFT_DOWN(XY.LEFT_DOWN);

    private XY direction;

    MoveDirection(XY xy){
        this.direction = xy;
    }

    public XY getDirection() {
        return direction;
    }
}
