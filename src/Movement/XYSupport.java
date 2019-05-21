package Movement;

public class XYSupport {

    public static boolean insideRectangle(XY point, XY lowerLeft, XY upperRight) {
        return (point.getX() >= lowerLeft.getX() && point.getX() <= upperRight.getX())
                && (point.getY() <= lowerLeft.getY() && point.getY() >= upperRight.getY());
    }

    public static boolean isValidDirection(XY xy) {
        for (MoveDirection direction : MoveDirection.values()) {
            if (direction.getDirection().equals(xy)) {
                return true;
            }
        }
        return false;
    }
}
