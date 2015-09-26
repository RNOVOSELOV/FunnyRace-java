import java.util.Random;

/**
 * Created by ����� on 26.09.2015.
 */
public class Warlock extends Creature {
    static private int count;

    static {
        count = 1;
    }

    Warlock() {
        this("����������� - " + count);
        count++;
    }

    Warlock(String name) {
        super(name + (" (������������)"));
        Random random = new Random();
        speed.setMinSpeed(10 + random.nextInt(16));      // 10 <= minimalSpeed <= 25
        speed.setMaxSpeed(25 + random.nextInt(16));     // 25 <= maximalSpeed <= 40
    }

    // C ������������ 25% ������������ ������ �������� ������� ������ ������, ��� ����������� ��� �� �������� ��������
    void ride() {
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
