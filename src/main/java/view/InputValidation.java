package view;

public class InputValidation {

    private static final String YES = "y";
    private static final String NO = "n";
    private static final String COMMAND_ERROR_MESSAGE = "y나 n을 입력해주세요.";

    public static void addMoreCardCommand(String command) {
        if (!(command.equals(YES) || command.equals(NO))) {
            throw new IllegalStateException(COMMAND_ERROR_MESSAGE);
        }
    }
}
