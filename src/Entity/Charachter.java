package Entity;

import Movement.XY;

public class Charachter extends Entity {

    Charachter(){}
    Charachter(int energy, XY pos){
        super(energy,pos);
    }

    public XY culcRun(int x, int y){
        if (x<0)
            x=-1;
        if (x>0)
            x=1;
        if (y<0)
            x=-1;
        if (y>1)
            x=1;

        return new XY(x,y);

    }
}
