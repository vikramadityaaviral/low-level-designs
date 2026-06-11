package moveset;

import pojo.Coordinate;

import java.util.List;

public class LTeleport implements Moveset{
    private static LTeleport INSTANCE;

    public static LTeleport getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new LTeleport();
        }
        return INSTANCE;
    }

    @Override
    public List<Coordinate> getTrail(Coordinate start, Coordinate end) {
        int di = Math.abs(start.i() - end.i());
        int dj = Math.abs(start.j() - end.j());

        if ((di == 1 && dj == 2) || (di == 2 && dj ==1)) {
            return List.of(start, end);
        } else {
            return null;
        }
    }
}
