package validator.movevalidator;

import board.Board;
import enums.Colour;
import piece.King;
import pojo.Coordinate;
import pojo.Move;
import validator.ValidatorService;

import java.util.Set;

public class KingIsSafe implements MoveValidator{
    private static KingIsSafe instance;

    public static KingIsSafe getInstance() {
        if (instance == null) {
            instance = new KingIsSafe(ValidatorService.getInstance());
        }
        return instance;
    }

    private final ValidatorService validatorService;

    public KingIsSafe(ValidatorService validatorService) {
        this.validatorService = validatorService;
    }

    @Override
    public boolean validate(Move move, Board board) {

        board.remove(move.start());
        board.remove(move.end());
        board.place(move.end(),move.startPiece());

        Colour colour = move.startPiece().getColour();
        Coordinate currentKing = board.getAllPieceLocationByColour(colour).stream()
                .filter(loc -> board.view(loc) instanceof King)
                .findFirst()
                .get();

        Colour enemyColour = colour == Colour.BLACK? Colour.WHITE : Colour.BLACK;
        Set<Coordinate> enemyCoords = board.getAllPieceLocationByColour(enemyColour);

        boolean safe = true;
        for (Coordinate enemyCoord: enemyCoords) {
            if (validatorService.validate(enemyColour, enemyCoord, currentKing, board, true)) {
                safe = false;
                break;
            }
        }


        board.remove(move.end());
        board.place(move.end(), move.endPiece());
        board.place(move.start(), move.startPiece());

        return safe;
    }
}
