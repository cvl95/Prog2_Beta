package Commandos;

import java.lang.reflect.Method;

public interface CommandTypeInfo {

    String getName();
    String getHelpText();
    Class<?>[] getParamTypes();
    Method getMethod();
    Object getTarget();
}
