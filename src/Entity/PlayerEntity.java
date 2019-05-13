package Entity;

import Movement.XY;

public class PlayerEntity extends Charachter {

    public static final int STUN_DURATION = 3;
    private boolean isStunned;
    private int stunDuration;

    public PlayerEntity(int id, XY position) {
        super(id, position);
    }

    public boolean isStunned() {
        return isStunned;
    }

    public void stun() {
        isStunned = true;
    }

    protected void passTurn() {
        stunDuration++;
        if (stunDuration == STUN_DURATION) {
            isStunned = false;
            stunDuration = 0;
        }
    }

}
