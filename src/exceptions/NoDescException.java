package exceptions;

public class NoDescException extends Exception {
    private String message;

    public NoDescException(String message) {
        super(message);
    }
}
