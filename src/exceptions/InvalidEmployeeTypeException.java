package exceptions;

public class InvalidEmployeeTypeException extends RuntimeException {
    public InvalidEmployeeTypeException(String msg) {
        super(msg);
    }
}
