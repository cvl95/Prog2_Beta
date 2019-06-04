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
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Board {
    private Logger logger = Logger.getLogger(Board.class.getName());

    private BoardConfig boardConfig;
    private Entity[][] gameField;
    private GameMode gameMode;

    private Set<Entity> entitySet = new TreeSet<>();
    private List<MasterSquirelBot> bots = new ArrayList<>();
    private Set<Entity> addedEntities = new HashSet<>();
    private Set<Entity> removedEntities = new HashSet<>();
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
            entitySet.add(entity);
            gameField[i][0] = entity;
            entity = new Wall(new XY(i,max-1));
            entitySet.add(entity);
            gameField[i][max-1] = entity;

            entity = new Wall(new XY(0,i));
            entitySet.add(entity);
            gameField[0][i] = entity;
            entity = new Wall(new XY(max-1,i));
            entitySet.add(entity);
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
            this.masterSquirel = (HandOperatedMasterSquirel) player;
            entitySet.add(player);
            gameField[xy.getX()][xy.getY()] =  player;
            logger.log(Level.INFO, masterSquirel.toString());
        }else{
            Map<String, Integer> nameCountMap = new HashMap<>();
            // uber der Factory laufen
            for (int j = 0; j < boardConfig.getNumberOfSquirels(); j++) {
                String packageName = "Entity.bots";
                String masterBotClassName = packageName + "." + boardConfig.getMasterBotNames().get(j);
                String miniBotClassName = packageName + "." + boardConfig.getMiniBotnames().get(j);
                String botName = masterBotClassName;
                if (nameCountMap.containsKey(botName)) {
                    botName += "-" + nameCountMap.get(botName);
                }
                xy  = findFreePlace(xy);
                try {
                    BotController masterBotController = (BotController)  Class.forName(masterBotClassName).getDeclaredConstructor().newInstance();
                    BotController miniBotController = (BotController) Class.forName(miniBotClassName).getDeclaredConstructor().newInstance();
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

                entitySet.add(masterBot);
                bots.add(masterBot);
                int count = nameCountMap.getOrDefault(masterBotClassName, 0);
                nameCountMap.put(masterBotClassName, count + 1);
            }catch (ClassNotFoundException | IllegalAccessException | InstantiationException | NoSuchMethodException | InvocationTargetException ex) {
                    logger.severe(ex.toString());
                }
                }
            }
        // badBeast spawn
        for(int i = 0; i < boardConfig.getNumberOfBadbeast(); i++ ){
            xy  = findFreePlace(xy) ;
            BadBeast badBeast = new BadBeast(100, xy);
            entitySet.add(badBeast);
            gameField[xy.getX()][xy.getY()] = badBeast;
            logger.log(Level.INFO, badBeast.toString());
        }
        //goodBeast spawn
        for(int i = 0; i < boardConfig.getNumberOfGoodbeast(); i++ ){
            xy = findFreePlace(xy);
            GoodBeast goodBeast = new GoodBeast(100, xy);
            entitySet.add(goodBeast);
            gameField[xy.getX()][xy.getY()] = goodBeast;
            logger.log(Level.INFO, goodBeast.toString());
        }
        // badPlant spawn
        for(int i = 0; i < boardConfig.getNumberOfBadplants(); i++ ){
            xy = findFreePlace(xy);
            BadPlant badPlant = new BadPlant(-100,xy);
            entitySet.add(badPlant);
            gameField[xy.getX()][xy.getY()] = badPlant;
        }
        //goodPlant spawn
        for(int i = 0; i < boardConfig.getNumberOfGoodplants(); i++ ){
            xy = findFreePlace(xy);
            GoodPlant goodPlant = new GoodPlant(50, xy);
            entitySet.add(goodPlant);
            gameField[xy.getX()][xy.getY()] = goodPlant;

        }
        //walls spawn
        for(int i = 0; i < boardConfig.getNumberOfWalls(); i++ ){
            xy = findFreePlace(xy);
            Wall wall = new Wall(-10, xy);
            entitySet.add(wall);
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

    public Set<Entity> getAddedEntities() {
        return addedEntities;
    }

    public Set<Entity> getRemovedEntities() {
        return removedEntities;
    }
    public void add(Entity entity) {
        addedEntities.add(entity);
    }

    public void remove(Entity entity) {
        removedEntities.add(entity);
    }


    public XY getSize(){
        return size;
    }

    public Set<Entity> getEntitySet() {
        return entitySet;
    }

    public BoardConfig getBoardConfig() {
        return boardConfig;
    }

    public HandOperatedMasterSquirel getMasterSquirel() {
        return masterSquirel;
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
