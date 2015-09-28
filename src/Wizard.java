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
        if (name.isEmpty())
        {
            this.name = "Неизвестный - " + count + " (маг)";
            count ++;
        }
        Random random = new Random();
        speed.setMinSpeed(10 + random.nextInt(16));     // 10 <= minimalSpeed <= 25
        speed.setMaxSpeed(25 + random.nextInt(16));     // 25 <= maximalSpeed <= 40
    }

    // Колдун - добрый волшебник, однако его добро ограничивается тем что ничего плохого он не делает окружающим его товарищам,
    // однако с вероятностью 25% маг прибалять себе скорость в два раза
    void ride() {
        if (skipNextMove) {
            skipNextMove = false;
            return;
        }
        int currSpeed = speed.moveAndGetSpeed();
        if (doSpell())
            currSpeed = currSpeed * 2;
        currentDistance = currentDistance + currSpeed;
    }

    boolean doSpell() {
        Random random = new Random();
        boolean isWiz = (random.nextInt(4) == 1) ? true : false;
        return isWiz;
    }
}
