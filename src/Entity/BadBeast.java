package Entity;

        import Core.EntityContext;
        import Movement.XY;

        import java.util.*;

public class BadBeast extends Beast {
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

        List surround = context.checkSuroundings(this);

        if (counter==0)
            context.;

        counter++;
        if (counter == 3)
            counter = 0;
    }
}
