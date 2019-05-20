package Commandos;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ExecutableCommand extends Command implements Executable {

    public ExecutableCommand(CommandTypeInfo commandType, Object[] params) {
        super(commandType, params);
    }

    @Override
    public Object execute() throws InvocationTargetException, IllegalAccessException {
        Method method = getCommandType().getMethod();
        Object target = getCommandType().getTarget();
        return method.invoke(target, getParams());
    }
}