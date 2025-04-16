package exceptions;

public class DuplicateElementException extends RuntimeException {
    public DuplicateElementException(String msg) {
        super(msg);
    }
}
