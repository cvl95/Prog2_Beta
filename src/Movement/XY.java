package Movement;

import static java.lang.Math.sqrt;

public class XY {

    private final int x;
    private final int y;

    public static final XY NONE = new XY(0, 0);
    public static final XY RIGHT = new XY(1, 0);
    public static final XY LEFT = new XY(-1, 0);
    public static final XY UP = new XY(0, -1);
    public static final XY DOWN = new XY(0, 1);
    public static final XY RIGHT_UP = new XY(1, -1);
    public static final XY RIGHT_DOWN = new XY(1, 1);
    public static final XY LEFT_UP = new XY(-1, -1);
    public static final XY LEFT_DOWN = new XY(-1, 1);

    public XY(int x, int y){
        this.x = x;
        this.y = y;
    }

    public XY increase(XY xy){
        return new XY(x +xy.x, y + xy.y);
    }

    public XY getNewPosition(){
        int newX = calculateRandom();
        int newY = calculateRandom();
        if(newX == 0 && newY == 0){
            getNewPosition();
        }
        return new XY(this.x + newX,this.y + newY);

    }
    private int calculateRandom(){

        double random;
        int y;
        random = Math.random()*3;
        y= -1 + (int)random;
        return y;
    }
    public XY getUserInputLoc(int nx, int ny){
        return new XY(this.x + nx, this.y + ny );
    }
    public XY setNewVectorPosition(XY xy){
        return new XY(this.x + xy.getX(), this.y + xy.getY());
    }

    public static XY[] getDirections() {
        return new XY[] {
                new XY(-1, 0), new XY(1, 0),
                new XY(0, -1), new XY(0, 1),
                new XY(-1, -1), new XY(-1, 1),
                new XY(1, -1), new XY(1, 1)
        };
    }
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public String toString(){
        String s = String.format("x: %s , y: %s ", this.x,this.y);
        return s;
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

    public double distanceFrom(XY position){
        int x = this.getX()-position.getX();
        int y = this.getY()-position.getY();
        return sqrt(x^2+y^2);
    }


}
