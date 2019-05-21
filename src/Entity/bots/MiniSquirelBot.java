package Entity.bots;

import BotAPI.BotController;
import BotAPI.BotControllerFactory;
import BotAPI.BotControllerFactoryImpl;
import BotAPI.ControllerContext;
import Core.EntityContext;
import Entity.MiniSquirel;
import Movement.XY;

public class MiniSquirelBot extends MiniSquirel {
    public static final int VIEW_RANGE = 10;
    private final BotControllerFactory botControllerFactory;
    private final BotController miniBotController;
    private ControllerContext controllerContext;

    public MiniSquirelBot(int energy, XY position) {
        super(energy, position);
        botControllerFactory = new BotControllerFactoryImpl();
        miniBotController = botControllerFactory.createMiniBotController();
    }

    @Override
    public void nextStep(EntityContext context) {
        if (getStun()>0) {
            return;
        }
        if(controllerContext == null){
            controllerContext = new MasterSquirelBot.ControllerContextImpl(context);
        }
        miniBotController.nextStep(controllerContext);

    }
}
