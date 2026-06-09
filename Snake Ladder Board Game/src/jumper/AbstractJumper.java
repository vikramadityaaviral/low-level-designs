package jumper;

public abstract class AbstractJumper {

    private int start;
    private int end;

    public AbstractJumper(int start, int end) {
        this.end = end;
        this.start = start;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getEnd() {
        return end;
    }

    public void setEnd(int end) {
        this.end = end;
    }
}
