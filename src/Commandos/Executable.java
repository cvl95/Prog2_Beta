package Commandos;
//import java.lang.reflect.InvocationTargetException;

import java.lang.reflect.InvocationTargetException;

public interface Executable {
    Object execute() throws InvocationTargetException, IllegalAccessException;
}
