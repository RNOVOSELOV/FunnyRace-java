import java.util.Random;

/**
 * Created by novoselov on 25.09.2015.
 */
public class Wizard extends Creature {
    static private int count;
    private boolean isFreezeSpellShoot;
    private boolean isSpeedSpellShoot;
    private boolean doNotUsingMagic;

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
            name = "Unknown #" + count + " (маг)";
            count++;
        }
        Random random = new Random();
        speed.setMinSpeed(10 + random.nextInt(16));     // 10 <= minimalSpeed <= 25
        speed.setMaxSpeed(25 + random.nextInt(16));     // 25 <= maximalSpeed <= 40
        doNotUsingMagic = true;
    }

    public boolean isFreezeSpellShoot() {
        return isFreezeSpellShoot;
    }

    boolean doSpell() {
        if (doNotUsingMagic) {
            return false;
        }
        Random random = new Random();
        boolean isWiz = (random.nextInt(4) == 1) ? true : false;
        return isWiz;
    }

    // с вероятностью 25% маг прибалять себе скорость в два раза
    // с вероятностью 25% замораживает впередиидущих юнитов на один ход
    void ride() {
        if (!skipNextMove() && !isDistanceOver()) {
            int currSpeed = speed.moveAndGetSpeed();
            isSpeedSpellShoot = doSpell();
            isFreezeSpellShoot = doSpell();
            if (isSpeedSpellShoot) {
                currSpeed = currSpeed * 2;
                isFreezeSpellShoot = false;
            }
            setCurrentDistance(getCurrentDistance() + currSpeed);
        }
        if (doNotUsingMagic)
            doNotUsingMagic = !doNotUsingMagic;
    }

    String getInformation() {
        String info = getName() + ": \t";
        if (isDistanceOver()) {
            info += "ФИНИШ (" + getPosition() + ")\n";
        } else if (skipNextMove()) {
            info += "Пройдено дистанции - " + getCurrentDistance() + "; Движение заморожено чародеем\n";
        } else if (isSpeedSpellShoot) {
            info += "Пройдено дистанции - " + getCurrentDistance() + "; Текущая скорость - " + speed.getCurrentSpeed() * 2 + " (использовал заклинание скорости)\n";
        } else if (isFreezeSpellShoot) {
            info += "Пройдено дистанции - " + getCurrentDistance() + "; Текущая скорость - " + speed.getCurrentSpeed() + " (использовал заклинание заморозки)\n";
        } else {
            info += "Пройдено дистанции - " + getCurrentDistance() + "; Текущая скорость - " + speed.getCurrentSpeed() + "\n";
        }
        System.out.print(info);
        return info;
    }
}
