package Core;

import Entity.EntitySet;
import Movement.XY;

public class BoardConfig {

    // später wollen wir diese in eine separate Text-Datei auslagern
    private static final XY SIZE = new XY(20,20);
    private static final int NUMBER_OF_WALLS = 30;
    private static final int NUMBER_OF_GOOD_PLANTS = 10;
    private static final int NUMBER_OF_BAD_PLANTS = 10;
    private static final int NUMBER_OF_GOOD_BEAST = 3;
    private static final int NUMBER_OF_BAD_BEAST = 3;
    private static final int NUMBER_OF_SQUIREL = 1;

    private final XY size;
    private final int numberOfWalls;
    private final int numberOfGoodplants;
    private final int numberOfBadplants;
    private final int numberOfGoodbeast;
    private final int numberOfBadbeast;
    private final int numberOfSquirels;


    public BoardConfig(){
        numberOfBadbeast = NUMBER_OF_BAD_BEAST;
        numberOfBadplants = NUMBER_OF_BAD_PLANTS;
        numberOfGoodbeast = NUMBER_OF_GOOD_BEAST;
        numberOfGoodplants = NUMBER_OF_GOOD_PLANTS;
        numberOfWalls = NUMBER_OF_WALLS;
        numberOfSquirels = NUMBER_OF_SQUIREL;
        size = SIZE;
    }
    public BoardConfig(XY boardSize, int numberOfBadbeast,int numberOfBadplants, int numberOfGoodbeast,
                       int numberOfGoodplants, int numberOfWalls, int numberOfSquirels) throws InvalidBoardSize {
        if(boardSize.getY() <= 0 || boardSize.getX() <= 0){
            throw new InvalidBoardSize("Board size is not correct " + getSize());
        }
        this.numberOfBadbeast = numberOfBadbeast;
        this.size = boardSize;
        this.numberOfSquirels = numberOfSquirels;
        this.numberOfWalls = numberOfWalls;
        this.numberOfGoodplants = numberOfGoodplants;
        this.numberOfBadplants = numberOfBadplants;
        this.numberOfGoodbeast = numberOfGoodbeast;
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

    public XY getSize() {
        return size;
    }

    public int getNumberOfSquirels() {
        return numberOfSquirels;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(String.format("Size:        %s\n", size));
        builder.append(String.format("Squirrels:   %d\n", numberOfSquirels));
        builder.append(String.format("Good Beasts: %d\n", numberOfGoodbeast));
        builder.append(String.format("Bad Beasts:  %d\n", numberOfBadbeast));
        builder.append(String.format("Good Plants: %d\n", numberOfGoodplants));
        builder.append(String.format("Bad Plants:  %d\n", numberOfBadplants));
        builder.append(String.format("Walls:       %d", numberOfWalls));
        return builder.toString();
    }
}
