import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by novoselov on 25.09.2015.
 */
public class Stadium {
    public static int DISTANCE = 500;           // Дистанция, которую необходимо пробежать
    public static int PRINTEDDISTANCE = 100;
    public static int numberOfCreaturesTypes = 3;
    private static Stadium instance = null;

    ArrayList<Creature> creatures;

    private Stadium() {
        creatures = new ArrayList<Creature>();
    }

    public static Stadium getInstance() {
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
            System.out.println("3 - создать мага (низкая скорость компенсируется колдовством: увеличения текущей скорости в два раза и замедления оппонентов)");
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
        printStartTab();
    }

    // Запрос идентификатора создаваемого существа
    // 0 - прекратить создание существ
    // 1 - создать гоблина
    // 2 - создать скелета
    // 3 - создать мага
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
            default:
                break;
        }
    }

    void addCreatureByDefault() {
        creatures.add(new Wizard("Гендальф белый"));
        creatures.add(new Wizard("Старик Хоттабыч"));
        creatures.add(new Wizard("Волан де Морт"));
        creatures.add(new Goblin(""));
        creatures.add(new Goblin(""));
        creatures.add(new Goblin(""));
        creatures.add(new Skeleton(""));
        creatures.add(new Skeleton(""));
        creatures.add(new Skeleton(""));
    }

    void printStartTab() {
        for (int i = 0; i < creatures.size(); i++) {
            System.out.printf(i + 1 + ") ");
            creatures.get(i).about();
        }
    }

    public void setBet() {
        Scanner scanner = new Scanner(System.in);
        int number;
        do {
            System.out.print("Введите порядковый номер бегуна: [1-" + creatures.size() + "]: ");
            if (scanner.hasNextInt()) {
                number = scanner.nextInt();
                if (number >= 1 && number <= creatures.size())
                    break;
                else
                    System.out.println("Вы ввели некорректный номер, повторите ввод пожалуйста.");
            }
            scanner.nextLine();
        } while (true);
        System.out.println("Вы считаете, что победит: " + creatures.get(number - 1).name + " ... посмотрим\n");
    }

    void startRace() {
        printCurrentPosition();

        for (Creature creature : creatures) {
            creature.ride();
        }

        printCurrentPosition();

        pause();
    }

    void printCurrentPosition() {
        for (int i = 0; i < creatures.size(); i++) {
            int currentPositionAtTreadmill = Stadium.PRINTEDDISTANCE * creatures.get(i).speed.getCurrentSpeed() / DISTANCE;
            System.out.print(i+1 + ") ");
            for (int j = 0; j < Stadium.PRINTEDDISTANCE; j++) {
                if (j == currentPositionAtTreadmill) {
                    System.out.print("|");
                } else {
                    System.out.print("_");
                }
            }
            System.out.print("\n");
        }
    }

    private static void pause() {
        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /*


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
*/
}
