import java.time.LocalDate;

public enum Repeatable {
    DAILY ("Ежедневно"),
    WEEKLY("Еженедельно"),
    MOUTHLY("Ежемесячно"),
    YEARLY("Ежегодно"),
    ONCE("Единожды");
    private String message;
    Repeatable(String message) {
        this.message = message;
    }

    public LocalDate getNextTime(LocalDate creationTimeDate) {
        switch (this) {
            case DAILY:
                return creationTimeDate.plusDays(1);
            case WEEKLY:
                return creationTimeDate.plusWeeks(1);
            case MOUTHLY:
                return creationTimeDate.plusMonths(1);
            case YEARLY:
                return creationTimeDate.plusYears(1);
        }
        return null;
    }
}
