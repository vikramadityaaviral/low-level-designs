package piece;

import enums.Colour;
import moveset.*;
import validator.movevalidator.KingIsSafe;
import validator.movevalidator.MoveValidator;
import validator.movevalidator.NoFriendlyFire;

import java.util.List;

public class Knight extends AbstractPiece {

    private static final List<Moveset> movesets = List.of(
            LTeleport.getInstance()
    );

    private static final List<MoveValidator> moveValidators = List.of(
            NoFriendlyFire.getInstance(),
            KingIsSafe.getInstance()
    );

    public Knight(Colour colour) {
        super(colour, "N");
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
