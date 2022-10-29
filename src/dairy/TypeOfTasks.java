package dairy;

public enum TypeOfTasks {

    PERSONAL("Личная задача"),
    WORK("Рабочая задача");
    private String message;
    TypeOfTasks(String message) {
        this.message = message;
    }
}
