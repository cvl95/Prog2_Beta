package Entity.bots;

import BotAPI.BotController;
import BotAPI.ControllerContext;

import java.util.ArrayList;
import java.util.List;

public class MasterBotController implements BotController {
    @Override
    public void nextStep(ControllerContext context) {
        List<MoveDirection> prefferedDirections = new ArrayList<>();
        List<MoveDirection> acceptableDirections = new ArrayList<>();
        

    }
}
