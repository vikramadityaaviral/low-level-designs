package validator.movevalidator;

import board.Board;
import enums.Colour;
import pojo.Move;

public class ColourWiseMoveDirectionRestriction implements MoveValidator {
    private static ColourWiseMoveDirectionRestriction instance;

    public static ColourWiseMoveDirectionRestriction getInstance() {
        if (instance == null) {
            instance = new ColourWiseMoveDirectionRestriction();
        }
        return instance;
    }

    @Override
    public boolean validate(Move move, Board board) {

        if (move.start().i() < move.end().i()) {
            return move.startPiece().getColour() == Colour.BLACK;
        } else if (move.start().i() > move.end().i()) {
            return move.startPiece().getColour() == Colour.WHITE;
        }
        return false;
    }
}
