package repeateble;

import dairy.Task;

import java.time.LocalDate;

public class Yearly implements Repeatable {
    @Override
    public boolean getNextDate(LocalDate date, Task task) {
        return (date.getDayOfYear() == task.getCreationDate().getDayOfYear() &&
                task.getCreationDate().isBefore(date)) ||
                task.getCreationDate().equals(date);
    }

    @Override
    public String toString() {
        return "Ежегодно";
    }
}
