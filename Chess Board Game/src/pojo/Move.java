package pojo;

import piece.Piece;

import java.util.List;

public record Move(Piece startPiece, Coordinate start, Piece endPiece, Coordinate end, List<Coordinate> trail) {
}
