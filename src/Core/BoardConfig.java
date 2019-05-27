package Core;

import Entity.EntitySet;
import Entity.bots.MasterSquirelBot;
import Movement.XY;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BoardConfig {
    private static final Logger logger = Logger.getLogger(BoardConfig.class.getName());

    private static final XY SIZE = new XY(20,20);
    private static final int BOARD_WIDTH = 20;
    private static final int BOARD_HEIGHT = 20;
    private static final int NUMBER_OF_WALLS = 30;
    private static final int NUMBER_OF_GOOD_PLANTS = 10;
    private static final int NUMBER_OF_BAD_PLANTS = 10;
    private static final int NUMBER_OF_GOOD_BEAST = 3;
    private static final int NUMBER_OF_BAD_BEAST = 3;
    private static final int NUMBER_OF_SQUIREL = 1;
    private static final String[] MASTER_BOT_NAMES = {"MasterSquirelBot","MasterSquirelBot","MasterSquirelBot"};
    private static final String[] MINI_SQUIREL_NAMES = {"MiniSquirelBot", "MiniSquirelBot", "MiniSquirelBot"};


    private final XY size;
    private final int numberOfWalls;
    private final int numberOfGoodplants;
    private final int numberOfBadplants;
    private final int numberOfGoodbeast;
    private final int numberOfBadbeast;
    private final int numberOfSquirels;
    private final List<String> masterBotNames;
    private final List<String> miniBotnames;


    public BoardConfig(){
        numberOfBadbeast = NUMBER_OF_BAD_BEAST;
        numberOfBadplants = NUMBER_OF_BAD_PLANTS;
        numberOfGoodbeast = NUMBER_OF_GOOD_BEAST;
        numberOfGoodplants = NUMBER_OF_GOOD_PLANTS;
        numberOfWalls = NUMBER_OF_WALLS;
        numberOfSquirels = NUMBER_OF_SQUIREL;
        masterBotNames = Arrays.asList(MASTER_BOT_NAMES);
        miniBotnames = Arrays.asList(MINI_SQUIREL_NAMES);
        size = SIZE;
    }
    public BoardConfig(XY boardSize, int numberOfBadbeast,int numberOfBadplants, int numberOfGoodbeast,
                       int numberOfGoodplants, int numberOfWalls, int numberOfSquirels, String[] masterBotNames,String[] miniBotnames) throws InvalidBoardSize {
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
        this.miniBotnames = Arrays.asList(miniBotnames);
        this.masterBotNames = Arrays.asList(masterBotNames);
    }

    public static BoardConfig load(String propertiespath){
        InputStream in = BoardConfig.class.getResourceAsStream(propertiespath);
        Properties properties = new Properties();
        try{
            properties.load(in);
        }catch (IOException ex){
            logger.info(ex.getMessage());
            return new BoardConfig();
        }
        int boardWidth = BOARD_WIDTH;
        int boardHeight = BOARD_HEIGHT;
        int numberOfBadbeast = NUMBER_OF_BAD_BEAST;
        int numberOfBadplants = NUMBER_OF_BAD_PLANTS;
        int numberOfGoodbeast = NUMBER_OF_GOOD_BEAST;
        int numberOfGoodplants = NUMBER_OF_GOOD_PLANTS;
        int numberOfWalls = NUMBER_OF_WALLS;
        int numberOfSquirels = NUMBER_OF_SQUIREL;
        String[] masterBotNames = MASTER_BOT_NAMES;
        String[] miniBotnames = MINI_SQUIREL_NAMES;
        XY size = SIZE;

        try {
            boardWidth = Integer.parseInt(properties.getProperty("BOARD_WIDTH"));
        } catch (NumberFormatException ex) {
            logger.log(Level.WARNING, ex.getMessage(), ex);
        }
        try {
            boardHeight = Integer.parseInt(properties.getProperty("BOARD_HEIGHT"));
        } catch (NumberFormatException ex) {
            logger.log(Level.WARNING, ex.getMessage(), ex);
        }
        try {
            numberOfSquirels = Integer.parseInt(properties.getProperty("NUMBER_OF_SQUIREL"));
        } catch (NumberFormatException ex) {
            logger.log(Level.WARNING, ex.getMessage(), ex);
        }
        try {
            numberOfGoodbeast = Integer.parseInt(properties.getProperty("NUMBER_OF_GOOD_BEAST"));
        } catch (NumberFormatException ex) {
            logger.log(Level.WARNING, ex.getMessage(), ex);
        }
        try {
            numberOfBadbeast = Integer.parseInt(properties.getProperty("NUMBER_OF_BAD_BEAST"));
        } catch (NumberFormatException ex) {
            logger.log(Level.WARNING, ex.getMessage(), ex);
        }
        try {
            numberOfGoodplants = Integer.parseInt(properties.getProperty("NUMBER_OF_GOOD_PLANTS"));
        } catch (NumberFormatException ex) {
            logger.log(Level.WARNING, ex.getMessage(), ex);
        }
        try {
            numberOfBadplants = Integer.parseInt(properties.getProperty("NUMBER_OF_BAD_PLANTS"));
        } catch (NumberFormatException ex) {
            logger.log(Level.WARNING, ex.getMessage(), ex);
        }
        try {
            numberOfWalls = Integer.parseInt(properties.getProperty("NUMBER_OF_WALLS"));
        } catch (NumberFormatException ex) {
            logger.log(Level.WARNING, ex.getMessage(), ex);
        }
        if (properties.getProperty("MASTER_BOT_NAMES") != null) {
            masterBotNames = properties.getProperty("MASTER_BOT_NAMES").split(",\\s*");
        }
        if (properties.getProperty("MINI_BOT_NAMES") != null) {
            miniBotnames = properties.getProperty("MINI_BOT_NAMES").split(",\\s*");
        }
        try {
            return new BoardConfig(new XY(boardWidth, boardHeight), numberOfBadbeast, numberOfBadplants, numberOfGoodbeast,
                    numberOfGoodplants, numberOfWalls, numberOfSquirels, masterBotNames, miniBotnames);
        } catch (InvalidBoardSize invalidBoardSize) {
            invalidBoardSize.printStackTrace();
        }

        return null;
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

    public static int getBoardHeight() {
        return BOARD_HEIGHT;
    }

    public static int getBoardWidth() {
        return BOARD_WIDTH;
    }

    public List<String> getMasterBotNames() {
        return masterBotNames;
    }

    public List<String> getMiniBotnames() {
        return miniBotnames;
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
