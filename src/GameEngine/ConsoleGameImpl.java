package GameEngine;

import Commandos.ExecutableCommand;
import Commandos.UserActionsImpl;
import Console.ConsoleUI;

import java.lang.reflect.InvocationTargetException;

public class ConsoleGameImpl extends Game{
    private ConsoleUI consoleUI;
    private UserActionsImpl userActions;

    public ConsoleGameImpl(State state, ConsoleUI consoleUI, UserActionsImpl actions){
        super(state);
        this.consoleUI = consoleUI;
        this.userActions = actions;
    }

    public UserActionsImpl getUserActions() {
        return userActions;
    }

    public ConsoleUI getConsoleUI() {
        return consoleUI;
    }

    @Override
    protected void render() {
        consoleUI.message("Highscore: " + getState().getHighscore());
        consoleUI.render(getState().getFlattenedBoard());
    }

    @Override
    protected void update() {
        getState().update();
    }

    @Override
    protected void processInput() {
        userActions.setTurnFinished(false);
        while (!userActions.isTurnFinished()) {
            ExecutableCommand command = consoleUI.getCommand();
            if (command == null) {
                continue;
            }
            try {
                command.execute();
            } catch (InvocationTargetException | IllegalAccessException ex) {
                consoleUI.message(ex.getMessage());
                continue;
            }
        }
    }
}
