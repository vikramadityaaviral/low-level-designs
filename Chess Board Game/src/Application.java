import game.Game;
import game.GameInitializer;
import game.Standard8x8GameInitializer;
import validator.ValidatorService;

public class Application {

    public static void main(String[] args) {

        GameInitializer gameInitializer = new Standard8x8GameInitializer();
        Game game = new Game(gameInitializer, ValidatorService.getInstance());
        game.run();
    }
}
