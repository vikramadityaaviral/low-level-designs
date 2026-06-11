package validator.movevalidator;

import board.Board;
import pojo.Move;

public class OnlyDiagonalMoveOnKillValid implements MoveValidator{
    private static OnlyDiagonalMoveOnKillValid instance;

    public static OnlyDiagonalMoveOnKillValid getInstance() {
        if (instance == null) {
            instance = new OnlyDiagonalMoveOnKillValid();
        }
        return instance;
    }

    @Override
    public boolean validate(Move move, Board board) {
        if (move.start().j() != move.end().j()) {
            return move.endPiece() != null;
        }
        return true;
    }
}
