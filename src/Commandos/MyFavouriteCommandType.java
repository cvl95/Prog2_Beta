package Commandos;

public enum MyFavouriteCommandType implements CommandTypeInfo {
    HELP,
    EXIT,
    ADDI,
    ADDF,
    ECHO;

    @Override
    public String getName() {
        return null;
    }

    @Override
    public String getHelpText() {
        return null;
    }

    @Override
    public Class<?>[] getParamTypes() {
        return new Class[0];
    }
}
