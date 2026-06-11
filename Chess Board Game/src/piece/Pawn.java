package piece;

import enums.Colour;
import moveset.Moveset;
import moveset.SingleStepDiagonal;
import moveset.SingleStepVertical;
import validator.movevalidator.*;

import java.util.List;

public class Pawn extends AbstractPiece {

    private static final List<Moveset> movesets = List.of(
            SingleStepDiagonal.getInstance(),
            SingleStepVertical.getInstance()
    );

    private static final List<MoveValidator> moveValidators = List.of(
            NoFriendlyFire.getInstance(),
            ColourWiseMoveDirectionRestriction.getInstance(),
            OnlyDiagonalMoveOnKillValid.getInstance(),
            OnlyNoKillFrontMoveValid.getInstance(),
            KingIsSafe.getInstance()
    );

    public Pawn(Colour colour) {
        super(colour, "P");
    }

    @Override
    public List<Moveset> getMovesets() {
        return movesets;
    }

    @Override
    public List<MoveValidator> getMoveValidators() {
        return moveValidators;
    }
}
