package moveset;

import pojo.Coordinate;

import java.util.List;

public class SingleStepVertical implements Moveset {
    private static SingleStepVertical instance;

    public static SingleStepVertical getInstance() {
        if (instance == null) {
            instance = new SingleStepVertical();
        }
        return instance;
    }

    @Override
    public List<Coordinate> getTrail(Coordinate start, Coordinate end) {
        if (start.j() == end.j() && Math.abs(start.i() - end.i())==1) {
            return List.of(start, end);
        } else {
            return null;
        }
    }
}
