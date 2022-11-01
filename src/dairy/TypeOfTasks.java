package dairy;

public enum TypeOfTasks {

    PERSONAL("Личная задача"),
    WORK("Рабочая задача");
    private final String message;
    TypeOfTasks(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return message;
    }
}
