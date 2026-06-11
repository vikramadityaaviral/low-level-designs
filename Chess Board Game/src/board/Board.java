package board;

import enums.Colour;
import piece.Piece;
import pojo.Coordinate;

import java.util.EnumMap;
import java.util.HashSet;
import java.util.Set;

public class Board {

    private int n;
    private int m;

    private Piece[][] board;
    private StringBuilder sb = new StringBuilder();
    private String header;

    private EnumMap<Colour, Set<Coordinate>> colourLocation;

    public Board(int n, int m) {
        this.n = n;
        this.m = m;
        this.board = new Piece[n][m];

        sb.append("    ");
        for (int j=0; j<m; j++) {
            sb.append(String.format("%-4s", String.valueOf((char) ('a'+j))));
        }
        sb.append('\n');
        this.header = sb.toString();

        this.colourLocation = new EnumMap<>(Colour.class);
        this.colourLocation.put(Colour.BLACK, new HashSet<>());
        this.colourLocation.put(Colour.WHITE, new HashSet<>());
    }

    @Override
    public String toString() {
        sb.setLength(0);
        sb.append(header);
        for (int i=0; i<n; i++) {
            sb.append(String.format("%-4s", (n-i)));
            for (int j=0; j<m; j++) {
                sb.append(String.format("%-4s", board[i][j] == null? "--" : board[i][j]));
            }
            sb.append(String.format("%-4s", (n-i)));
            sb.append('\n');
        }
        sb.append(header);
        return sb.toString();
    }

    public int getN() {
        return n;
    }

    public int getM() {
        return m;
    }

    public Piece view(Coordinate coordinate) {
        return board[coordinate.i()][coordinate.j()];
    }

    public void place(Coordinate coordinate, Piece piece) {
        if (piece != null)
            colourLocation.get(piece.getColour()).add(coordinate);
        board[coordinate.i()][coordinate.j()] = piece;
    }

    public Piece remove(Coordinate coordinate) {
        Piece p = board[coordinate.i()][coordinate.j()];
        board[coordinate.i()][coordinate.j()] = null;
        if (p != null) {
            colourLocation.get(p.getColour()).remove(coordinate);
        }
        return p;
    }

    public Set<Coordinate> getAllPieceLocationByColour(Colour colour) {
        return colourLocation.get(colour);
    }
}
