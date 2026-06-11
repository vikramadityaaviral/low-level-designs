package validator.movevalidator;

import board.Board;
import pojo.Move;

public class NoFriendlyFire implements MoveValidator {
    private static NoFriendlyFire instance;

    public static NoFriendlyFire getInstance() {
        if (instance == null) {
            instance = new NoFriendlyFire();
        }
        return instance;
    }

    @Override
    public boolean validate(Move move, Board board) {
        return move.endPiece() == null || move.startPiece().getColour() != move.endPiece().getColour();
    }
}
