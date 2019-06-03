package Core;

import BotAPI.BotController;
import BotAPI.BotControllerFactory;
import Entity.*;
import Entity.EntitySet;
import Entity.bots.MasterBotController;
import Entity.bots.MasterSquirelBot;
import Entity.bots.MiniBotController;
import GameEngine.GameMode;
import Movement.XY;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

public class Board {
    private Logger logger = Logger.getLogger(Board.class.getName());

    private BoardConfig boardConfig;
    private Entity[][] gameField;
    private GameMode gameMode;

    private EntitySet entitySet = new EntitySet();
    private List<MasterSquirelBot> bots = new ArrayList<>();
    private HandOperatedMasterSquirel masterSquirel;
    XY size;

    public Board(BoardConfig boardConfig, GameMode mode){
        this.gameMode = mode;
        this.boardConfig = boardConfig;
        this.gameField = new Entity[this.boardConfig.getSize().getX() + 2][this.boardConfig.getSize().getY() + 2];
        this.size = new XY(this.boardConfig.getSize().getX() + 2,this.boardConfig.getSize().getY() + 2);
        buildABoard();
    }

    private void buildABoard(){
        createWalls();
        fillEntitySet();
    }
    private void createWalls(){
        int max = this.gameField.length;
        Entity entity;
        for(int i = 0; i < max; i++ ){
            entity = new Wall(new XY(i,0));
            entitySet.addEntity(entity);
            gameField[i][0] = entity;
            entity = new Wall(new XY(i,max-1));
            entitySet.addEntity(entity);
            gameField[i][max-1] = entity;

            entity = new Wall(new XY(0,i));
            entitySet.addEntity(entity);
            gameField[0][i] = entity;
            entity = new Wall(new XY(max-1,i));
            entitySet.addEntity(entity);
            gameField[max-1][i] = entity;

        }

    }
    private void fillEntitySet(){
        //Fills gameField and EntitySet!
        XY xy = calculateRandomPosition();
        //squorels
        xy  = findFreePlace(xy);
        MasterSquirel player = null;
        if(gameMode == GameMode.SINGLE_PLAYER){
            player = new HandOperatedMasterSquirel(1000, xy);
            entitySet.addEntity(player);
            gameField[xy.getX()][xy.getY()] =  player;
        }else{
            Map<String, Integer> nameCountMap = new HashMap<>();
            // uber der Factory laufen
            for (int j = 0; j < boardConfig.getNumberOfSquirels(); j++) {
                String packageName = "Prog2_Beta.src.Entity.bots";
                String masterBotClassName = packageName + "." + boardConfig.getMasterBotNames().get(j);
                String miniBotClassName = packageName + "." + boardConfig.getMiniBotnames().get(j);
                String botName = masterBotClassName;
                if (nameCountMap.containsKey(botName)) {
                    botName += "-" + nameCountMap.get(botName);
                }
                xy  = findFreePlace(xy);
                try {
                    BotController masterBotController = (BotController)  Class.forName(masterBotClassName).newInstance();
                    BotController miniBotController = (BotController) Class.forName(miniBotClassName).newInstance();
                    MasterSquirelBot masterBot = new MasterSquirelBot(MasterSquirel.START_ENERGY, xy, botName, new BotControllerFactory() {
                        @Override
                        public BotController createMasterBotController() {
                            return masterBotController;
                        }

                        @Override
                        public BotController createMiniBotController() {
                            return miniBotController;
                        }
                    });

                entitySet.addEntity(masterBot);
                bots.add(masterBot);
                int count = nameCountMap.getOrDefault(masterBotClassName, 0);
                nameCountMap.put(masterBotClassName, count + 1);
            }catch (ClassNotFoundException | IllegalAccessException | InstantiationException  ex) {
                    logger.severe(ex.getMessage());
                }
                }
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
        int randomX = (int) (Math.random() * (boardConfig.getSize().getX()-1) );
        int randomY = (int) (Math.random() * (boardConfig.getSize().getY()-1) );
        return new XY(randomX, randomY);
    }

    public List<MasterSquirelBot> getBots() {
        return bots;
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

    /**
     *
     * @return a two-dimensional representation of the board
     */
    public FlattenedBoard flatten() {
        return new FlattenedBoard(this);
        }

    public GameMode getGameMode() {
        return gameMode;
    }
}
