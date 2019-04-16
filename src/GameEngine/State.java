package GameEngine;

import Core.Board;
import Entity.HandOperatedMasterSquirel;
import Entity.MasterSquirel;

public class State {

    private int highscore = 0;
    private int activeScore = 0;
    private Board board;

    public State(int startScore, Board startBoard){
        this.highscore = startScore;
        this.activeScore = startScore;
        this.board = startBoard;

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
    public void update(){
        this.board.flatten();
        this.setActiveScore(board.getEntitySet().findHandoperated().getEnergy());
        if(activeScore > highscore){
            setHighscore(activeScore);
        }

    }

    @Override
    public String toString(){

        String s = "Your current score is: " + this.highscore;
        return s;
    }
}
