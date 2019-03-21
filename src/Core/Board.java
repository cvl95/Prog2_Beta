package Core;

import Entity.*;
import Entity.EntitySet;
import Movement.XY;

public class Board {
    EntitySet entitySet;
    BoardConfig boardConfig;
    private Entity[][] gameBoard;


    public Board(EntitySet entitySet, BoardConfig boardConfig){
        this.entitySet = entitySet;
        this.boardConfig = boardConfig;
        this.gameBoard = new Entity[this.boardConfig.getBoardX()][this.boardConfig.getBoardY()];
        buildABoard();
    }

    private void buildABoard(){
        createWalls();
        fillEntitySet();
    }
    private void createWalls(){
        int max = this.gameBoard.length;
        for(int i = 0; i <= max-1; i++ ){
            this.gameBoard[i][0] = new Wall(new XY(i,0));
            this.gameBoard[i][max-1] = new Wall(new XY(i,max-1));
            // problem with corners created two times
            this.gameBoard[0][i] = new Wall(new XY(0,i));
            this.gameBoard[max-1][i] = new Wall(new XY(max-1,i));
        }

    }
    private void fillEntitySet(){

        int badBeastNo = boardConfig.getNumberOfBadbeast();
        int goodBeastNo = boardConfig.getNumberOfGoodbeast();
        int badPlantNo = boardConfig.getNumberOfBadplants();
        int goodPlantNo = boardConfig.getNumberOfGoodplants();
        int wallNo =boardConfig.getNumberOfWalls();
        XY xy = calculateRandomPosition();
        // badBeast spawn
        for(int i = 1; i <= badBeastNo; i++ ){

                try{
                    xy  = findFreePlace(xy) ;
                    BadBeast badBeast = new BadBeast(generateRandomId() ,xy);
                    entitySet.addEntity(badBeast);
                    this.gameBoard[xy.getX()][xy.getY()] = badBeast;
                }catch (Exception e){
                    System.out.println(e.getMessage());
                }
        }
        //goodBeast spawn
        for(int i = 1; i <= goodBeastNo; i++ ){

            try{
                xy = findFreePlace(xy);
                GoodBeast goodBeast = new GoodBeast(generateRandomId(),xy);
                entitySet.addEntity(goodBeast);
                this.gameBoard[xy.getX()][xy.getY()] = goodBeast;
            }catch (Exception e){
                System.out.println(e.getMessage());
            }
        }
        // badPlant spawn
        for(int i = 1; i <= badPlantNo; i++ ){
            try{
                xy = findFreePlace(xy);
                BadPlant badPlant = new BadPlant(generateRandomId(),xy);
                entitySet.addEntity(badPlant);
                this.gameBoard[xy.getX()][xy.getY()] = badPlant;
            }catch (Exception e){
                System.out.println(e.getMessage());
            }
        }
        //goodPlant spawn
        for(int i = 1; i <= goodPlantNo; i++ ){
            try{
                xy = findFreePlace(xy);
                GoodPlant goodPlant = new GoodPlant(generateRandomId(),xy);
                entitySet.addEntity(goodPlant);
                this.gameBoard[xy.getX()][xy.getY()] = goodPlant;
            }catch (Exception e){
                System.out.println(e.getMessage());
            }
        }
        //walls spawn
        for(int i = 1; i <= wallNo ; i++ ){
            try{
                xy = findFreePlace(xy);
                Wall wall = new Wall(generateRandomId(),xy);
                entitySet.addEntity(wall);
                this.gameBoard[xy.getX()][xy.getY()] = wall;
            }catch (Exception e){
                System.out.println(e.getMessage());
            }
        }


    }
    private XY findFreePlace(XY xy){
        if(this.gameBoard[xy.getX()][xy.getY()] == null){
            return  new XY (xy.getX(),xy.getY());
        }
        else{
            XY xy2 = calculateRandomPosition();
           return findFreePlace(xy2);
        }
    }
    private XY calculateRandomPosition(){
        int randomX = (int)(Math.random() * (this.gameBoard.length)-1);
        int randomY = (int)(Math.random() * (this.gameBoard.length)-1);
        return new XY(randomX,randomY);
    }
    public int generateRandomId(){
        int id = (int) (System.currentTimeMillis() & 0xfffffff);
        return id;
    }


    public Entity[][] getGameBoard() {
        return gameBoard;
    }


    @Override
    public String toString(){
        StringBuilder stringBuilder = new StringBuilder();

        for(int i = 0; i <= this.gameBoard.length-1; i++){
            for(int j = 0; j<=this.gameBoard.length-1; j++){
                stringBuilder.append(printHelper(this.gameBoard[i][j])+" ");
            }stringBuilder.append("\n");
        }
        return stringBuilder.toString();
    }
    private String printHelper(Entity entity){
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
