package Commandos;

import javafx.scene.input.KeyCode;

/**
 * Provides methods for movement of hand operated squirel
 */

public interface UserActions {
    @AsConsoleCommand(name = "help", helpText = "* list all commands",order = 0)
    public void help();

    @AsConsoleCommand(name = "exit", helpText = "* exit game", order = 1)
    public void exit();

    @AsConsoleCommand(name = "pass", helpText = "* pass the turn", order = 2)
    public void pass();

    @AsKeyCommand(name = "up", keys = {KeyCode.W})
    @AsConsoleCommand(name = "up", helpText = "* move up", order = 3)
    public void moveUp();

    @AsKeyCommand(name = "down", keys = {KeyCode.S})
    @AsConsoleCommand(name = "down", helpText = "* move down", order = 4)
    public void moveDown();

    @AsKeyCommand(name = "left", keys = {KeyCode.A})
    @AsConsoleCommand(name = "left", helpText = "* move left", order = 5)
    public void moveLeft();

    @AsKeyCommand(name = "right", keys = {KeyCode.D})
    @AsConsoleCommand(name = "right", helpText = "* move right", order = 6)
    public void moveRight();

    @AsKeyCommand(name = "left-up", keys = {KeyCode.A, KeyCode.W})
    @AsConsoleCommand(name = "lup", helpText = "* move up and left", order = 7)
    public void moveLeftUp();

    @AsKeyCommand(name = "right-up", keys = {KeyCode.D, KeyCode.W})
    @AsConsoleCommand(name = "rup", helpText = "* move up and right", order = 8)
    public void moveRightUp();

    @AsKeyCommand(name = "left-down", keys = {KeyCode.A, KeyCode.S})
    @AsConsoleCommand(name = "ldown", helpText = "* move down and left", order = 9)
    public void moveLeftDown();

    @AsKeyCommand(name = "right-down", keys = {KeyCode.D, KeyCode.S})
    @AsConsoleCommand(name = "rdown", helpText = "* move down and right", order = 10)
    public void moveRightDown();

    @AsConsoleCommand(name = "energy", helpText = "* show remaining energy",order = 11)
    public void showEnergy();

    @AsKeyCommand(name = "spawn", keys = {KeyCode.SPACE})
    @AsConsoleCommand(name = "spawn", helpText = "<energy>  * spawn a mini-squirrel with specified energy", order = 12)
    public void spawnMiniSquirrel(int energy);
}
