package main;

import dairy.Dairy;
import dairy.Task;
import dairy.TypeOfTasks;
import exceptions.*;
import repeateble.*;

import javax.swing.text.html.HTMLDocument;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Main {
    private final static Dairy dairy = new Dairy();

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
                        break;
                    case 5:
                        printAllTasks();
                        break;
                    case 6:
                        setTaskName(sc);
                        break;
                    case 7:
                        setTaskDescription(sc);
                        break;
                    case 8:
                        getGroupedTasks(sc);
                        break;
                    case 9:
                        inputTaskNewDate(sc);
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
                "1. Добавить задачу,\n" +
                        "2. Удалить задачу,\n" +
                        "3. Получить задачи на указанный день,\n" +
                        "4. Получить список удаленных задач,\n" +
                        "5. Распечатать список всех задач,\n" +
                        "6. Внести изменения в заголовок задачи,\n" +
                        "7. Внести изменение в описание задачи,\n" +
                        "8. Получить задачи сгруппированные по дням за определенный период\n" +
                        "9. Добавить задачу с датой начала, отличной от текущей\n" +
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
            Task task = new Task(taskName, taskDescribe, type, repeatable);
            System.out.println("пробуем добавить " + taskName + " описание " + taskDescribe + " тип " + type + " повтор " + repeatable);
            dairy.addTask(task);
            System.out.println("должно было добавиться");
            System.out.println(Dairy.getDairy().size());
        } catch (NoNameException | NoTypeException | NoDescException | NoRepeatException | NoTaskException e) {
            System.out.println(e.getMessage() + e.getStackTrace());
        }

    }private static void inputTaskNewDate (Scanner scanner) {
        System.out.println("Введите название задачи:");
        String taskName = scanner.next();
        System.out.println("Введите описание задачи:");
        String taskDescribe = scanner.next();
        TypeOfTasks type = getTypeOfTask(scanner);
        Repeatable repeatable = getRepeatable(scanner);
        System.out.println("Введите первую дату задачи");
        LocalDate date = getDate(scanner);
        try {
            Task task = new Task(taskName, taskDescribe, type, repeatable, date);
            System.out.println("пробуем добавить " + taskName + " описание " + taskDescribe + " тип " + type + " повтор " + repeatable);
            dairy.addTask(task);
            System.out.println("должно было добавиться");
            System.out.println(Dairy.getDairy().size());
        } catch (NoNameException | NoTypeException | NoDescException | NoRepeatException | NoTaskException e) {
            System.out.println(e.getMessage() + e.getStackTrace());
        }

    }

    private static TypeOfTasks getTypeOfTask(Scanner scanner) {
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

    private static repeateble.Repeatable getRepeatable(Scanner scanner) {
        while (true) {
            System.out.println("Введите повторяемость задачи:\n" +
                    "0. однократная,\n" +
                    "1. ежедневная,\n" +
                    "2. еженедельная,\n" +
                    "3. ежемесячная,\n" +
                    "4. ежегодная.");
            if (scanner.hasNextInt()) {
                int i = scanner.nextInt();
                switch (i) {
                    case 0:
                        return new Once();
                    case 1:
                        return new Daily();
                    case 2:
                        return new Weekly();
                    case 3:
                        return new Monthly();
                    case 4:
                        return new Yearly();
                    default:
                        System.out.println("введено иное число, выберите из списка!");
                }
            } else {
                System.out.println("Введено не число, введите число из списка!");
            }
        }
    }

    private static void getTasksOnDay(Scanner scanner) {
        List<Task> tasksOnDate = dairy.getTasksOnDate(getDate(scanner));//получили список задач
        for (Task task : tasksOnDate) {//напечатали список задач
            System.out.println(task);
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
        int id;
        while (true) {
            System.out.println("Введите id задачи для изменения её заголовка: ");
            if (scanner.hasNextInt()) {
                id = scanner.nextInt();
                if (Dairy.isTaskExist(id)) {
                    System.out.println("Введите новый заголовок задачи:");
                    dairy.setTaskName(id, scanner.nextLine());
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
        int id;
        while (true) {
            System.out.println("Введите id задачи для изменения её заголовка: ");
            if (scanner.hasNextInt()) {
                id = scanner.nextInt();
                if (Dairy.isTaskExist(id)) {
                    System.out.println("Введите новое описание задания: ");
                    dairy.setTaskDescription(id, scanner.nextLine());
                    break;
                } else {
                    System.out.println("Задачи с таким id не существует, уточните id");
                }
            } else {
                System.out.println("Введено не число, попробуйте еще раз!");
            }
        }
        dairy.setTaskDescription(id, scanner.nextLine());
    }

    private static void getGroupedTasks(Scanner scanner) {
        LocalDate date = getDate(scanner);
        Map<LocalDate, List<Task>> groupedTasks = dairy.getGroupedTasks(date);//получили список задач, сгруппированный по дням, можно сделать любой отрезов времени в принципе
        for (Map.Entry<LocalDate, List<Task>> entry :
                groupedTasks.entrySet()) {
            LocalDate currentDate = entry.getKey();
            System.out.println("на дату " + currentDate.getDayOfMonth() + "." + currentDate.getMonthValue() +
                    "." + currentDate.getYear() + ":");
            for (Task t :
                    entry.getValue()) {
                System.out.println(t);
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

            try {
                dairy.deleteTask(taskID);
            } catch (NoTaskException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private static LocalDate getDate(Scanner scanner) {
        while (true) {
            try {
            System.out.println("введите дату в формате DD-MM-YYYY:");

                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

                return LocalDate.parse(scanner.next(), formatter);
            } catch (Exception e) {
                System.out.println("Неверный формат ввода");
            }
        }
    }
}