import java.util.HashMap;

//В ежедневник можно заносить задачи, можно удалять их, можно получать список задач на предстоящий день.
//Все задачи должны храниться в коллекции, тип которой нужно выбрать самостоятельно как самый оптимальный для работы.
public class Dairy {
    private static HashMap<Integer, Task> dairy = new HashMap<>();
    private static HashMap<Integer, Task> deletedTasks = new HashMap<>();

    public void addTask(Task task) throws NoTaskException {
        if (task != null) {
            dairy.put(task.getID(), task);
        } else {
            throw new NoTaskException("Переданная задача пуста!");
        }
    }

    public void printTasksOnDate(String date) {

    }

    public void deleteTask(int id) throws NoTaskException {
        Task task = dairy.remove(id);
        if (task != null) {
            deletedTasks.put(task.getID(), task);
        } else {
            throw new NoTaskException("Такой задачи нет в ежедневнике!");
        }
    }

    public static HashMap<Integer, Task> getDairy() {
        return dairy;
    }
}
