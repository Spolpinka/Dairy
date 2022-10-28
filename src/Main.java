import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
                        getTasksOnDay(sc);
                        break;
                    case 4:
                        getDeletedTasks();
                    case 5:
                        printAllTasks();
                    case 6:
                        setTaskName(sc);
                    case 7:
                        setTaskDescription(sc);
                    case 8:
                        getGroupedTasks(sc);
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
                "1. Добавить задачу,\n" +
                        "2. Удалить задачу,\n" +
                        "3. Получить задачи на указанный день,\n" +
                        "4. Получить список удаленных задач,\n" +
                        "5. Распечатать список всех задач,\n" +
                        "6. Внести изменения в заголовок задачи,\n" +
                        "7. Внести изменение в описание задачи,\n" +
                        "8. Получить задачи сгруппированные по дням за определенный период\n" +
                        "0. Выход."
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

    private static void getTasksOnDay(Scanner scanner) {
        while (true) {
            System.out.println("введите дату для печати заданий в формате DD-MM-YYYY : ");
            Dairy dairy = new Dairy();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d-MM-yyyy");
            String dateS = scanner.nextLine();
            LocalDate date = null;
            try {
                date = LocalDate.parse(dateS, formatter);
            } catch (DateTimeParseException e) {
                System.out.println("Неверный формат ввода");
                continue;
            }
            List<Task> tasksOnDate = dairy.getTasksOnDate(date);//получили список задач
            for (int i = 0; i < tasksOnDate.size(); i++) {//напечатали список задач
                System.out.println(tasksOnDate.get(i));
            }
            break;
        }
    }
    private static void getDeletedTasks() {
        HashMap<Integer, Task> deletedTasks = Dairy.getDeletedTasks();
        for (Map.Entry<Integer, Task> task : deletedTasks.entrySet()) {//выводим в консоль удаленные задачи
            System.out.println(task.getValue());
        }
    }

    private static void printAllTasks() {
        HashMap<Integer, Task> dairy = Dairy.getDairy();
        for (Map.Entry<Integer, Task> task :
                dairy.entrySet()) {
            System.out.println(task.getValue());
        }
    }

    private static void setTaskName(Scanner scanner) {
        Dairy dairy = new Dairy();
        int id;
        while (true) {
            System.out.println("Введите id задачи для изменения её заголовка: ");
            if (scanner.hasNextInt()) {
                id = scanner.nextInt();
                if (dairy.isTaskExist(id)) {
                    break;
                } else {
                    System.out.println("Задачи с таким id не существует, уточните id");
                }
            } else {
                System.out.println("Введено не число, попробуйте еще раз!");
            }
        }
        System.out.println("Введите новый заголовок задания: ");
        dairy.setTaskName(id, scanner.nextLine());
    }
    private static void setTaskDescription(Scanner scanner) {
        Dairy dairy = new Dairy();
        int id;
        while (true) {
            System.out.println("Введите id задачи для изменения её заголовка: ");
            if (scanner.hasNextInt()) {
                id = scanner.nextInt();
                if (dairy.isTaskExist(id)) {
                    break;
                } else {
                    System.out.println("Задачи с таким id не существует, уточните id");
                }
            } else {
                System.out.println("Введено не число, попробуйте еще раз!");
            }
        }
        System.out.println("Введите новое описание задания: ");
        dairy.setTaskDescription(id, scanner.nextLine());
    }

    private static void getGroupedTasks(Scanner scanner) {
        while (true) {
            System.out.println("введите крайнюю дату для выгрузки сгруппированных тасков в формате DD-MM-YYYY : ");
            Dairy dairy = new Dairy();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d-MM-yyyy");
            String dateS = scanner.nextLine();
            LocalDate date = null;
            try {
                date = LocalDate.parse(dateS, formatter);
            } catch (DateTimeParseException e) {
                System.out.println("Неверный формат ввода");
                continue;
            }
            Map<LocalDate, List<Task>> groupedTasks = dairy.getGroupedTasks(date);//получили список задач, сгруппированный по дням, можно сделать любой отрезов времени в принципе
            for (Map.Entry<LocalDate, List < Task >> entry:
            groupedTasks.entrySet()){
                LocalDate currentDate = entry.getKey();
                System.out.println("на дату " + currentDate.getDayOfMonth() + "." + currentDate.getMonthValue() +
                        "." + currentDate.getYear() + ":");
                for (Task t :
                        entry.getValue()) {
                    System.out.println(t);
                }
            }
        }
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