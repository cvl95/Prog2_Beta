package Entity;

public class ID {

    private static int id = 0;

    public static int getId() {
        setID();
        return ID.id;
    }
    private static void setID(){
        ID.id ++;
    }

}
