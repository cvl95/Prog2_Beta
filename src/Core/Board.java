package Core;

import Entity.*;
import Entity.EntitySet;
import Movement.XY;

public class Board {
    EntitySet entitySet;
    BoardConfig boardConfig;
    private Entity[][] gameField;


    public Board(EntitySet entitySet, BoardConfig boardConfig){
        this.entitySet = entitySet;
        this.boardConfig = boardConfig;
        this.gameField = new Entity[this.boardConfig.getBoardX()][this.boardConfig.getBoardY()];
        buildABoard();
    }

    private void buildABoard(){
        createWalls();
        fillEntitySet();
    }
    private void createWalls(){
        int max = this.gameField.length;
        for(int i = 0; i <= max-1; i++ ){
            entitySet.addEntity(new Wall(new XY(i,0)));
            entitySet.addEntity(new Wall(new XY(i,max-1)));
            // problem with corners created two times
            entitySet.addEntity(new Wall(new XY(0,i)));
            entitySet.addEntity(new Wall(new XY(max-1,i)));
        }

    }
    private void fillEntitySet(){
        //Fills gameField and EntitySet!

        int badBeastNo = boardConfig.getNumberOfBadbeast();
        int goodBeastNo = boardConfig.getNumberOfGoodbeast();
        int badPlantNo = boardConfig.getNumberOfBadplants();
        int goodPlantNo = boardConfig.getNumberOfGoodplants();
        int wallNo =boardConfig.getNumberOfWalls();
        XY xy = calculateRandomPosition();
        //Handoperated master squirrel
        try{
            xy  = findFreePlace(xy) ;
            HandOperatedMasterSquirel handOperatedMasterSquirel = new HandOperatedMasterSquirel(1000, xy);
            entitySet.addEntity(handOperatedMasterSquirel);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

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
    public XY findFreePlace(XY xy){
        if(this.gameField[xy.getX()][xy.getY()] == null){
            return  new XY (xy.getX(),xy.getY());
        }
        else{
            XY xy2 = calculateRandomPosition();
           return findFreePlace(xy2);
        }
    }
    public XY calculateRandomPosition() {
        int randomX = (int) (Math.random() * (this.gameField.length) - 1);
        int randomY = (int) (Math.random() * (this.gameField.length) - 1);
        return new XY(randomX, randomY);
    }
    public XY getSize(){
        return new XY(this.boardConfig.getBoardX(),this.boardConfig.getBoardY());
    }

    public EntitySet getEntitySet() {
        return entitySet;
    }

    public Entity[][] getGameField() {
        return gameField;
    }

    public FlattenedBoard flatten() {
        return new FlattenedBoard(this);
        }

    public void callNextStep() {
        for (int i = 0; i < this.entitySet.getLENTGH(); i++) {
            if(this.getEntitySet().getEntitySet()[i] instanceof Charachter ){
                this.getEntitySet().getEntitySet()[i].nextStep(flatten());
            }

        }
    }

}
