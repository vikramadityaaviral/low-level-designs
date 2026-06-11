package piece;

import enums.Colour;
import moveset.Moveset;
import moveset.SingleStepDiagonal;
import moveset.SingleStepHorizontal;
import moveset.SingleStepVertical;
import validator.movevalidator.KingIsSafe;
import validator.movevalidator.MoveValidator;
import validator.movevalidator.NoFriendlyFire;

import java.util.List;

public class King extends AbstractPiece {

    private static final List<Moveset> movesets = List.of(
            SingleStepDiagonal.getInstance(),
            SingleStepHorizontal.getInstance(),
            SingleStepVertical.getInstance()
    );

    private static final List<MoveValidator> moveValidators = List.of(
            NoFriendlyFire.getInstance(),
            KingIsSafe.getInstance()
    );

    public King(Colour colour) {
        super(colour, "K");
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
