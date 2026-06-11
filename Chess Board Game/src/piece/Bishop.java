package piece;

import enums.Colour;
import moveset.Moveset;
import moveset.MultiStepDiagonal;
import validator.movevalidator.KingIsSafe;
import validator.movevalidator.MoveValidator;
import validator.movevalidator.NoFriendlyFire;
import validator.movevalidator.NoPieceBetweenStartAndEnd;

import java.util.List;

public class Bishop extends AbstractPiece {

    private static final List<Moveset> movesets = List.of(
            MultiStepDiagonal.getInstance()
    );

    private static final List<MoveValidator> moveValidators = List.of(
            NoFriendlyFire.getInstance(),
            NoPieceBetweenStartAndEnd.getInstance(),
            KingIsSafe.getInstance()
    );

    public Bishop(Colour colour) {
        super(colour, "B");
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
