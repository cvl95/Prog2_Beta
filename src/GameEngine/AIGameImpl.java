package GameEngine;

import Entity.Entity;
import Entity.bots.MasterSquirelBot;
import Util.ui.cosoleTest.FxUI;

import java.util.ArrayList;
import java.util.List;

public class AIGameImpl extends Game {
    private FxUI fxUI;
    private List<MasterSquirelBot> masterSquirelBots;

    public AIGameImpl(State state, FxUI fxUI){
        super(state);
        this.fxUI = fxUI;
        masterSquirelBots = getMasterSquirelBots();
    }

    public List<MasterSquirelBot> getMasterSquirelBots() {
        List<MasterSquirelBot> masterBots = new ArrayList<>();
        for (Entity entity : getState().getBoard().getEntitySet().getEntitySet()) {
            if (entity instanceof MasterSquirelBot) {
                masterBots.add((MasterSquirelBot) entity);
            }
        }
        return masterSquirelBots;
    }

    @Override
    protected void render() {
        String status = "";
        for (MasterSquirelBot masterBot : masterSquirelBots) {
            status += "Bot energy: " + masterBot.getEnergy() + "   ";
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

    }
}
