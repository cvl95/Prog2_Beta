package Commandos;

import javafx.scene.input.KeyCode;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class KeyCommandType implements CommandTypeInfo {
    private final String name;
    private final KeyCode[] keys;
    private final Method method;
    private final Object target;

    public KeyCommandType(Method method, Object target) {
        this.method = method;
        this.target = target;
        this.name = method.getAnnotation(AsKeyCommand.class).name();
        this.keys = method.getAnnotation(AsKeyCommand.class).keys();
    }

    public static List<KeyCommandType> getCommandTypes(Object target, Class<?> commandInterface){
        List<Method> methods = ReflectionUtils.getMethodsAnnotatedWith(commandInterface, AsKeyCommand.class);
        List<KeyCommandType> commandTypes = new ArrayList<>(methods.size());
        for (Method method : methods) {
            commandTypes.add(new KeyCommandType(method, target));
        }
        commandTypes.sort(Comparator.comparingInt(command -> -command.getKeys().length));
        return commandTypes;
    }

    public KeyCode[] getKeys() {
        return keys;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getHelpText() {
        return null;
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
