package moveset;

import pojo.Coordinate;

import java.util.ArrayList;
import java.util.List;

public class MultiStepDiagonal implements Moveset{
    private static MultiStepDiagonal instance;

    public static MultiStepDiagonal getInstance() {
        if (instance == null) {
            instance = new MultiStepDiagonal();
        }
        return instance;
    }

    @Override
    public List<Coordinate> getTrail(Coordinate start, Coordinate end) {
        int startDiff = start.i() - start.j();
        int endDiff = end.i() - end.j();

        int startSum = start.i() + start.j();
        int endSum = end.i() + end.j();

        int idir = start.i() == end.i()? 0 : start.i() < end.i()? 1 : -1;
        int jdir = start.j() == end.j()? 0 : start.j() < end.j()? 1 : -1;

        if (startSum == endSum || startDiff == endDiff) {
            List<Coordinate> trail = new ArrayList<>();
            int i = start.i() + idir;
            int j = start.j() + jdir;

            trail.add(start);
            while (!(i == end.i() && j == end.j())) {
                trail.add(new Coordinate(i, j));
                i += idir;
                j += jdir;
            }
            trail.add(end);
            return trail;
        } else {
            return null;
        }
    }
}
