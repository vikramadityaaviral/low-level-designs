package moveset;

import pojo.Coordinate;

import java.util.List;

public class SingleStepDiagonal implements Moveset{
    private static SingleStepDiagonal instance;

    public static SingleStepDiagonal getInstance() {
        if (instance == null) {
            instance = new SingleStepDiagonal();
        }
        return instance;
    }

    @Override
    public List<Coordinate> getTrail(Coordinate start, Coordinate end) {
        if (Math.abs(start.i() - end.i())==1 && Math.abs(start.j() - end.j())==1) {
            return List.of(start, end);
        } else {
            return null;
        }
    }
}
