import java.util.Random;

/**
 * Created by novoselov on 25.09.2015.
 */
public class Wizard extends Creature {
    static private int count;
    boolean isFreezeSpellShoot;
    boolean isSpeedSpellShoot;
    boolean doNotUsingMagic;

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
        if (!skipNextMove && !isDistanceOver) {
            int currSpeed = speed.moveAndGetSpeed();
            isSpeedSpellShoot = doSpell();
            isFreezeSpellShoot = doSpell();
            if (isSpeedSpellShoot) {
                currSpeed = currSpeed * 2;
                isFreezeSpellShoot = false;
            }
            currentDistance = currentDistance + currSpeed;
        }
        if (doNotUsingMagic)
            doNotUsingMagic = !doNotUsingMagic;
    }

    void getInformation() {
        if (isDistanceOver) {
            System.out.printf("%s: \tФИНИШ (%d)\n", name, position);
        } else if (skipNextMove) {
            System.out.printf("%s: \tПройдено дистанции - %d; Движение заморожено чародеем\n", name, currentDistance);
        } else if (isSpeedSpellShoot) {
            System.out.printf("%s: \tПройдено дистанции - %d; Текущая скорость - %d (использовал заклинание скорости)\n", name, currentDistance, speed.getCurrentSpeed() * 2);
        } else if (isFreezeSpellShoot) {
            System.out.printf("%s: \tПройдено дистанции - %d; Текущая скорость - %d (использовал заклинание заморозки)\n", name, currentDistance, speed.getCurrentSpeed());
        } else {
            System.out.printf("%s: \tПройдено дистанции - %d; Текущая скорость - %d\n", name, currentDistance, speed.getCurrentSpeed());
        }
    }
}
