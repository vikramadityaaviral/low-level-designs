package piece;

import enums.Colour;

public abstract class AbstractPiece implements Piece {

    private Colour colour;
    private String code;

    public AbstractPiece(Colour colour, String code) {
        this.colour = colour;
        this.code = code;
    }

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public Colour getColour() {
        return colour;
    }

    @Override
    public String toString() {
        return colour.getCode()+code;
    }
}
