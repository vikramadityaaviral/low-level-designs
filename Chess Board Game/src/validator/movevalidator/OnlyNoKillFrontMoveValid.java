package validator.movevalidator;

import board.Board;
import pojo.Move;

public class OnlyNoKillFrontMoveValid implements MoveValidator{
    private static OnlyNoKillFrontMoveValid instance;
    
    public static OnlyNoKillFrontMoveValid getInstance() {
        if (instance == null) {
            instance = new OnlyNoKillFrontMoveValid();
        }
        return instance;
    }
    
    @Override
    public boolean validate(Move move, Board board) {
        if (move.start().j() == move.end().j()){
            return move.endPiece() == null;
        } else {
            return true;
        }
    }
}
