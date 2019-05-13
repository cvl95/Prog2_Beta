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
     /*   if (counter==0) {
            Entity[] surround = context.checkSuroundings(this);
            XY direction = null;
            for(Entity entity : surround) {

                if (entity instanceof Squirel) {
                    int x = entity.getPosition().getX() - this.getPosition().getX();
                    int y = entity.getPosition().getY() - this.getPosition().getY();
                    direction = this.culcRun(x,y) ;
                }

            }
            if (direction != null)
                context.tryMove(this,direction);
            else
                context.tryMove(this,this.getPosition().getNewPosition());
        }
        counter++;
        if (counter == 3)
            counter = 0;*/
    }
}
