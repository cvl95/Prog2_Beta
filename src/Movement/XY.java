package Movement;

public class XY {

    private final int x;
    private final int y;
    private int[][] gamefield = new int[20][20];

    public XY(int x, int y){
        this.x = x;
        this.y = y;
    }
    public XY getNewPosition(){

        int newX = calculateRandom();
        int newY = calculateRandom();
        if(newX == 0 && newY == 0){
            getNewPosition();
        }

       // this.x = this.x + newX;
        //this.y = this.y + newY;
        return new XY(this.x + newX, this.y + newY);

    }
    private int calculateRandom(){

        double random;
        int y;
        random = Math.random()*3;
        y= -1 + (int)random;
        return y;
    }
    public XY getUserInputLoc(int nx, int ny){
        return new XY(this.x + nx, this.y + ny);
    }

    @Override
    public String toString(){
        String s = String.format("x: %s , y: %s ", this.x,this.y);
        return s;
    }

}
