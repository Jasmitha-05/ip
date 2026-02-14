package stitch;

/**
 * Uses custom exception class for error-handling
 */
public class StitchException extends Exception {
    public StitchException(String message) {
        super(message);
    }
}
