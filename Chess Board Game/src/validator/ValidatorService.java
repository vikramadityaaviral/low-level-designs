package validator;

import board.Board;
import enums.Colour;
import piece.Piece;
import pojo.Coordinate;
import pojo.Move;
import validator.movevalidator.KingIsSafe;
import validator.movevalidator.MoveValidator;

import java.util.List;
import java.util.Set;

public class ValidatorService {
    private static ValidatorService instance;

    public static ValidatorService getInstance() {
        if (instance == null) {
            instance = new ValidatorService();
        }
        return instance;
    }

    private static final Set<Class> secondaryValidationExcludedValidators = Set.of(KingIsSafe.class);

    public boolean validate(Colour turn, Coordinate start, Coordinate end, Board board, boolean isSecondary) {
        Piece startPiece = board.view(start);

        if (startPiece == null || startPiece.getColour() != turn) {
            return false;
        }

        List<Coordinate> trail = startPiece.getMovesets().stream()
                .map(moveset -> moveset.getTrail(start, end))
                .filter(t -> t != null)
                .findFirst()
                .orElse(null);

        if (trail == null) {
            return false;
        }

        Move move = new Move(startPiece, start, board.view(end), end, trail);

        for (MoveValidator moveValidator : startPiece.getMoveValidators()) {
            if (isSecondary && secondaryValidationExcludedValidators.contains(moveValidator.getClass())) {
                continue;
            }
            if (!moveValidator.validate(move, board)) {
                return false;
            }
        }

        return true;
    }
}
