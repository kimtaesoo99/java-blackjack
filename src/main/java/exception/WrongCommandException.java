package exception;

public class WrongCommandException extends RuntimeException {

    public WrongCommandException(String commandErrorMessage) {
        super(commandErrorMessage);
    }
}
