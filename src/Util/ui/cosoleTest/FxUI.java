package Util.ui.cosoleTest;

import Commandos.CommandScanner;
import Commandos.ExecutableCommand;
import Commandos.KeyCommandType;
import Console.UI;
import Core.Boardview;
import Entity.MiniSquirel;
import Movement.XY;
import javafx.application.Platform;
import javafx.scene.*;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class FxUI extends Scene implements UI {
    private static final int CELL_SIZE = 20;
    private final Canvas boardCanvas;
    private final Label msgLabel;
    private final Set<KeyCode> pressedKeys = new HashSet<>();
    private ExecutableCommand command;

    FxUI(Parent parent, Canvas boardCanvas, Label msgLabel){
        super(parent);
        this.boardCanvas = boardCanvas;
        this.msgLabel = msgLabel;
    }
    public static FxUI createInstance(XY boardSize, List<KeyCommandType> commandTypes) {
        FxUI fxUI = createInstance(boardSize);

        fxUI.setOnKeyPressed(event -> {
            Set<KeyCode> pressedKeys = fxUI.getPressedKeys();
            pressedKeys.add(event.getCode());
            for (KeyCommandType commandType : commandTypes) {
                if (pressedKeys.containsAll(Arrays.asList(commandType.getKeys()))) {
                    Object[] parameters = new Object[commandType.getParamTypes().length];
                    if (commandType.getName().equals("spawn")) {
                        parameters[0] = MiniSquirel.MINIMUM_SPAWN_ENERGY;
                    }
                    fxUI.setCommand(new ExecutableCommand(commandType, parameters));
                    return;
                }
            }
        });
        fxUI.setOnKeyReleased(event -> {
            fxUI.getPressedKeys().remove(event.getCode());
        });

        return fxUI;
    }



    public static FxUI createInstance(XY boardSize){
        Canvas boardCanvas = new Canvas(boardSize.getX() * CELL_SIZE, boardSize.getY() * CELL_SIZE);
        Label statusLabel = new Label();
        VBox top = new VBox();
        top.getChildren().add(boardCanvas);
        top.getChildren().add(statusLabel);
        final FxUI fxUI = new FxUI(top, boardCanvas, statusLabel);
        return fxUI;
    }



    @Override
    public ExecutableCommand getCommand() {
        return command;
    }

    public void setCommand(ExecutableCommand command) {
        this.command = command;
    }

    @Override
    public void render(Boardview boardview) {
        Platform.runLater(()-> repaintBoardCanvas(boardview));

    }

    @Override
    public void message(String msg) {
        Platform.runLater(()->msgLabel.setText(msg));

    }
    private Set<KeyCode> getPressedKeys(){
        return pressedKeys;
    }

    private void repaintBoardCanvas(Boardview view) {
        GraphicsContext gc = boardCanvas.getGraphicsContext2D();
        gc.clearRect(0, 0, boardCanvas.getWidth(), boardCanvas.getHeight());
        XY viewSize = view.getSize();

        for (int i = 0; i < viewSize.getX(); i++) {
            for (int j = 0; j < viewSize.getY(); j++) {
                int x = CELL_SIZE * i;
                int y = CELL_SIZE * j;
                switch (view.getEntityType(new XY(i, j))) {
                    case MASTER_SQUIRREL:
                        gc.setFill(Color.BLUE);
                        gc.fillRect(x, y, CELL_SIZE, CELL_SIZE);
                        break;
                    case MINI_SQUIRREL:
                        gc.setFill(Color.LIGHTBLUE);
                        gc.fillRect(x, y, CELL_SIZE, CELL_SIZE);
                        break;
                    case GOOD_BEAST:
                        gc.setFill(Color.GREEN);
                        gc.fillRect(x, y, CELL_SIZE, CELL_SIZE);
                        break;
                    case BAD_BEAST:
                        gc.setFill(Color.RED);
                        gc.fillRect(x, y, CELL_SIZE, CELL_SIZE);
                        break;
                    case GOOD_PLANT:
                        gc.setFill(Color.GREEN);
                        gc.fillOval(x, y, CELL_SIZE, CELL_SIZE);
                        break;
                    case BAD_PLANT:
                        gc.setFill(Color.RED);
                        gc.fillOval(x, y, CELL_SIZE, CELL_SIZE);
                        break;
                    case WALL:
                        gc.setFill(Color.GOLD);
                        gc.fillRect(x, y, CELL_SIZE, CELL_SIZE);
                        break;
                }
            }
        }
    }

    @Override
    public CommandScanner getCommandScanner() {
        return null;
    }
}
