import java.util.Random;

/**
 * Created by novoselov on 25.09.2015.
 */
public class Goblin extends Creature {
    static private int count;

    static {
        count = 1;
    }

    Goblin() {
        this("Неизвестный - " + count);
        count++;
    }

    Goblin(String name) {
        super(name + (" (гоблин)"));
        Random random = new Random();
        minSpeed = 10 + random.nextInt(16);     // 10 <= minimalSpeed <= 25
        maxSpeed = 40 + random.nextInt(16);     // 40 <= maximalSpeed <= 55
    }
}
