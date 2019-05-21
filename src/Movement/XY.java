package Movement;

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

}
