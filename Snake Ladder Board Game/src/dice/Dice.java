package dice;

import java.util.Random;

public class Dice {
    private static Random random = new Random();

    private int n;

    public Dice(int n) {
        this.n = n;
    }

    public int roll() {
        return random.nextInt(1, n+1);
    }
}
