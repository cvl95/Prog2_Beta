package Core;

import Entity.*;
import Entity.EntitySet;
import Movement.XY;

public class Board {
    EntitySet entitySet;
    BoardConfig boardConfig;
    private Entity[][] flattenedBoard;


    public Board(EntitySet entitySet, BoardConfig boardConfig){
        this.entitySet = entitySet;
        this.boardConfig = boardConfig;
        this.flattenedBoard = new Entity[this.boardConfig.getBoardX()][this.boardConfig.getBoardY()];
        buildABoard();
    }

    private void buildABoard(){
        createWalls();
        fillEntitySet();
    }
    private void createWalls(){
        int max = this.flattenedBoard.length;
        for(int i = 0; i <= max-1; i++ ){
            entitySet.addEntity(new Wall(new XY(i,0)));
            entitySet.addEntity(new Wall(new XY(i,max-1)));
            // problem with corners created two times
            entitySet.addEntity(new Wall(new XY(0,i)));
            entitySet.addEntity(new Wall(new XY(max-1,i)));
        }

    }
    private void fillEntitySet(){
        //Fills flattenedBoard and EntitySet!

        int badBeastNo = boardConfig.getNumberOfBadbeast();
        int goodBeastNo = boardConfig.getNumberOfGoodbeast();
        int badPlantNo = boardConfig.getNumberOfBadplants();
        int goodPlantNo = boardConfig.getNumberOfGoodplants();
        int wallNo =boardConfig.getNumberOfWalls();
        XY xy = null;
        // badBeast spawn
        for(int i = 1; i <= badBeastNo; i++ ){

                try{
                    xy  = findFreePlace(xy) ;
                    BadBeast badBeast = new BadBeast(-100 ,xy);
                    entitySet.addEntity(badBeast);
                }catch (Exception e){
                    System.out.println(e.getMessage());
                }
        }
        //goodBeast spawn
        for(int i = 1; i <= goodBeastNo; i++ ){

            try{
                xy = findFreePlace(xy);
                GoodBeast goodBeast = new GoodBeast(100,xy);
                entitySet.addEntity(goodBeast);
            }catch (Exception e){
                System.out.println(e.getMessage());
            }
        }
        // badPlant spawn
        for(int i = 1; i <= badPlantNo; i++ ){
            try{
                xy = findFreePlace(xy);
                BadPlant badPlant = new BadPlant(-100,xy);
                entitySet.addEntity(badPlant);
            }catch (Exception e){
                System.out.println(e.getMessage());
            }
        }
        //goodPlant spawn
        for(int i = 1; i <= goodPlantNo; i++ ){
            try{
                xy = findFreePlace(xy);
                GoodPlant goodPlant = new GoodPlant(50 ,xy);
                entitySet.addEntity(goodPlant);
            }catch (Exception e){
                System.out.println(e.getMessage());
            }
        }
        //walls spawn
        for(int i = 1; i <= wallNo ; i++ ){
            try{
                xy = findFreePlace(xy);
                Wall wall = new Wall(-10,xy);
                entitySet.addEntity(wall);
            }catch (Exception e){
                System.out.println(e.getMessage());
            }
        }

    }
    private XY findFreePlace(XY xy){
        if(this.flattenedBoard[xy.getX()][xy.getY()] == null){
            return  new XY (xy.getX(),xy.getY());
        }
        else{
            XY xy2 = calculateRandomPosition();
           return findFreePlace(xy2);
        }
    }
    private XY calculateRandomPosition() {
        int randomX = (int) (Math.random() * (this.flattenedBoard.length) - 1);
        int randomY = (int) (Math.random() * (this.flattenedBoard.length) - 1);
        return new XY(randomX, randomY);
    }
    public String flatten(){
        StringBuilder stringBuilder = new StringBuilder();

        for(int i = 0; i <= flattenedBoard.length-1; i++){
            for(int j = 0; j<=flattenedBoard[0].length-1-1; j++){
                stringBuilder.append(identHelper(this.flattenedBoard[i][j])+" ");
            }stringBuilder.append("\n");
        }
        return stringBuilder.toString();
    }
    private String identHelper(Entity entity){
        if(entity instanceof BadBeast){
            return " BB";
        }else if(entity instanceof GoodBeast){
            return " GB";
        }else if(entity instanceof GoodPlant){
            return " GP";
        }else if(entity instanceof BadPlant){
            return " BP";
        }else if(entity instanceof Wall){
            return " +";
        }else if(entity instanceof MasterSquirel){
            return " M";
        }else{
            return " -";
        }

    }

}
