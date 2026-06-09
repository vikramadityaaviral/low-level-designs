package pojo;

import jumper.AbstractJumper;

public class MoveResult {

    private boolean isTarget = false;
    private int newPosition;
    private AbstractJumper jumper;

    public MoveResult(boolean isTarget, int newPosition, AbstractJumper jumper) {
        this.isTarget = isTarget;
        this.newPosition = newPosition;
        this.jumper = jumper;
    }

    public boolean isTarget() {
        return isTarget;
    }

    public int getNewPosition() {
        return newPosition;
    }

    public AbstractJumper getJumper() {
        return jumper;
    }
}
