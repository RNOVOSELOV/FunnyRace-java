import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by novoselov on 25.09.2015.
 */
public class Stadium {
    public static int DISTANCE = 150;           // Дистанция, которую необходимо пробежать
    ArrayList<Creature> creatures;

    Stadium() {
        creatures = new ArrayList<Creature>();
    }

    void tuneCreatures() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Нажмите любую клавишу чтоб создать существ вручную [ВВОД - создать по умолчанию]: ");
        if (scanner.nextLine().isEmpty()) {
            addCreatureByDefault();
        } else {
            // Создание существ вручную
            addCreatureByDefault();
        }

    }

    void addCreature(Creature c) {
        creatures.add(c);
    }

    void addCreatureByDefault() {
        creatures.add(new Wizard("Боря"));
        creatures.add(new Wizard("Вася"));
        creatures.add(new Wizard());
        creatures.add(new Goblin());
        creatures.add(new Goblin());
        creatures.add(new Goblin());
        creatures.add(new Skeleton());
        creatures.add(new Skeleton());
        creatures.add(new Skeleton());
    }

    void startRace() {
        for (int i = 0; i < creatures.size(); i++) {
            System.out.printf(i + 1 + ") ");
            creatures.get(i).about();
        }
    }

    /*    Horse [] horses = new Horse[15];
        for (int i = 0; i < horses.length; i++) {
            Horse tempHorse = new Horse("Кобылка - " + (i + 1), i+1);
            horses[i] = tempHorse;
        }
        for (Horse horse : horses) {
            horse.about();
        }
        Scanner scanner = new Scanner(System.in);
        int number = 5;
        do {
            System.out.print("Введите порядковый номер лошади: [1-20]: ");
            if (scanner.hasNextInt()) {
                number = scanner.nextInt();
                if (number >=1 && number <= 20)
                    break;
                else
                    System.out.println("Вы ввели некорректный номер лошади, повторите ввод пожалуйста.");
            }
            scanner.nextLine();
        } while (true);
        System.out.println("Ваша лошадь: " + horses[number-1].name);
        String msg;
        for (int i = 0; true; i++) {
            msg = (i == 0) ? "\nЗабег начался. Дистанция "+ DISTANCE +" м." : "\nВременная отсечка " + i + ": ";
            System.out.println(msg);
            // Заставляем лошадок пробежаться
            for (Horse horse : horses) {
                horse.ride();
            }
            // Сортируем по дистанции
            sorthorses(horses);
            // Выдаем список
            for (Horse horse : horses) {
                horse.getInformation();
            }
            pause();
            if (horses[14].currentDistance > DISTANCE)
                break;
        }
        System.out.println("Забег закончен.");
        if (horses[0].horseNumber == number) {
            System.out.println("Ваша лошадь пришла первой! Принимайте поздравления и вознаграждения!");
        } else {
            for (int i = 0; i < horses.length; i++) {
                if (horses[i].horseNumber == number)
                {
                    number = i+1;   // Теперь здесь храним порядковы помер нашей лошадки в списке финишеров
                    break;
                }
            }
            System.out.println("Победила лошадь: " + horses[0].name + ". Ваша лошадь пришла под номером: " + number);
        }
    }

    // Пузырек :-)
    static void sorthorses (Horse[] h) {
        for (int i = h.length-1; i >= 0; i--) {
            for (int j = 0; j < i; j++) {
                if (h[j].currentDistance < h[j + 1].currentDistance) {
                    Horse temp = h[j];
                    h[j] = h[j+1];
                    h[j+1] = temp;
                }
            }
        }
    }

    private static void pause () {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
*/
}
