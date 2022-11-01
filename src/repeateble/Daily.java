package repeateble;

import dairy.Task;

import java.time.LocalDate;

public class Daily implements Repeatable{
    @Override
    public boolean getNextDate(LocalDate date, Task task) {
        return task.getCreationDate().isBefore(date);
    }

    @Override
    public String toString() {
        return "Ежедневно";
    }
}
