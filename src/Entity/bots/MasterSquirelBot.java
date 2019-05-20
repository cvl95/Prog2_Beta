package Entity.bots;

import BotAPI.BotController;
import BotAPI.BotControllerFactory;
import BotAPI.BotControllerFactoryImpl;
import BotAPI.ControllerContext;
import Core.EntityContext;
import Entity.Entity;
import Entity.MasterSquirel;
import Movement.XY;

public class MasterSquirelBot extends MasterSquirel {
    public static int VIEW_RANGE = 15;
    private BotControllerFactory botControllerFactory;
    private BotController masterBotController;
    private ControllerContext controllerContext;


    public MasterSquirelBot(int energy, XY pos) {
        super(energy, pos);
        botControllerFactory = new BotControllerFactoryImpl();
        masterBotController = botControllerFactory.createMasterBotController();
    }
    @Override
    public void nextStep(EntityContext context){
        if(controllerContext == null){
            ControllerContext controllerContext = new ControllerContextImpl(context);
            ControllerContextHandler handler = new ControllerContextHandler(controllerContext);

        }

    }


}
