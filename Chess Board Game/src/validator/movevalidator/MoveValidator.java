package validator.movevalidator;

import board.Board;
import pojo.Move;

public interface MoveValidator {

    boolean validate(Move move, Board board);
}
