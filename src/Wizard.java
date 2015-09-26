import java.util.Random;

/**
 * Created by novoselov on 25.09.2015.
 */
public class Wizard extends Creature {
    static private int count;

    static {
        count = 1;
    }

    Wizard() {
        this("Неизвестный - " + count);
        count++;
    }

    Wizard(String name) {
        super(name + (" (маг)"));
        Random random = new Random();
        minSpeed = 10 + random.nextInt(16);     // 10 <= minimalSpeed <= 25
        maxSpeed = 25 + random.nextInt(16);     // 25 <= maximalSpeed <= 40
    }

    // C вероятностью 25% колдун может колдовать и прибалять себе скорость на круге в два раза
    void ride() {
        Random random = new Random();
        boolean isWiz = (random.nextInt(4) == 1) ? true : false;
        currentSpeed = minSpeed + random.nextInt(maxSpeed - minSpeed);
        if (isWiz) {
            currentSpeed = makeIncreaseSpeedSpell(currentSpeed);
        }
        currentDistance = currentDistance + currentSpeed;
    }

    int makeIncreaseSpeedSpell(int speed) {
        return speed * 2;
    }
}
