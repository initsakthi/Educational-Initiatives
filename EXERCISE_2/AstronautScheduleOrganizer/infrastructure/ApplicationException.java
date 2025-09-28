package infrastructure;

/**
 * Custom RuntimeException for all business logic errors (e.g., Invalid Input,
 * Task Conflict).
 * Allows for clear, graceful error handling in the main app loop.
 */
public class ApplicationException extends RuntimeException {
    public ApplicationException(String msg) {
        super(msg);
    }
}