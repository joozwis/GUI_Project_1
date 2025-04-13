package exceptions;

public class IncorrectBirthYearException extends RuntimeException {
    public IncorrectBirthYearException(String msg) {
        super(msg);
    }
}
