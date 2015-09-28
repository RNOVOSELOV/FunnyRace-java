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
        this("Unknown - " + count);
        count++;
    }

    Wizard(String name) {
        super(name + (" (маг)"));
        if (name.isEmpty()) {
            this.name = "Unknown #" + count + " (маг)";
            count++;
        }
        Random random = new Random();
        speed.setMinSpeed(10 + random.nextInt(16));     // 10 <= minimalSpeed <= 25
        speed.setMaxSpeed(25 + random.nextInt(16));     // 25 <= maximalSpeed <= 40
    }

    boolean doSpell() {
        Random random = new Random();
        boolean isWiz = (random.nextInt(4) == 1) ? true : false;
        return isWiz;
    }

    // с вероятностью 25% маг прибалять себе скорость в два раза
    // с вероятностью 25% замораживает впередиидущих юнитов на один ход
    void ride() {
        if (skipNextMove) {
            skipNextMove = false;
            return;
        }
        int currSpeed = speed.moveAndGetSpeed();
        boolean isSpeedSellDone = doSpell();
        boolean isFreezeSpeelDone = doSpell();
        if (isSpeedSellDone) {
            currSpeed = currSpeed * 2;
        }
        if (isFreezeSpeelDone) {
            makeFreeze();
        }
        currentDistance = currentDistance + currSpeed;
    }

    private void makeFreeze() {

    }
}
