package GameEngine;

import Core.Board;
import Core.FlattenedBoard;


public class State {

    private int highscore = 0;
    private int activeScore = 0;
    private Board board;

    public State( Board startBoard){
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
        this.setActiveScore(board.getEntitySet().findHandoperated().getEnergy());
        if(activeScore > highscore){
            setHighscore(activeScore);
        }
        board.callNextStep();
    }
    public FlattenedBoard flattenedBoard(){
        return this.board.flatten();
    }

    public Board getBoard() {
        return board;
    }

    @Override
    public String toString(){

        String s = "Your current score is: " + this.highscore;
        return s;
    }
}
