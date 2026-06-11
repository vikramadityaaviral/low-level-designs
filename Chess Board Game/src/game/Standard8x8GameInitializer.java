package game;

import board.Board;
import enums.Colour;
import piece.*;
import pojo.Coordinate;

public class Standard8x8GameInitializer implements GameInitializer {

    @Override
    public Board getNewBoard() {
        Board board = new Board(8, 8);

        Rook blackRook = new Rook(Colour.BLACK);
        board.place(new Coordinate(0, 0), blackRook);
        board.place(new Coordinate(0, 7), blackRook);

        Knight blackKnight = new Knight(Colour.BLACK);
        board.place(new Coordinate(0, 1), blackKnight);
        board.place(new Coordinate(0, 6), blackKnight);

        Bishop blackBishop = new Bishop(Colour.BLACK);
        board.place(new Coordinate(0, 2), blackBishop);
        board.place(new Coordinate(0, 5), blackBishop);

        Queen blackQueen = new Queen(Colour.BLACK);
        board.place(new Coordinate(0, 3), blackQueen);

        King blackKing = new King(Colour.BLACK);
        board.place(new Coordinate(0, 4), blackKing);

        Pawn blackPawn = new Pawn(Colour.BLACK);
        for (int j=0; j<8; j++) {
            board.place(new Coordinate(1, j), blackPawn);
        }



        Rook whiteRook = new Rook(Colour.WHITE);
        board.place(new Coordinate(7, 0), whiteRook);
        board.place(new Coordinate(7, 7), whiteRook);

        Knight whiteKnight = new Knight(Colour.WHITE);
        board.place(new Coordinate(7, 1), whiteKnight);
        board.place(new Coordinate(7, 6), whiteKnight);

        Bishop whiteBishop = new Bishop(Colour.WHITE);
        board.place(new Coordinate(7, 2), whiteBishop);
        board.place(new Coordinate(7, 5), whiteBishop);

        Queen whiteQueen = new Queen(Colour.WHITE);
        board.place(new Coordinate(7, 3), whiteQueen);

        King whiteKing = new King(Colour.WHITE);
        board.place(new Coordinate(7, 4), whiteKing);

        Pawn whitePawn = new Pawn(Colour.WHITE);
        for (int j=0; j<8; j++) {
            board.place(new Coordinate(6, j), whitePawn);
        }

        return board;
    }
}
