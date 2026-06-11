package game;

import board.Board;
import enums.Colour;
import piece.Piece;
import pojo.Coordinate;
import validator.ValidatorService;

import java.util.Scanner;

public class Game {

    private GameInitializer gameInitializer;
    private ValidatorService validatorService;
    private Board board;
    private Scanner scanner = new Scanner(System.in);

    public Game(GameInitializer gameInitializer, ValidatorService validatorService) {
        this.gameInitializer = gameInitializer;
        this.validatorService = validatorService;
        this.board = gameInitializer.getNewBoard();
    }

    public void run() {
        boolean whitesTurn = true;
        boolean running = true;
        Colour turn = Colour.WHITE;
        System.out.println(board);

        while (running) {
            System.out.println(turn + "'s turn");

            boolean moved = false;
            while (!moved) {
                String command = scanner.nextLine();
                if (command.equals("")) {
                    continue;
                }
                if (command.equals("exit")) {
                    running = false;
                    System.out.println(turn + " surrenders");
                    break;
                } else {
                    String[] locs = command.split(" ");
                    Coordinate start = getCoordinateFromInput(locs[0], board.getN(), board.getM());
                    Coordinate end = getCoordinateFromInput(locs[1], board.getN(), board.getM());
                    if (start != null && end != null
                            && !start.equals(end)
                            && validatorService.validate(turn, start, end, board, false)
                    ) {
                        Piece p1 = board.remove(start);
                        Piece p2 = board.remove(end);
                        board.place(end, p1);
                        moved = true;
                    } else {
                        System.out.println("Invalid move");
                    }
                }
            }

            System.out.println(board);

            whitesTurn = !whitesTurn;
            turn = whitesTurn? Colour.WHITE : Colour.BLACK;
        }
        System.out.println(turn + " wins!");
    }

    private Coordinate getCoordinateFromInput(String s, int n, int m) {
        try {
            int j = s.charAt(0) - 'a';
            int i = n - Integer.valueOf(s.substring(1));
            if (j >= m || i < 0) {
                return null;
            }
            return new Coordinate(i, j);
        } catch (Exception e) {
            return null;
        }
    }
}
