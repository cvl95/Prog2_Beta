package Entity;

        import Core.EntityContext;
        import Movement.XY;

public class BadBeast extends Entity {
    int counter = 0;
    int snack = 3;
    public BadBeast(int energy, XY pos) {
        super(-150, pos);
    }

    public void setSnack() {
        this.snack = this.snack--;
    }

    public int getSnack() {
        return snack;
    }

    @Override
    public void nextStep(EntityContext context) {
        if (counter==0)
            context.

                    counter++;
        if (counter == 3)
            counter = 0;
    }
}
