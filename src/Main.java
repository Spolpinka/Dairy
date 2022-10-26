import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (true) {
            printMenu();
            System.out.println("Введите соответствующую цифру пункта меню:");
            if (sc.hasNextInt()) {
                int choice = sc.nextInt();
                switch (choice) {
                    case 1:
                        inputTask(sc);
                        break;
                    case 2:
                        deleteTask(sc);
                        break;
                    case 3:
                        printTasksOnDay(sc);
                        break;
                    case 0:
                        System.exit(0);
                }
            } else {
                sc.next();
                System.out.println("Выберите пункт меню из списка, а не из головы!");
            }
        }

    }

    private static void printMenu() {
        System.out.println(
                "1. Добавить задачу \n" +
                        "2. Удалить задачу\n" +
                        "3. Получить задачи на указанный день\n" +
                        "0. Выход"
        );
    }

    private static void inputTask(Scanner scanner) {
        System.out.println("Введите название задачи: ");
        String taskName = scanner.next();
        System.out.println("Введите описание задачи: ");
        String taskDescribe = scanner.next();
        TypeOfTasks type = getTypeOfTask(scanner);
        Repeatable repeatable = getRepeatable(scanner);
        try {
            Dairy dairy = new Dairy();
            dairy.addTask(new Task(taskName, taskDescribe, type, repeatable));
        } catch (NoNameException | NoTypeException | NoDescException | NoRepeatException | NoTaskException e) {
            System.out.println(e);
        }

    }

    private static TypeOfTasks getTypeOfTask(Scanner scanner) {
        TypeOfTasks type;
        while (true) {
            System.out.println("Введите категорию задачи:\n" +
                    "0. личная,\n" +
                    "1. рабочая.");
            if (scanner.hasNextInt()) {
                int i = scanner.nextInt();
                if (i == 0) {
                    return TypeOfTasks.PERSONAL;
                } else if (i == 1) {
                    return TypeOfTasks.WORK;
                } else {
                    System.out.println("введено иное число, выберите из списка!");
                }
            } else {
                System.out.println("Введено не число, введите число из списка!");
            }
        }
    }

    private static Repeatable getRepeatable(Scanner scanner) {
        Repeatable repeatable;
        while (true) {
            System.out.println("Введите повторяемость задачи:\n" +
                    "0. однократная,\n" +
                    "1. ежедневная,\n" +
                    "2. еженедельная,\n" +
                    "3. ежемесячная,\n" +
                    "4. ежегодная.\n");
            if (scanner.hasNextInt()) {
                int i = scanner.nextInt();
                switch (i) {
                    case 0:
                        return Repeatable.ONCE;
                    case 1:
                        return Repeatable.DAILY;
                    case 2:
                        return Repeatable.WEEKLY;
                    case 3:
                        return Repeatable.MOUTHLY;
                    case 4:
                        return Repeatable.YEARLY;
                    default:
                        System.out.println("введено иное число, выберите из списка!");
                }
            } else {
                System.out.println("Введено не число, введите число из списка!");
            }
        }
    }

    private static void printTasksOnDay(Scanner scanner) {
        System.out.println("введите дату для печати заданий в формате DD-MM-YYYY : ");
        Dairy dairy = new Dairy();
        dairy.printTasksOnDate(scanner.nextLine());
    }

    private static void deleteTask(Scanner sc) {
        while (true) {
            System.out.println("Введите ID задачи для удаления: \n" +
                    "0. выйти в предыдущее меню");
            int taskID;
            try {
                taskID = sc.nextInt();
                if (taskID == 0) {
                    break;
                }
            } catch (Exception e) {
                System.out.println("введено не число!");
                continue;
            }

            Dairy dairy = new Dairy();
            try {
                dairy.deleteTask(taskID);
            } catch (NoTaskException e) {
                System.out.println(e);
            }
        }
    }
}