package Core;

import Entity.EntitySet;

public class BoardConfig {

    // später wollen wir diese in eine separate Text-Datei auslagern
    private int boardX;
    private int boardY;
    private int numberOfEntities;

    //entity number pro Typ in Prozenten
    private int numberOfWalls;      //10%
    private int numberOfGoodplants; //20%
    private int numberOfBadplants;  //20%
    private int numberOfGoodbeast;  //25%
    private int numberOfBadbeast;   //25%



    public BoardConfig(int width,int height, int entitiesAmount) throws Exception {
        if(width == height) {
            this.boardX = width;
            this.boardY = height;
            this.numberOfEntities = entitiesAmount;
            calculateNumberOfBeasts();
        }else{
            throw new Exception("unequal width and height");
        }


    }

    private void calculateNumberOfBeasts() throws Exception {
        //maybe add modulo calculation to know if given amount of entities is equal or unequal number
        int S = (this.boardX-2) * (this.boardY-2);
        if(S < this.numberOfEntities){
            throw new Exception("ERROR: Too many entities in a field. Please reduce amount of entities." +
                    "\nCurrent amount is: " +this.numberOfEntities);
        }else if(this.numberOfEntities == 0){
            throw new Exception("Error: you entered 0 entities please enter number > 0");

        }else if(this.numberOfEntities > EntitySet.getLENTGH()){
            throw new Exception("Error: maximum entities you can fill is: "+EntitySet.getLENTGH());
        }
        else{
            this.numberOfBadbeast = (int)(this.numberOfEntities * 0.25);
            this.numberOfGoodbeast = (int)(this.numberOfEntities * 0.25);
            this.numberOfBadplants = (int)(this.numberOfEntities * 0.2);
            this.numberOfGoodplants = (int)(this.numberOfEntities * 0.2);
            this.numberOfWalls = (int)(this.numberOfEntities * 0.1);
        }



    }

    public int getBoardX() {
        return boardX;
    }

    public int getBoardY() {
        return boardY;
    }

    public int getNumberOfWalls() {
        return numberOfWalls;
    }

    public int getNumberOfGoodplants() {
        return numberOfGoodplants;
    }

    public int getNumberOfBadplants() {
        return numberOfBadplants;
    }

    public int getNumberOfGoodbeast() {
        return numberOfGoodbeast;
    }

    public int getNumberOfBadbeast() {
        return numberOfBadbeast;
    }
}
