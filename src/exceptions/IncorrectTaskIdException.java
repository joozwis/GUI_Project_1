package exceptions;

public class IncorrectTaskIdException extends RuntimeException {
    public IncorrectTaskIdException(String msg) {
        super(msg);
    }
}
