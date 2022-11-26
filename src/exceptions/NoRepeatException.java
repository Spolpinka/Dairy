package exceptions;

public class NoRepeatException extends Exception {
    private String message;

    public NoRepeatException(String message) {
        super(message);
    }
}
