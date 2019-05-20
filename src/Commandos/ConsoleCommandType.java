package Commandos;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ConsoleCommandType implements CommandTypeInfo {
    private final String name;
    private final String helpText;
    private final int order;
    private final Method method;
    private final Object target;

    public ConsoleCommandType(Method method, Object target) {
        this.method = method;
        this.target = target;
        this.name = method.getAnnotation(AsConsoleCommand.class).name();
        this.helpText = method.getAnnotation(AsConsoleCommand.class).helpText();
        this.order = method.getAnnotation(AsConsoleCommand.class).order();
    }

    public static List<ConsoleCommandType> getCommandTypes(Object target, Class<?> commandInterface) {
        List<Method> methods = ReflectionUtils.getMethodsAnnotatedWith(commandInterface, AsConsoleCommand.class);
        List<ConsoleCommandType> commandTypes = new ArrayList<>(methods.size());
        for (Method method : methods) {
            commandTypes.add(new ConsoleCommandType(method, target));
        }
        commandTypes.sort(Comparator.comparingInt(command -> command.getOrder()));
        return commandTypes;
    }

    public String getName() {
        return name;
    }

    public String getHelpText() {
        return helpText;
    }

    public int getOrder() {
        return order;
    }

    @Override
    public Class<?>[] getParamTypes() {
        return method.getParameterTypes();
    }

    @Override
    public Method getMethod() {
        return method;
    }

    @Override
    public Object getTarget() {
        return target;
    }

}