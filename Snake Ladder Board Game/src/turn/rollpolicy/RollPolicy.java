package turn.rollpolicy;

import dice.Dice;
import player.Player;

public interface RollPolicy {

    int resolveTurn(Dice dice, Player player);

}
