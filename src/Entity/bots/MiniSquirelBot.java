package Entity.bots;

import BotAPI.BotController;
import BotAPI.BotControllerFactory;
import BotAPI.BotControllerFactoryImpl;
import BotAPI.ControllerContext;
import Core.EntityContext;
import Entity.EntityType;
import Entity.MiniSquirel;
import Movement.XY;
import Movement.XYSupport;

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
            controllerContext = new ControllerContextImpl();
        }
        miniBotController.nextStep(controllerContext);

    }

    public ControllerContext getControllerContext() {
        return controllerContext;
    }

    public class ControllerContextImpl implements ControllerContext {

        private EntityContext context;

        @Override
        public XY getViewLowerLeft() {
            return null;
        }

        @Override
        public XY getViewUpperRight() {
            return null;
        }

        @Override
        public XY locate() {
            return null;
        }

        @Override
        public EntityType getEntityAt(XY xy) {
            return null;
        }

        @Override
        public boolean isMine(XY xy) {
            return false;
        }

        @Override
        public void move(XY direction) {

        }

        @Override
        public void implode(int impactRadius) {

        }

        @Override
        public int getEnergy() {
            return 0;
        }

        @Override
        public XY directionOfMaster() {
            return null;
        }

        @Override
        public long getRemainingSteps() {
            return 0;
        }
    }
}
