package game;

import board.Board;
import dice.Dice;
import jumper.Snake;
import player.Player;
import pojo.MoveResult;
import turn.TurnController;
import turn.rollpolicy.RollPolicy;

import java.util.List;

public class Game {

    private Board board;
    private TurnController turnController;
    private Dice dice;
    private RollPolicy rollPolicy;


    public Game(Board board, List<Player> players, Dice dice, RollPolicy rollPolicy) {
        this.board = board;
        this.dice = dice;
        this.turnController = new TurnController(players);
        this.rollPolicy = rollPolicy;
    }

    public void run() {
        while (turnController.hasActivePlayers()) {
            TurnController.PlayerState ps = turnController.getNextPlayer();
            Player player = ps.getPlayer();
            int currentPosition = ps.getPosition();

            int roll = rollPolicy.resolveTurn(dice, player);

            if (roll <= 0) {
                turnController.requeue(ps);
                continue;
            }

            MoveResult result = board.nextPosition(currentPosition, roll);

            System.out.println(player.getName() + " moved from " + currentPosition +" to "+ result.getNewPosition());

            if (result.getJumper() != null) {
                String messageKey = (result.getJumper() instanceof Snake)? " moved down a snake " : " moved up a ladder";
                System.out.println(player.getName() + messageKey + " from " + result.getJumper().getStart() + " to " + result.getJumper().getEnd());
                ps.setPosition(result.getJumper().getEnd());
            } else {
                ps.setPosition(result.getNewPosition());
            }

            if (result.isTarget()) {
                turnController.eliminate(ps);
            } else {
                turnController.requeue(ps);
            }
        }

        List<TurnController.PlayerState> finalLeaderBoard = turnController.getLeaderboard();
        System.out.println(finalLeaderBoard.get(0).getPlayer().getName() + " wins the game!");
        System.out.println("Rankings:");
        int i = 1;
        for (TurnController.PlayerState p: finalLeaderBoard) {
            System.out.println("# "+i+" : "+p.getPlayer().getName());
            i++;
        }
    }
}
