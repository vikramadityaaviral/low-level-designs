package piece;

import enums.Colour;
import moveset.Moveset;
import validator.movevalidator.MoveValidator;

import java.util.List;

public interface Piece {

    String getCode();

    Colour getColour();

    List<Moveset> getMovesets();

    List<MoveValidator> getMoveValidators();
}
