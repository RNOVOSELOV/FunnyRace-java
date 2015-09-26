import java.util.Random;

/**
 * Created by novoselov on 25.09.2015.
 */
public class Creature {
    String name;
    Speed speed;
    int age;
    int currentDistance;
    boolean skipMove;

    Creature() {
        this("Вообще что то странное");
    }

    Creature(String name) {
        Random random = new Random();
        this.name = name;
        speed = new Speed();
        age = 3 + random.nextInt(8);    // От трех до 10 лет допускаем
        currentDistance = 0;
        skipMove = false;
    }

    void about() {
        System.out.printf("%s: Возраст %2d,\t Минимальная скорость: %d;\tМаксимальная скорость: %d\n", name, age, speed.getMinSpeed(), speed.getMaxSpeed());
    }

    void ride() {

        currentDistance = currentDistance + speed.moveAndGetSpeed();
    }

    void getInformation() {
        if (currentDistance < Stadium.DISTANCE) {
            System.out.printf("%s: \tТекущая скорость - %d; Пройдено дистанции - %d\n", name, speed.getCurrentSpeed(), currentDistance);
        } else {
            System.out.printf("%s: \tФИНИШ\n", name);
        }
    }
}
