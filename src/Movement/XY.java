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


    public XY plus(XY xy) {
        return new XY(x + xy.x, y + xy.y );
    }

    public XY minus(XY xy) {
        return new XY(x - xy.x, y - xy.y);
    }

    public XY times(int factor) {
        return new XY(x * factor, y * factor);
    }

    public double length() {
        return Math.sqrt((x * x) + (y * y));
    }

    /**
     * @param xy a second coordinate pair
     * @return the euklidian distance (pythagoras)
     */
    public double distanceFrom(XY xy) {
        int distX = x - xy.x;
        int distY = y - xy.y;
        return Math.sqrt((distX * distX) + (distY * distY));
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

    @Override
    public boolean equals(Object obj) {
        XY xy = (XY) obj;
        if(this.getY() == xy.getY() && xy.getX() == xy.getX()){
            return true;
        }
        return false;
    }
}
