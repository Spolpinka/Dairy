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
                        //обработка 3
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