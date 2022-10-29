package dairy;

import java.time.LocalDate;

public enum Repeatable {
    DAILY ("Ежедневно"),
    WEEKLY("Еженедельно"),
    MONTHLY("Ежемесячно"),
    YEARLY("Ежегодно"),
    ONCE("Единожды");
    private final String message;
    Repeatable(String message) {
        this.message = message;
    }

    public LocalDate getNextDate(Task task) {
        LocalDate nextDate = LocalDate.now();
        LocalDate taskDate = task.getCreationDate();
        switch (this) {
            case DAILY:
                return nextDate;
            case WEEKLY:
                while (nextDate.getDayOfWeek() != taskDate.getDayOfWeek()){
                    nextDate = nextDate.plusDays(1);
                }
                return nextDate;
            case MONTHLY:
                if (nextDate.getDayOfMonth() > taskDate.getDayOfMonth()){
                    nextDate = nextDate.plusMonths(1);
                }
                while (nextDate.getDayOfMonth() != taskDate.getDayOfMonth()) {
                    nextDate = nextDate.plusDays(1);
                }
                return nextDate;
            case YEARLY:
                if (nextDate.getDayOfYear() > taskDate.getDayOfYear()) {
                    nextDate = nextDate.plusYears(1);
                }
                while (nextDate.getMonthValue() < taskDate.getMonthValue()) {
                    nextDate = nextDate.plusMonths(1);
                }
                nextDate = nextDate.withDayOfMonth(0);
                while (nextDate.getDayOfMonth() != taskDate.getDayOfMonth()) {
                    nextDate = nextDate.plusDays(1);
                }
                return nextDate;
        }
        return null;
    }

    public String getMessage() {
        return message;
    }
}
