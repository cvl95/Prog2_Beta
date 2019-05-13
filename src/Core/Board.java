package Core;

import Entity.*;
import Entity.EntitySet;
import Movement.XY;

public class Board {
    EntitySet entitySet;
    BoardConfig boardConfig;
    private Entity[][] gameField;
    XY size;


    public Board(EntitySet entitySet, BoardConfig boardConfig){
        this.entitySet = entitySet;
        this.boardConfig = boardConfig;
        this.gameField = new Entity[this.boardConfig.getSize().getX() + 2][this.boardConfig.getSize().getY() + 2];
        this.size = new XY(this.boardConfig.getSize().getX() + 2,this.boardConfig.getSize().getY() + 2);
        buildABoard();
    }

    private void buildABoard(){
        fillEntitySet();
        createWalls();
    }
    private void createWalls(){
        int max = this.gameField.length;
        for(int i = 0; i < max; i++ ){
            entitySet.addEntity(new Wall(new XY(i,0)));
            entitySet.addEntity(new Wall(new XY(i,max-1)));
            // problem with corners created two times
            entitySet.addEntity(new Wall(new XY(0,i)));
            entitySet.addEntity(new Wall(new XY(max-1,i)));
        }

    }
    private void fillEntitySet(){
        //Fills gameField and EntitySet!
        XY xy = calculateRandomPosition();
        //squorels
        for(int i = 0; i < boardConfig.getNumberOfSquirels(); i++){
            xy  = findFreePlace(xy) ;
            HandOperatedMasterSquirel handOperatedMasterSquirel = new HandOperatedMasterSquirel(1000, xy);
            entitySet.addEntity(handOperatedMasterSquirel);
            gameField[xy.getX()][xy.getY()] =  handOperatedMasterSquirel;
        }
        // badBeast spawn
        for(int i = 1; i < boardConfig.getNumberOfBadbeast(); i++ ){
            xy  = findFreePlace(xy) ;
            BadBeast badBeast = new BadBeast(100, xy);
            entitySet.addEntity(badBeast);
            gameField[xy.getX()][xy.getY()] = badBeast;
        }
        //goodBeast spawn
        for(int i = 1; i < boardConfig.getNumberOfGoodbeast(); i++ ){
            xy = findFreePlace(xy);
            GoodBeast goodBeast = new GoodBeast(100, xy);
            entitySet.addEntity(goodBeast);
            gameField[xy.getX()][xy.getY()] = goodBeast;
        }
        // badPlant spawn
        for(int i = 1; i < boardConfig.getNumberOfBadplants(); i++ ){
            xy = findFreePlace(xy);
            BadPlant badPlant = new BadPlant(-100,xy);
            entitySet.addEntity(badPlant);
            gameField[xy.getX()][xy.getY()] = badPlant;
        }
        //goodPlant spawn
        for(int i = 1; i < boardConfig.getNumberOfGoodplants(); i++ ){
            xy = findFreePlace(xy);
            GoodPlant goodPlant = new GoodPlant(50, xy);
            entitySet.addEntity(goodPlant);
            gameField[xy.getX()][xy.getY()] = goodPlant;

        }
        //walls spawn
        for(int i = 1; i < boardConfig.getNumberOfWalls(); i++ ){
            xy = findFreePlace(xy);
            Wall wall = new Wall(-10, xy);
            entitySet.addEntity(wall);
            gameField[xy.getX()][xy.getY()] = wall;
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
        int randomX = (int) (Math.random() * (boardConfig.getSize().getX()) );
        int randomY = (int) (Math.random() * (boardConfig.getSize().getY()) );
        return new XY(randomX, randomY);
    }

    public XY getSize(){
        return size;
    }

    public EntitySet getEntitySet() {
        return entitySet;
    }

    public BoardConfig getBoardConfig() {
        return boardConfig;
    }

    public Entity[][] getGameField() {
        return this.gameField;
    }

    public FlattenedBoard flatten() {
        return new FlattenedBoard(this);
        }
}
