package Core;

public class InvalidBoardSize extends Exception {

    public InvalidBoardSize(){
        super();
    }
    public InvalidBoardSize(String msg){
        super(msg);
    }
}
