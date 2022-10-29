package dairy;

import java.time.LocalDate;

public enum Repeatable {
    DAILY ("Ежедневно"),
    WEEKLY("Еженедельно"),
    MONTHLY("Ежемесячно"),
    YEARLY("Ежегодно"),
    ONCE("Единожды");
    private String message;
    Repeatable(String message) {
        this.message = message;
    }

    public LocalDate getNextDate(Task task) {
        LocalDate nextDate = LocalDate.now();
        switch (this) {
            case DAILY:
                return nextDate;
            case WEEKLY:
                while (nextDate.getDayOfWeek() != task.getCreationTimeDate().getDayOfWeek()){
                    nextDate.plusDays(1);
                }
                return nextDate;
            case MONTHLY:
                if (nextDate.getDayOfMonth() > task.getCreationTimeDate().getDayOfMonth()){
                    nextDate.plusMonths(1);
                }
                while (nextDate.getDayOfMonth() != task.getCreationTimeDate().getDayOfMonth()) {
                    nextDate.plusDays(1);
                }
                return nextDate;
            case YEARLY:
                if (nextDate.getDayOfYear() > task.getCreationTimeDate().getDayOfYear()) {
                    nextDate.plusYears(1);
                }
                while (nextDate.getMonthValue() < task.getCreationTimeDate().getMonthValue()) {
                    nextDate.plusMonths(1);
                }
                nextDate = nextDate.withDayOfMonth(0);
                while (nextDate.getDayOfMonth() != task.getCreationTimeDate().getDayOfMonth()) {
                    nextDate.plusDays(1);
                }
                return nextDate;
        }
        return null;
    }

    public String getMessage() {
        return message;
    }
}
