public class NoTaskException extends Exception {
    private String message;

    public NoTaskException(String message) {
        super(message);
    }
}
