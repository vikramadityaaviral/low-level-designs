package board;

import jumper.AbstractJumper;
import jumper.Ladder;
import jumper.Snake;
import pojo.MoveResult;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Board {

    private Map<Integer, Snake> snakeMap;
    private Map<Integer, Ladder> ladderMap;
    private int n;
    private int m;
    private int target;

    public Board(List<Snake> snakes, List<Ladder> ladders, int n, int m) {
        this.snakeMap = snakes.stream().collect(Collectors.toMap(Snake::getStart, Function.identity()));
        this.ladderMap = ladders.stream().collect(Collectors.toMap(Ladder::getStart, Function.identity()));
        this.n = n;
        this.m = m;
        this.target = n * m;
    }

    public MoveResult nextPosition(int currentPosition, int steps) {
        int nextPosition = currentPosition + steps;
        if (nextPosition > target) {
            return new MoveResult(false, currentPosition, null);
        } else if (nextPosition == target) {
            return new MoveResult(true, target, null);
        }


        AbstractJumper jumper = null;
        if (snakeMap.containsKey(nextPosition)) {
            jumper = snakeMap.get(nextPosition);
        } else if(ladderMap.containsKey(nextPosition)) {
            jumper = ladderMap.get(nextPosition);
        }

        return new MoveResult(false, nextPosition, jumper);
    }
}
