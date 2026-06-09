package turn;

import player.Player;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class TurnController {

    public static class PlayerState {
        private final Player player;
        private int position;

        public PlayerState(Player player) {
            this.player = player;
            this.position = 0;
        }

        public Player getPlayer() { return player; }
        public int getPosition() { return position; }
        public void setPosition(int position) { this.position = position; }
    }

    private Deque<PlayerState> activeQueue = new ArrayDeque<>();
    private final List<PlayerState> leaderboard = new ArrayList<>();

    public TurnController(List<Player> players) {
        for (Player player: players) {
            this.activeQueue.offerLast(new PlayerState(player));
        }
    }

    public PlayerState getNextPlayer() {
        return activeQueue.pollFirst();
    }

    public void requeue(PlayerState ps) {
        activeQueue.offerLast(ps);
    }

    public void eliminate(PlayerState ps) {
        leaderboard.add(ps);

        // auto elimination for the last player
        if (remainingCount() ==1) {
            leaderboard.add(activeQueue.pollFirst());
        }
    }

    public boolean hasActivePlayers() {
        return !activeQueue.isEmpty();
    }

    public int remainingCount() {
        return activeQueue.size();
    }

    public List<PlayerState> getLeaderboard() {
        return List.copyOf(leaderboard);
    }
}
