package Entity.bots;

import BotAPI.BotController;
import BotAPI.ControllerContext;
import Entity.EntityType;
import Movement.MoveDirection;
import Movement.XY;

import java.util.ArrayList;
import java.util.List;

public class MasterBotController implements BotController {
    @Override
    public void nextStep(ControllerContext context) {
        List<MoveDirection> prefferedDirections = new ArrayList<>();
        List<MoveDirection> acceptableDirections = new ArrayList<>();
        XY position = context.locate();
        for(MoveDirection direction: MoveDirection.values()){
            XY newPosition =position.plus(direction.getDirection());
            EntityType entityType = context.getEntityAt(newPosition);
            if(entityType == EntityType.GOOD_PLANT || entityType == entityType.GOOD_BEAST){
                prefferedDirections.add(direction);
            }else if (entityType == EntityType.NONE){
                acceptableDirections.add(direction);
            }
        }
        MoveDirection direction;
        if(!prefferedDirections.isEmpty()){
            int random = (int)(Math.random() * prefferedDirections.size());
            direction = prefferedDirections.get(random);

        }else if(!acceptableDirections.isEmpty()){
            int random = (int)(Math.random() * acceptableDirections.size());
            direction = acceptableDirections.get(random);
        }else{
            return;
        }
        context.move(direction.getDirection());


    }
}
