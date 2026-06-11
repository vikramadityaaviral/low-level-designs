package moveset;

import pojo.Coordinate;

import java.util.List;

public class SingleStepHorizontal implements Moveset{
    private static SingleStepHorizontal instance;

    public static SingleStepHorizontal getInstance() {
        if (instance == null) {
            instance = new SingleStepHorizontal();
        }
        return instance;
    }

    @Override
    public List<Coordinate> getTrail(Coordinate start, Coordinate end) {
        if (start.i() == end.i() && Math.abs(start.j() - end.j())==1) {
            return List.of(start, end);
        } else {
            return null;
        }
    }
}
