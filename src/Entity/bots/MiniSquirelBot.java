package Entity.bots;

import BotAPI.BotController;
import BotAPI.BotControllerFactory;
import BotAPI.BotControllerFactoryImpl;
import BotAPI.ControllerContext;
import Core.EntityContext;
import Entity.Entity;
import Entity.EntityType;
import Entity.MiniSquirel;
import Movement.XY;
import Entity.MasterSquirel;
import Movement.XYSupport;

public class MiniSquirelBot extends MiniSquirel {
    public static final int VIEW_RANGE = 10;
    private final BotControllerFactory botControllerFactory;
    private final BotController miniBotController;
    private ControllerContext controllerContext;
    private Entity patron;

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

            patron=context.getEntityByID(getReferenceFather());
            if (impactRadius <= 0) {
                throw new IllegalArgumentException();
            }
            XY lowerLeft = XY.getLowerLeft(getPosition(),impactRadius,context.getSize());
            XY upperRight = XY.getUpperRight(getPosition(), impactRadius, context.getSize());
            double impactArea = impactRadius * impactRadius * Math.PI;
            int energy = getEnergy();
            int collectedEnergy = 0;
            for (int y = upperRight.getY(); y <= lowerLeft.getY(); y++) {
                for (int x = lowerLeft.getX(); x <= upperRight.getX(); x++) {
                    XY affectedCell = new XY(x, y);
                    double distance = affectedCell.distanceFrom(getPosition());
                    int energyLoss = (int) (200 * (energy / impactArea) * (1 - distance / impactRadius));
                    Entity entity = context.getEntityAt(affectedCell);
                    switch (context.getEntityType(affectedCell)) {
                        case MASTER_SQUIRREL:
                            MasterSquirel masterSquirrel = (MasterSquirel) entity;
                            if (!patron.equals(masterSquirrel)) {
                                if (energyLoss > masterSquirrel.getEnergy()) {
                                    energyLoss = masterSquirrel.getEnergy();
                                }
                                masterSquirrel.updateEnergy(-energyLoss);
                            }
                            collectedEnergy += energyLoss;
                            break;
                        case MINI_SQUIRREL:
                            MiniSquirel miniSquirrel = (MiniSquirel) entity;
                            if (patron.getId() !=(miniSquirrel.getReferenceFather())) {
                                if (energyLoss >= miniSquirrel.getEnergy()) {
                                    energyLoss = miniSquirrel.getEnergy();
                                    context.kill(miniSquirrel);
                                }
                                else {
                                    miniSquirrel.updateEnergy(-energyLoss);
                                }
                            }
                            collectedEnergy += energyLoss;
                            break;
                        case GOOD_BEAST:
                        case GOOD_PLANT:
                            if (energyLoss >= entity.getEnergy()) {
                                energyLoss = entity.getEnergy();
                                context.killAndReplace(entity);
                            }
                            else {
                                entity.updateEnergy(-energyLoss);
                            }
                            collectedEnergy += energyLoss;
                            break;
                        case BAD_BEAST:
                        case BAD_PLANT:
                            if (energyLoss >= -entity.getEnergy()) {
                                context.killAndReplace(entity);
                            }
                            else {
                                entity.updateEnergy(energyLoss);
                            }
                            break;
                        default:
                            break;
                    }
                }
            }
            patron.updateEnergy(collectedEnergy);
        }

        @Override
        public int getEnergy() {
            return 0;
        }

        @Override
        public XY directionOfMaster() {

            int x= getPosition().getX() - context.getEntityByID(getReferenceFather()).getPosition().getX();
            int y= getPosition().getY() - context.getEntityByID(getReferenceFather()).getPosition().getY();
            return new XY(x,y);
        }

        @Override
        public long getRemainingSteps() {
            return 0;
        }
    }
}
