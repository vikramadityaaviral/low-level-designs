package turn.rollpolicy;

import dice.Dice;
import player.Player;

public class SingleRollPolicy implements RollPolicy {

    @Override
    public int resolveTurn(Dice dice, Player player) {
        int roll = dice.roll();
        System.out.println(player.getName() + " rolled a "+roll);
        return roll;
    }
}
