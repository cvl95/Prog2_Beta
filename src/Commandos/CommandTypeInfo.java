package Commandos;

public interface CommandTypeInfo {

    String getName();
    String getHelpText();
    Class<?>[] getParamTypes();
}
