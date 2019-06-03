package GameEngine;

import Core.Board;
import Core.FlattenedBoard;
import Entity.*;
import Entity.bots.MasterBotController;
import Entity.bots.MasterSquirelBot;

import java.util.*;
import java.util.Map.Entry;
import java.util.logging.Level;
import java.util.logging.Logger;


public class State {
    private Logger logger = Logger.getLogger(State.class.getName());

    private int highscore = 0;
    private int activeScore = 0;
    private Board board;
    private FlattenedBoard flattenedBoard;
    private Map<String, BotScore> botscores = new HashMap<>();
    private GameMode gameMode;
    private boolean reset;


    public State( Board startBoard){
        this.board = startBoard;
        this.flattenedBoard = board.flatten();
        this.gameMode = startBoard.getGameMode();

        if(gameMode == GameMode.AI){
            for(MasterSquirelBot masterSquirelBot: board.getBots()){
                botscores.put(masterSquirelBot.getName(), new BotScore());
            }
        }

    }
    public State(Board board, Map<String, BotScore> botScores) {
        this(board);
        for (Entry<String, BotScore> entry : botScores.entrySet()) {
            this.botscores.put(entry.getKey(), entry.getValue());
        }
    }
    public Map<String, BotScore> getSortedBotScores() {
        List<Map.Entry<String, BotScore>> entryList = new ArrayList<>(botscores.entrySet());
        entryList.sort(Map.Entry.comparingByValue());
        Collections.reverse(entryList);
        Map<String, BotScore> sortedScores = new LinkedHashMap<>();
        for (Map.Entry<String, BotScore> entry : entryList) {
            sortedScores.put(entry.getKey(), entry.getValue());
        }
        return sortedScores;
    }


    public void setHighscore(int highscore){
            if (highscore > this.highscore) {
                this.highscore = highscore;
            }
    }

    public void setActiveScore(int activeScore) {
        this.activeScore = activeScore;
    }


    public int getHighscore() {
        return highscore;
    }


    public Board getBoard() {
        return board;
    }


    public void update(){
        Set<Entity> entitySet = board.getEntitySet();
        if(board.getGameMode() == GameMode.AI){
            flattenedBoard();
            for (MasterSquirelBot masterBot : board.getBots()) {
                BotScore scoreList = botscores.get(masterBot.getName());
                scoreList.addScore(masterBot.getEnergy());
                botscores.put(masterBot.getName(), scoreList);
            }
            callNextStep(entitySet);
            entitySet.removeAll(board.getRemovedEntities());
            entitySet.addAll(board.getAddedEntities());
            board.getRemovedEntities().clear();
            board.getAddedEntities().clear();
            printRoundResults();
        }else{
            this.setActiveScore(board.getMasterSquirel().getEnergy());
            if(activeScore > highscore){
                setHighscore(activeScore);
            }
            flattenedBoard();
            callNextStep(entitySet);
            entitySet.removeAll(board.getRemovedEntities());
            entitySet.addAll(board.getAddedEntities());
            board.getRemovedEntities().clear();
            board.getAddedEntities().clear();
        }
    }
    public FlattenedBoard flattenedBoard(){
        return this.board.flatten();
    }

    public FlattenedBoard getFlattenedBoard() {
        return flattenedBoard;
    }

   private void callNextStep(Set<Entity> entitySet) {
        for (Entity entity: entitySet) {
            if(entity instanceof Charachter && !board.getRemovedEntities().contains(entity)){
                Charachter charachter = (Charachter) entity;
                charachter.nextStep(flattenedBoard);
            }

        }
    }

    @Override
    public String toString(){

        String s = "Your current score is: " + this.highscore;
        return s;
    }
    private void printRoundResults() {
        String message = String.format("* Game  *\n");
        for (Map.Entry<String, BotScore> entry : getSortedBotScores().entrySet()) {
            message += entry.getKey() + "\n" + entry.getValue() + "\n";
        }
        System.out.println(message);
        logger.log(Level.INFO, message);
    }
    private void reset() {
        this.board = new Board(board.getBoardConfig(), gameMode);
        flattenedBoard = board.flatten();
        reset = true;
    }
    public boolean wasReset() {
        return reset;
    }


}
