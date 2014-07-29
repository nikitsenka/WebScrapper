package sites.linkedIn.io;

public class InvalidInputData extends Exception {
    /**
     * Constructor
     */
    public InvalidInputData() {
    }

    /**
     * Constructor
     * @param message
     */
    public InvalidInputData(String message) {
        super(message);
    }

    /**
     * Constructor
     * @param message
     * @param cause
     */
    public InvalidInputData(String message, Throwable cause) {
        super(message, cause);
    }
}
