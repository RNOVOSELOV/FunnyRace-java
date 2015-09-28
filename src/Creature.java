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
    }

    void about() {
        System.out.printf("%s: \tВозраст %2d,\t Минимальная скорость: %d;\tМаксимальная скорость: %d\n", name, age, speed.getMinSpeed(), speed.getMaxSpeed());
    }

    void ride() {
        if (skipNextMove) {
            skipNextMove = false;
            return;
        }
        currentDistance = currentDistance + speed.moveAndGetSpeed();
        if (currentDistance >= Stadium.DISTANCE) {
            isDistanceOver = true;
        }
        getInformation();
    }

    void getInformation() {
            System.out.printf("%s: \tТекущая скорость - %d; Пройдено дистанции - %d %s\n", name, speed.getCurrentSpeed(), currentDistance, isDistanceOver?"true":"false");
    }

    // Сравниваем по пройденной дистанции,
    // необходимо для сортировки обьектов в эррэй листе
    // первыми ходят те юниты, кто прошел наибольшую дистанцию
    @Override
    public int compareTo(Object o) throws ClassCastException {
        if (!(o instanceof Creature))
            throw new ClassCastException("A Creature object expected.");
        return ((Creature)o).currentDistance - this.currentDistance;
    }
}
