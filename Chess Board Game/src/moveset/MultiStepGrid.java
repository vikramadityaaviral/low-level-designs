package moveset;

import pojo.Coordinate;

import java.util.ArrayList;
import java.util.List;

public class MultiStepGrid implements Moveset {
    private static MultiStepGrid instance;

    public static MultiStepGrid getInstance() {
        if (instance == null) {
            instance = new MultiStepGrid();
        }
        return instance;
    }

    @Override
    public List<Coordinate> getTrail(Coordinate start, Coordinate end) {

        int idir = start.i() == end.i()? 0 : start.i() < end.i()? 1 : -1;
        int jdir = start.j() == end.j()? 0 : start.j() < end.j()? 1 : -1;

        if (idir * jdir == 0) {
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
