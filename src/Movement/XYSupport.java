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
    public static XY getLowerLeft(XY origin, int radius, XY size) {
        if (!insideBorders(origin, size) || radius <= 0) {
            throw new IllegalArgumentException();
        }
        int x = origin.getX() - radius;
        int y = origin.getY() + radius;
        x = (x < 0) ? 0 : x;
        y = (y > size.getX()) ? size.getY() : y;
        return new XY(x, y);
    }

    public static XY getUpperRight(XY origin, int radius, XY size) {
        if (!insideBorders(origin, size) || radius <= 0) {
            throw new IllegalArgumentException();
        }
        int x = origin.getX() + radius;
        int y = origin.getY() - radius;
        x = (x > 0) ? size.getX() : x;
        y = (y < 0) ? 0 : y;
        return new XY(x, y);
    }

    public static boolean insideBorders(XY point, XY size) {
        if (size.getX() <= 0 || size.getY() <= 0) {
            throw new IllegalArgumentException();
        }
        return insideBorders(point, new XY(0, size.getY()), new XY(size.getX(), 0));
    }

    public static boolean insideBorders(XY point, XY lowerLeft, XY upperRight) {
        return (lowerLeft.getX() <= point.getX() && point.getX() <= upperRight.getX())
                && (upperRight.getY() <= point.getY() && point.getY() <= lowerLeft.getY());
    }

}
