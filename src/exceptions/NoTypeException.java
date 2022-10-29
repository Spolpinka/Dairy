package exceptions;

public class NoTypeException extends Exception {
    private String message;

    public NoTypeException(String message) {
        super(message);
    }
}
