import java.util.Random;

/**
 * Created by novoselov on 25.09.2015.
 */
public class Creature {
    String name;
    int minSpeed;
    int maxSpeed;
    int age;
    int currentSpeed;
    int currentDistance;

    Creature() {
        this("Вообще что то странное");
    }

    Creature(String name) {
        Random random = new Random();
        this.name = name;
        minSpeed = 0;
        maxSpeed = 0;
        age = 3 + random.nextInt(8);    // От трех до 10 лет допускаем
        currentDistance = 0;
    }

    void about() {
        System.out.printf("%s: Возраст %2d,\t Минимальная скорость: %d;\tМаксимальная скорость: %d\n", name, age, minSpeed, maxSpeed);
    }

    void ride() {
        Random random = new Random();
        currentSpeed = minSpeed + random.nextInt(maxSpeed - minSpeed);
        currentDistance = currentDistance + currentSpeed;
    }

    void getInformation() {
        if (currentDistance < Stadium.DISTANCE) {
            System.out.printf("%s: \tТекущая скорость - %d; Пройдено дистанции - %d\n", name, currentSpeed, currentDistance);
        } else {
            System.out.printf("%s: \tФИНИШ\n", name);
        }
    }
}
