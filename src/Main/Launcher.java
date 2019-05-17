package Main;


import Core.Board;
import Core.BoardConfig;
import Entity.*;
import GameEngine.*;

public class Launcher {

    public static void min(String[]args)throws Exception{
    //board, entityset state, game`
        EntitySet entitySet = new EntitySet();
        BoardConfig boardConfig = new BoardConfig();
        Board board = new Board(entitySet, boardConfig);
        State state = new State(board);
        Game game = new GameImpl(state);
        game.run();






/*        MasterSquirel masterSquirel = new MasterSquirel(1,new XY(0,0));
        BadBeast badBeast = new BadBeast(2,new XY(3,4));
        GoodBeast goodBeast = new GoodBeast(3,new XY(2,1));
        GoodPlant goodPlant = new GoodPlant(4,new XY(5,6));
        BadPlant badPlant = new BadPlant(5,new XY(3,3));
        Wall wall = new Wall(6, new XY(0,1));*//*
        MiniSquirel miniSquirel = masterSquirel.createMinisquirel(150, new XY(0,0));
        entitySet.addEntity(masterSquirel);
        entitySet.addEntity(badBeast);
        entitySet.addEntity(goodBeast);
        entitySet.addEntity(goodPlant);
        entitySet.addEntity(badPlant);
        entitySet.addEntity(wall);
        entitySet.addEntity(miniSquirel);*/
       /* EntitySet entitySet = new EntitySet();
        BoardConfig boardConfig = new BoardConfig(20,20,20);
        Board board =new Board(entitySet,boardConfig);
        System.out.println(board);
        */

    }
}
