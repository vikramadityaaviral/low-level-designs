package moveset;

import pojo.Coordinate;

import java.util.List;

public interface Moveset {

    List<Coordinate> getTrail(Coordinate start, Coordinate end);
}
