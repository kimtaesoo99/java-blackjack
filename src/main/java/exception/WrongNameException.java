package exception;

public class WrongNameException extends RuntimeException {

    public WrongNameException(String message) {
        super(message);
    }
}
