import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    public List<Task> getTasksOnDate(LocalDate date) {
        List<Task> taskList = new ArrayList<>();
        for (Map.Entry<Integer, Task> task :
                dairy.entrySet()) {
            LocalDate localDate = task.getValue().getCreationTimeDate();
            if (localDate.getDayOfMonth() == date.getDayOfMonth() ||
                    localDate.getDayOfYear() == date.getDayOfYear() ||
                    localDate.getDayOfWeek().getValue() == date.getDayOfWeek().getValue() ||
                    task.getValue().getRepeatable().equals(Repeatable.DAILY)) {
                taskList.add(task.getValue());
            }
        }
        return taskList;
    }

    public void deleteTask(int id) throws NoTaskException {
        Task task = dairy.remove(id);
        if (task != null) {
            deletedTasks.put(task.getID(), task);
        } else {
            throw new NoTaskException("Такой задачи нет в ежедневнике - нечего удалять!");
        }
    }

    public static HashMap<Integer, Task> getDairy() {
        return dairy;
    }

    public static HashMap<Integer, Task> getDeletedTasks() {
        return deletedTasks;
    }

    public static boolean isTaskExist(int id) {
        if (dairy.containsKey(id)) {
            return true;
        } else {
            return false;
        }
    }

    public void setTaskName(int id, String newName) {
        dairy.get(id).setName(newName);
    }

    public void setTaskDescription(int id, String newDescription) {
        dairy.get(id).setDescribe(newDescription);
    }
}
