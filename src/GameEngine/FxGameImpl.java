package GameEngine;

import Commandos.ExecutableCommand;
import Console.UI;
import Entity.HandOperatedMasterSquirel;
import Util.ui.cosoleTest.FxUI;

import java.lang.reflect.InvocationTargetException;

public class FxGameImpl extends Game {
    private FxUI fxUI;
    private HandOperatedMasterSquirel player;

    public FxGameImpl(State state, FxUI fxUI, HandOperatedMasterSquirel player){
        super(state);
        this.fxUI = fxUI;
        this.player = player;
    }




    @Override
    protected void render() {
        String status = "Energy: " + String.valueOf(player.getEnergy());
        if(player.getStun()>0){
            status += "  Stunned!";
        }
        fxUI.message(status);
        fxUI.render(getState().getFlattenedBoard());
    }

    @Override
    protected void update() {
        getState().update();

    }

    @Override
    protected void processInput() {
        ExecutableCommand command = fxUI.getCommand();
        if (command == null){
            return;
        }
            try {
                command.execute();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
    }
}
