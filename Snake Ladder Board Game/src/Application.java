import board.Board;
import dice.Dice;
import game.Game;
import jumper.Ladder;
import jumper.Snake;
import player.Player;
import turn.rollpolicy.SingleRollPolicy;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Application {

    public static void main(String[] args) {

        int n=0;
        int m=0;
        List<Snake> snakes = new ArrayList<>();
        List<Ladder> ladders = new ArrayList<>();
        List<Player> players = new ArrayList<>();

        File file = new File("resources/inputs.txt");

        try (FileInputStream inputStream = new FileInputStream(file);
             Scanner fileScanner = new Scanner(inputStream)) {

            String[] dimensions = fileScanner.nextLine().split(" ");
            n = Integer.valueOf(dimensions[0]);
            m = Integer.valueOf(dimensions[1]);

            int S = Integer.valueOf(fileScanner.nextLine());
            for (int i=0; i<S; i++) {
                String[] snake = fileScanner.nextLine().split(" ");
                snakes.add(new Snake(Integer.valueOf(snake[0]), Integer.valueOf(snake[1])));
            }

            int L = Integer.valueOf(fileScanner.nextLine());
            for (int i=0; i<L; i++) {
                String[] ladder = fileScanner.nextLine().split(" ");
                ladders.add(new Ladder(Integer.valueOf(ladder[0]), Integer.valueOf(ladder[1])));
            }

            int P = Integer.valueOf(fileScanner.nextLine());
            for (int i=0; i<P; i++) {
                String playerName = fileScanner.nextLine();
                players.add(new Player(playerName));
            }

        } catch (FileNotFoundException e) {
            System.err.println("File not found");
        } catch (Exception e) {
            e.printStackTrace();
        }

        Board board = new Board(snakes, ladders, n, m);
        Dice dice = new Dice(6);
        Game game = new Game(board, players, dice, new SingleRollPolicy());
        game.run();
    }
}
