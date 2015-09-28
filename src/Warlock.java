import java.util.Random;

/**
 * Created by Роман on 26.09.2015.
 */
public class Warlock extends Creature {
    static private int count;

    static {
        count = 1;
    }

    Warlock() {
        this("Неизвестный - " + count);
        count++;
    }

    Warlock(String name) {
        super(name + (" (чернокнижник)"));
        if (name.isEmpty())
        {
            this.name = "Неизвестный - " + count + " (чернокнижник)";
            count ++;
        }
        Random random = new Random();
        speed.setMinSpeed(10 + random.nextInt(16));      // 10 <= minimalSpeed <= 25
        speed.setMaxSpeed(25 + random.nextInt(16));     // 25 <= maximalSpeed <= 40
    }

    // Чернокнижник - подлянщик, делает все свои темные делишки за спиной коллег
    // С вероятностью 25% ставит подножку впередиидущим игрокам (они пропускают следущий ход)
    void ride() {
        if (skipNextMove) {
            skipNextMove = false;
            return;
        }
        Random random = new Random();
        boolean isWiz = (random.nextInt(4) == 1) ? true : false;
        currentDistance = currentDistance + speed.moveAndGetSpeed();
        if (isWiz) {
            giveBandwagon();
        }
    }

    void giveBandwagon() {

    }
}
