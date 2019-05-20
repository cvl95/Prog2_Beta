package Main;


import Commandos.CommandScanner;
import Commandos.CommandTypeInfo;
import Commandos.ConsoleCommandType;
import Console.ConsoleUI;
import Console.UI;
import Core.Board;
import Core.BoardConfig;
import Entity.*;
import GameEngine.*;
import Util.ui.cosoleTest.MyFavouriteCommandType;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;

public class Launcher {

    public static void main(String[]args)throws Exception{
    //board, entityset state, game`

        List<ConsoleCommandType> commandTypes = ConsoleCommandType.getCommandTypes(MyFavouriteCommandType, CommandTypeInfo);
        CommandScanner cs= new CommandScanner(commandTypes, new BufferedReader(new InputStreamReader(System.in)));

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
