package GameEngine;

import Core.Board;
import Core.FlattenedBoard;
import Entity.*;
import Entity.bots.MasterSquirelBot;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;


public class State {
    private Logger logger = Logger.getLogger(State.class.getName());

    private int highscore = 0;
    private int activeScore = 0;
    private final Board board;
    private final FlattenedBoard flattenedBoard;
    private Map<String, Integer> botscores = new HashMap<>();

    public State( Board startBoard){
        this.board = startBoard;
        this.flattenedBoard = board.flatten();
    }

    public void setHighscore(int highscore) {
        if(highscore > this.highscore){
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
        if(board.getGameMode() == GameMode.AI){

            flattenedBoard();
            for(MasterSquirelBot masterSquirelBot : board.getBots()){
                botscores.put(masterSquirelBot.getName(),masterSquirelBot.getEnergy());
            }
            printBotscore();
            callNextStep();
        }else{
            this.setActiveScore(board.getEntitySet().findHandoperated().getEnergy());
            if(activeScore > highscore){
                setHighscore(activeScore);
            }
            flattenedBoard();
            callNextStep();
        }

    }
    public FlattenedBoard flattenedBoard(){
        return this.board.flatten();
    }

    public FlattenedBoard getFlattenedBoard() {
        return flattenedBoard;
    }

    private void callNextStep() {
        for (int i = 0; i < board.getEntitySet().getLENTGH(); i++) {
            Entity entity = board.getEntitySet().get(i);
            if(!(entity instanceof Charachter)){
                continue;
            }
            Charachter charachter = (Charachter) entity;
            charachter.nextStep(flattenedBoard);
        }
    }

    @Override
    public String toString(){

        String s = "Your current score is: " + this.highscore;
        return s;
    }
    private void printBotscore(){
        String scores = String.format(" Botscore \n");
        for(Map.Entry<String,Integer> entry : botscores.entrySet()){
            scores += entry.getKey() + "\n" + entry.getValue() + "\n";
        }
        System.out.println(scores);
        logger.log(Level.INFO, scores);
    }

}
