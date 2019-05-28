package Entity.bots;

import BotAPI.*;
import Core.EntityContext;
import Entity.*;
import Entity.MasterSquirel;
import Movement.XY;
import Movement.XYSupport;

public class MasterSquirelBot extends MasterSquirel {
    public static int VIEW_RANGE = 15;
    private BotControllerFactory botControllerFactory;
    private BotController masterBotController;
    private ControllerContext controllerContext;
    private final String name;


    public MasterSquirelBot(int energy, XY pos, String name) {
        super(energy, pos);
        this.name = name;
        botControllerFactory = new BotControllerFactoryImpl();
        masterBotController = botControllerFactory.createMasterBotController();
    }
    @Override
    public void nextStep(EntityContext context){

        if (getStun()>0) {
            return;
        }
        if(controllerContext == null){
           controllerContext = new ControllerContextImpl(context);

        }
        masterBotController.nextStep(controllerContext);

    }
    public ControllerContext getControllerContextImpl(EntityContext context){
        return new ControllerContextImpl(context);
    }

    public String getName() {
        return name;
    }

    public class ControllerContextImpl implements ControllerContext {

        private EntityContext context;

        public ControllerContextImpl(EntityContext context){
            this.context = context;
        }

        @Override
        public XY getViewLowerLeft() {
            int x = getPosition().getX() - VIEW_RANGE;
            int y = getPosition().getY() + VIEW_RANGE;
            x = (x < 0) ? 0 : x;
            y = (y >context.getSize().getY()) ? context.getSize().getY() : y;
            return new XY(x, y);
        }

        @Override
        public XY getViewUpperRight() {
            int x = getPosition().getX() + VIEW_RANGE;
            int y = getPosition().getY() - VIEW_RANGE;
            x = (x > 0) ? context.getSize().getX() : x;
            y = (y < 0) ? 0 : y;
            return new XY(x, y);
        }

        @Override
        public XY locate() {
            return getPosition();
        }

        @Override
        public EntityType getEntityAt(XY xy) {
            if (!XYSupport.insideRectangle(xy, getViewLowerLeft(), getViewUpperRight())) {
                System.out.println(xy + " is out of view.");
            }
            return context.getEntityType(xy);
        }

        @Override
        public boolean isMine(XY xy) {
            if (!XYSupport.insideRectangle(xy, getViewLowerLeft(), getViewUpperRight())) {
                System.out.println(xy + " is out of view.");
            }
            if (context.getEntityType(xy) == EntityType.MINI_SQUIRREL
                    && ownsMini((MiniSquirel) context.getEntityAt(xy))) {
                return true;
            }
            return false;
        }

        @Override
        public void move(XY direction) {
            if (!XYSupport.isValidDirection(direction)) {
                return;
            }
            context.tryMove(MasterSquirelBot.this, direction);

        }

        @Override
        public void implode(int impactRadius) {
            System.out.println("This operation is not implemented");
        }

        @Override
        public int getEnergy() {
            return MasterSquirelBot.this.getEnergy();
        }

        @Override
        public XY directionOfMaster() {
            System.out.println("This operation is not implemented");
            return null;
        }


        @Override
        public long getRemainingSteps() {
            return 0;
        }
    }

}
