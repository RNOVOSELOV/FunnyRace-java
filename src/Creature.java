import java.util.Random;

/**
 * Created by novoselov on 25.09.2015.
 */
public class Creature implements Comparable {
    String name;
    Speed speed;
    int age;
    int currentDistance;
    boolean isDistanceOver;
    boolean skipNextMove;
    int position;

    Creature() {
        this("Неведомая сущность");
    }

    Creature(String name) {
        this.name = name;
        speed = new Speed();
        Random random = new Random();
        age = 3 + random.nextInt(8);    // От трех до 10 лет допускаем
        currentDistance = 0;
        skipNextMove = false;
        isDistanceOver = false;
        position = 0;
    }

    void about() {
        System.out.printf("%s: \tВозраст %2d,\t Минимальная скорость: %d;\tМаксимальная скорость: %d\n", name, age, speed.getMinSpeed(), speed.getMaxSpeed());
    }

    void ride() {
        if (!skipNextMove && !isDistanceOver) {
            currentDistance = currentDistance + speed.moveAndGetSpeed();
        }
    }

    void getInformation() {
        if (isDistanceOver) {
            System.out.printf("%s: \tФИНИШ (%d)\n", name, position);
        } else if (skipNextMove) {
            System.out.printf("%s: \tПройдено дистанции - %d; Движение заморожено чародеем\n", name, currentDistance);
        } else {
            System.out.printf("%s: \tПройдено дистанции - %d; Текущая скорость - %d\n", name, currentDistance, speed.getCurrentSpeed());
        }
    }

    // Сравниваем по пройденной дистанции,
    // необходимо для сортировки обьектов в эррэй листе
    // первыми ходят те юниты, кто прошел наибольшую дистанцию
    @Override
    public int compareTo(Object o) throws ClassCastException {
        if (!(o instanceof Creature))
            throw new ClassCastException("A Creature object expected.");
        return this.currentDistance - ((Creature) o).currentDistance;
    }
}
