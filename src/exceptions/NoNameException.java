package exceptions;

public class NoNameException extends Exception {
    private String message;

    public NoNameException(String message) {
        super(message);
    }
}
