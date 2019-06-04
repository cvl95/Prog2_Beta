package Commandos;

import java.lang.reflect.Method;

/**
 * Provides methods required for Command creation and reading
 */

public interface CommandTypeInfo {

    String getName();
    String getHelpText();
    Class<?>[] getParamTypes();
    Method getMethod();
    Object getTarget();
}
