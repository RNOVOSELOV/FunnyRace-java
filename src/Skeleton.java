import java.util.Random;

/**
 * Created by novoselov on 25.09.2015.
 */
public class Skeleton extends Creature {
    static private int count;

    static {
        count = 1;
    }

    Skeleton() {
        this("Неизвестный - " + count);
        count++;
    }

    Skeleton(String name) {
        super(name + (" (скелет)"));
        Random random = new Random();
        minSpeed = 5 + random.nextInt(6);       // 5 <= minimalSpeed <= 10
        maxSpeed = 70 + random.nextInt(6);      // 70 <= maximalSpeed <= 75
    }
}
