import java.util.Random;

/**
 * Created by novoselov on 25.09.2015.
 */
public class Skeleton extends Creature {
    static private int count;
    private boolean isCrashed;

    static {
        count = 1;
    }

    Skeleton() {
        this("Unknown - " + count);
        count++;
    }

    Skeleton(String name) {
        super(name + (" (скелет)"));
        if (name.isEmpty()) {
            setName("Unknown #" + count + " (скелет)");
            count++;
        }
        Random random = new Random();
        speed.setMinSpeed(25 + random.nextInt(6));       // 25 <= minimalSpeed <= 30
        speed.setMaxSpeed(70 + random.nextInt(6));      // 70 <= maximalSpeed <= 75
        isCrashed = false;
    }

    public boolean isCrashed() {
        return isCrashed;
    }

    // Скелет очень легкий и обладает хорошей скоростью,
    // однако его кости не из титана, а обычная органика , потому он так же и очень хрупкий,
    // может распасться на множество кусочков во время бега и сойти с дистанции
    void ride() {
        if (!isDistanceOver()) {
            crashSkeleton();
        }
        if (isCrashed) {
            setDistanceOver(true);
        } else if (!skipNextMove() && !isDistanceOver()) {
            setCurrentDistance(getCurrentDistance() + speed.moveAndGetSpeed());
        }
    }

    void crashSkeleton() {
        if (!isCrashed) {
            Random random = new Random();
            isCrashed = (random.nextInt(10) == 9) ? true : false;
        }
    }

    String getInformation() {
        String info = getName() + ": \t";
        if (isCrashed) {
            info += "Скелет не выдержал нагрузки\n";
        } else if (isDistanceOver()) {
            info += "ФИНИШ (" + getPosition() + ")\n";
        } else if (skipNextMove()) {
            info += "Пройдено дистанции - " + getCurrentDistance() + "; Движение заморожено чародеем\n";
        } else {
            info += "Пройдено дистанции - " + getCurrentDistance() + "; Текущая скорость - " + speed.getCurrentSpeed() + "\n";
        }
        System.out.print(info);
        return info;
    }
}
