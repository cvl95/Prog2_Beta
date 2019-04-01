package Movement;

public class XY {

    private int x;
    private int y;

    public XY(int x, int y){
        this.x = x;
        this.y = y;
    }
    public void getNewPosition(){

        int newX = calculateRandom();
        int newY = calculateRandom();
        if(newX == 0 && newY == 0){
            getNewPosition();
        }

        this.x = this.x + newX;
        this.y = this.y + newY;

    }
    private int calculateRandom(){

        double random;
        int y;
        random = Math.random()*3;
        y= -1 + (int)random;
        return y;
    }
    public void getUserInputLoc(int nx, int ny){
        this.x = this.x + nx;
        this.y = this.y + ny;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    @Override
    public String toString(){
        String s = String.format("x: %s , y: %s ", this.x,this.y);
        return s;
    }

}
