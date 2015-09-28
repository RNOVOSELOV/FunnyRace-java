import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by novoselov on 25.09.2015.
 */
public class Stadium {
    public static int DISTANCE = 400;           // Дистанция, которую необходимо пробежать
    public static int numberOfCreaturesTypes = 4;
    private static Stadium instance = null;

    ArrayList<Creature> creatures;

    private Stadium() {
        creatures = new ArrayList<Creature>();
    }

    public static Stadium getInstance () {
        if (instance == null)
            instance = new Stadium();
        return instance;
    }

    void createAndSetUpUnits() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Нажмите любую клавишу чтоб создать существ вручную [ВВОД - создать по умолчанию]: ");
        if (scanner.nextLine().isEmpty()) {
            addCreatureByDefault();
        } else {
            // Создание существ вручную
            System.out.println("В настоящий момент поддерживается " + Stadium.numberOfCreaturesTypes + " типа существ:");
            System.out.println("1 - создать гоблина (обладает средней среди юнитов скоростью)");
            System.out.println("2 - создать скелета (обладает максимально возможной скоростью, однако с 20% вероятностью может разрушиться во время забега)");
            System.out.println("3 - создать мага (низкая скорость компенсируется 25% вероятностью увеличения текущей скорости в два раза)");
            System.out.println("4 - создать чернокнижника (с 25% вероятностью замораживает впередиидущих бегунов на один ход, однако собственная скорость является довольно низкой)");
            do {
                int creatureID = getCreatureID();
                if (creatureID == 0) {
                    if (creatures.size() == 0) {
                        System.out.println("Вы не содали ни одного юнита, бегуны сформированы автоматически");
                        addCreatureByDefault();
                    }
                    break;
                } else {
                    addCreature(creatureID);
                }
            } while (true);
        }
        showRunnersList();
    }

    // Запрос идентификатора создаваемого существа
    // 0 - прекратить создание существ
    // 1 - создать гоблина
    // 2 - создать скелета
    // 3 - создать мага
    // 4 - создать чернокнижника
    int getCreatureID() {
        int id;
        Scanner scanner = new Scanner(System.in);
        do {
            System.out.print("Введите идентификатор существа [1-" + Stadium.numberOfCreaturesTypes + "; 0 - выход из режима настройки]: ");
            if (scanner.hasNextInt()) {
                id = scanner.nextInt();
                if (id >= 0 && id <= Stadium.numberOfCreaturesTypes) {
                    break;
                } else {
                    System.out.println("Вы ввели неверный идентификатор существа, повторите ввод пожалуйста.");
                }
            }
            scanner.nextLine();
        } while (true);
        return id;
    }

    // Ручное создание игрока определенного типа
    void addCreature(int id) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите имя для существа [ВВОД - назначить имя по умолчанию]: ");
        String name = scanner.nextLine();
        switch (id) {
            case 1:
                creatures.add(new Goblin(name));
                break;
            case 2:
                creatures.add(new Skeleton(name));
                break;
            case 3:
                creatures.add(new Wizard(name));
                break;
            case 4:
                creatures.add(new Warlock(name));
                break;
            default:
                break;
        }
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
        creatures.add(new Warlock());
        creatures.add(new Warlock());
        creatures.add(new Warlock());
        creatures.add(new Creature() {
        });
    }

    void showRunnersList() {
        for (int i = 0; i < creatures.size(); i++) {
            System.out.printf(i + 1 + ") ");
            creatures.get(i).about();
        }
    }

    void startRace () {
        for (int i = 0; i < 400; i++) {
            System.out.printf("-");
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
