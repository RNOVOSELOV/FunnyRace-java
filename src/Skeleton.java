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
        if (isCrashed) {
            return;
        }
        if (skipNextMove) {
            skipNextMove = false;
            return;
        }
        currentDistance = currentDistance + speed.moveAndGetSpeed();
        checkIsCrash();
    }

    void checkIsCrash () {
        Random random = new Random();
        isCrashed = (random.nextInt(5) == 1) ? true : false;
    }
}
