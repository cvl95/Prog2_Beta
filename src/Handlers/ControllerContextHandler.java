package Handlers;

import BotAPI.ControllerContext;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ControllerContextHandler implements InvocationHandler {
    private final Logger logger = Logger.getLogger(ControllerContextHandler.class.getName());
    private ControllerContext  controllerContext;

    public ControllerContextHandler(ControllerContext controllerContext){
        this.controllerContext = controllerContext;
    }


    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (!method.getName().equals("getEntityAt")) {
            String message = "* Calling method \"" + method.getName() + "\"";
            if (args != null && args.length > 0) {
                message += " with parameters " + Arrays.toString(args);
            }
            logger.log(Level.INFO, message);
        }
        Object result = null;
        try {
            result = method.invoke(controllerContext, args);
        } catch (InvocationTargetException ex) {
            logger.severe(ex.getTargetException().getMessage());
        }
        return result;
    }
}
