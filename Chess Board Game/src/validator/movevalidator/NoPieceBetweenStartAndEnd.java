package validator.movevalidator;

import board.Board;
import pojo.Coordinate;
import pojo.Move;

public class NoPieceBetweenStartAndEnd implements MoveValidator{
    private static NoPieceBetweenStartAndEnd instance;
    
    public static NoPieceBetweenStartAndEnd getInstance() {
        if (instance == null) {
            instance = new NoPieceBetweenStartAndEnd();
        }
        return instance;
    }
    
    @Override
    public boolean validate(Move move, Board board) {
        for (int i=1; i<move.trail().size()-1; i++) {
            if (board.view(move.trail().get(i)) != null) {
                return false;
            }
        }
        return true;
    }
}
