package Main;

import Entity.*;
import Movement.XY;



public class Main {

    public static void main(String[]args){

        EntitySet entitySet = new EntitySet();
        MasterSquirel masterSquirel = new MasterSquirel(1,new XY(0,0));
        BadBeast badBeast = new BadBeast(2,new XY(3,4));
        GoodBeast goodBeast = new GoodBeast(3,new XY(2,1));
        GoodPlant goodPlant = new GoodPlant(4,new XY(5,6));
        BadPlant badPlant = new BadPlant(5,new XY(3,3));
        Wall wall = new Wall(6, new XY(0,1));

        MiniSquirel miniSquirel = masterSquirel.createMinisquirel(150);

        entitySet.addEntity(masterSquirel);
        entitySet.addEntity(badBeast);
        entitySet.addEntity(goodBeast);
        entitySet.addEntity(goodPlant);
        entitySet.addEntity(badPlant);
        entitySet.addEntity(wall);
        entitySet.addEntity(miniSquirel);

        entitySet.callNextStep();
        System.out.println(entitySet.toString());
    }
}
