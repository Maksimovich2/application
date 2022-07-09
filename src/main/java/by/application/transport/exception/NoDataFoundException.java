package by.application.transport.exception;

/**
 * @author Maksim Maksimovich
 */
public class NoDataFoundException extends RuntimeException {
    public NoDataFoundException() {
    }

    public NoDataFoundException(String message) {
        super(message);
    }

    public NoDataFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoDataFoundException(Throwable cause) {
        super(cause);
    }
}
