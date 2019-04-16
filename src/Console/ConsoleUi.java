package Console;
import Core.Boardview;
import Entity.*;

public class ConsoleUI implements UI {

    public MoveCommand getCommand() {
        return new MoveCommand();
    }

    @Override
    public void render(Boardview boardview) {
        Entity [][] flattenedBoard = boardview.getBoardRep();
        StringBuilder outPut = null;

        for(int i = 0; i <= flattenedBoard.length-1; i++){
            for(int j = 0; j<=flattenedBoard.length-1; j++){
                outPut.append(identHelper(flattenedBoard[i][j])+" ");
            }outPut.append("\n");
        }
        System.out.print(outPut.toString());
    }

    @Override
    public void processInput() {

    }

    private String identHelper(Entity entity){
        if(entity instanceof BadBeast){
            return "BB";
        }else if(entity instanceof GoodBeast){
            return "GB";
        }else if(entity instanceof GoodPlant){
            return "GP";
        }else if(entity instanceof BadPlant){
            return "BP";
        }else if(entity instanceof Wall){
            return " +";
        }else if(entity instanceof MasterSquirel){
            return " M";
        }else{
            return " -";
        }

    }
    //Ein Eingabekommando abholen (zunächst nur nächster Move des Eichhörnchens im Single-Player-Modus)
    // und das Rendern des Boards, was bedeutet, dass der Render-Code einen Blick auf das Board werfen mus
}
