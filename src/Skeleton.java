import java.util.Random;

/**
 * Created by novoselov on 25.09.2015.
 */
public class Skeleton extends Creature {
    static private int count;
    boolean isCrashed;

    static {
        count = 1;
    }

    Skeleton() {
        this("Unknown - " + count);
        count++;
    }

    Skeleton(String name) {
        super(name + (" (скелет)"));
        if (name.isEmpty())
        {
            this.name = "Unknown #" + count + " (скелет)";
            count ++;
        }
        Random random = new Random();
        speed.setMinSpeed(25 + random.nextInt(6));       // 25 <= minimalSpeed <= 30
        speed.setMaxSpeed(70 + random.nextInt(6));      // 70 <= maximalSpeed <= 75
        isCrashed = false;
    }

    // Скелет очень легкий и обладает хорошей скоростью,
    // однако его кости не из титана, а обычная органика , потому он так же и очень хрупкий,
    // может распасться на множество кусочков во время бега и сойти с дистанции
    void ride() {
        crashSkeleton();
        if (isCrashed) {
            isDistanceOver = true;
        } else if (!skipNextMove) {
            currentDistance = currentDistance + speed.moveAndGetSpeed();
        }
    }

    void crashSkeleton() {
        if (!isCrashed) {
            Random random = new Random();
            isCrashed = (random.nextInt(10) == 9) ? true : false;
        }
    }

    void getInformation() {
        if (isCrashed) {
            System.out.printf("%s: \tСкелет не выдержал нагрузки, распался на мелкие кусочки\n", name);
        } else if (isDistanceOver) {
            System.out.printf("%s: \tФИНИШ\n", name);
        } else if (skipNextMove) {
            System.out.printf("%s: \tДвижение заморожено чародеем\n", name);
        }else {
            System.out.printf("%s: \tПройдено дистанции - %d; Текущая скорость - %d\n", name, currentDistance, speed.getCurrentSpeed());
        }
    }
}
