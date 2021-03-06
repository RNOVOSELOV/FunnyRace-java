import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/**
 * Created by novoselov on 25.09.2015.
 */
public class Stadium {
    public static int DISTANCE = 150;           // Дистанция, которую необходимо пробежать
    public static int SIZEOFPRINTEDDISTANCE = 100;
    public static int numberOfCreaturesTypes = 3;

    private static Stadium instance = null;
    private int number;
    private FunnyRaceWindow window = new FunnyRaceWindow();

    private ArrayList<Creature> creatures;

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
            System.out.printf("Дорожка " + (i + 1) + "] ");
            creatures.get(i).about();
        }
    }

    public void setBet() {
        Scanner scanner = new Scanner(System.in);
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
        System.out.println("Вы считаете, что победит: " + creatures.get(number - 1).getName() + " ... посмотрим\n");
    }

    void startRace() {
        boolean stopRace = false;
        ArrayList<Creature> tempList;
        printCurrentPosition();
        boolean skipNextMove;
        int position = 1;
        int time = 1;
        window.openWindow();
        while (!stopRace) {
            stopRace = true;
            skipNextMove = false;
            tempList = new ArrayList<Creature>(creatures);

            Collections.sort(tempList);
            for (Creature creature : tempList) {
                creature.setSkipNextMove(skipNextMove);
                creature.ride();
                if (creature instanceof Wizard && !skipNextMove) {
                    skipNextMove = ((Wizard) creature).isFreezeSpellShoot();
                }
            }

            Collections.sort(tempList);
            for (int i = tempList.size() - 1; i >= 0; i--) {
                if (creatures.get(i).getCurrentDistance() >= Stadium.DISTANCE && !creatures.get(i).isDistanceOver()) {
                    creatures.get(i).setDistanceOver(true);
                    creatures.get(i).setPosition(position);
                    position++;
                }
                if (creatures.get(i).isDistanceOver() && stopRace) {
                    stopRace = true;
                } else {
                    stopRace = false;
                }
            }

            String currState = "Временная отсечка " + time++ + ":\n";
            System.out.print("\n" + currState);
            window.clearAndSetText("Вы поставили на: " + creatures.get(number - 1).getName() + " [ДОРОЖКА №" + number + "]\n" + currState + "\n");
            printCurrentPosition();
            int treadmillNumber = 1;
            for (Creature creature : creatures) {
                window.appendText("Дорожка " + treadmillNumber++ + "] " + creature.getInformation());
                creature.setSkipNextMove(false);
            }
            pause();
        }
        String result = "";
        for (int i = 0; i < creatures.size(); i++) {
            if (creatures.get(i).getPosition() == 1) {
                if (i == number - 1) {
                    result = "Да ты везунчик по жизни, пора в Лас Вегас!";
                } else if (creatures.get(number - 1) instanceof Skeleton && ((Skeleton) creatures.get(number - 1)).isCrashed()) {
                    result = "Победил: " + creatures.get(i).getName() + ". " + creatures.get(number - 1).getName() + " не смог дойти до финиша";
                } else {
                    result = "Победил: " + creatures.get(i).getName() + ". " + creatures.get(number - 1).getName() + " занял место: " + creatures.get(number - 1).getPosition();
                }
            }
        }
        window.appendText("\n" + result);
        System.out.println(result);
    }

    void printCurrentPosition() {
        String text = "";
        for (int i = 0; i < creatures.size(); i++) {
            int currentPositionAtTreadmill = Stadium.SIZEOFPRINTEDDISTANCE * creatures.get(i).getCurrentDistance() / DISTANCE;
            if (currentPositionAtTreadmill >= Stadium.SIZEOFPRINTEDDISTANCE) {
                currentPositionAtTreadmill = Stadium.SIZEOFPRINTEDDISTANCE - 1;
            }

            text += "Дорожка " + (i + 1) + "] ";
            for (int j = 0; j < Stadium.SIZEOFPRINTEDDISTANCE; j++) {
                if (j == currentPositionAtTreadmill && creatures.get(i).isDistanceOver()) {
                    text += "X";
                } else if (j == currentPositionAtTreadmill) {
                    text += "|";
                } else {
                    text += "_";
                }
            }
            text += "\n";
        }
        System.out.println(text);
        window.appendText(text + "\n");
    }

    private static void pause() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
