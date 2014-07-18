package api;

public class InvalidFieldException extends RuntimeException {
    public InvalidFieldException(String message) {
        super(message);
    }

    public InvalidFieldException(String message, Exception e) {
        super(message,e);
    }
}
