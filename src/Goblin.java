import java.util.Random;

/**
 * Created by novoselov on 25.09.2015.
 */
public class Goblin extends Creature {
    static private int count;

    static {
        count = 1;
    }

    Goblin() {
        this("Unknown - " + count);
        count++;
    }

    // Гоблин просто бежит, без колдовства и выкрутасов .... беги форест, беги )
    Goblin(String name) {
        super(name + (" (гоблин)"));
        if (name.isEmpty()) {
            setName("Unknown #" + count + " (гоблин)");
            count++;
        }
        Random random = new Random();
        speed.setMinSpeed(10 + random.nextInt(16));     // 10 <= minimalSpeed <= 25
        speed.setMaxSpeed(40 + random.nextInt(16));     // 40 <= maximalSpeed <= 55
    }
}
