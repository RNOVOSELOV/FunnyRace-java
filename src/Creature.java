import java.util.Random;

/**
 * Created by novoselov on 25.09.2015.
 */
public class Creature implements Comparable {
    private String name;
    protected Speed speed;
    private int age;
    private int currentDistance;
    private boolean isDistanceOver;
    private boolean skipNextMove;
    private int position;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isDistanceOver() {
        return isDistanceOver;
    }

    public void setDistanceOver(boolean isDistanceOver) {
        this.isDistanceOver = isDistanceOver;
    }

    public boolean skipNextMove() {
        return skipNextMove;
    }

    public void setSkipNextMove(boolean skipNextMove) {
        this.skipNextMove = skipNextMove;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public int getCurrentDistance() {
        return currentDistance;
    }

    public void setCurrentDistance(int currentDistance) {
        this.currentDistance = currentDistance;
    }

    void about() {
        System.out.printf("%s: \tВозраст %2d,\t Минимальная скорость: %d;\tМаксимальная скорость: %d\n", name, age, speed.getMinSpeed(), speed.getMaxSpeed());
    }

    void ride() {
        if (!skipNextMove && !isDistanceOver) {
            currentDistance = currentDistance + speed.moveAndGetSpeed();
        }
    }

    String getInformation() {
        String info = name + ": \t";
        if (isDistanceOver) {
            info += "ФИНИШ (" + position + ")\n";
        } else if (skipNextMove) {
            info += "Пройдено дистанции - " + currentDistance + "; Движение заморожено чародеем\n";
        } else {
            info += "Пройдено дистанции - " + currentDistance + "; Текущая скорость - " + speed.getCurrentSpeed() + "\n";
        }
        System.out.print(info);
        return info;
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
