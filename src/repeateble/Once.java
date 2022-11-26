package repeateble;

import dairy.Task;

import java.time.LocalDate;

public class Once implements Repeatable {
    @Override
    public boolean getNextDate(LocalDate date, Task task) {
        return date.equals(task.getCreationDate());
    }

    @Override
    public String toString() {
        return "Единожды";
    }
}
