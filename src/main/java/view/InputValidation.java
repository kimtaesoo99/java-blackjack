package view;

import domain.Command;
import exception.WrongCommandException;

public class InputValidation {

    private static final String COMMAND_ERROR_MESSAGE = "y나 n을 입력해주세요.";

    public static void checkCorrectCommand(String input) {
        if (!Command.isCorrectInput(input)) {
            throw new WrongCommandException(COMMAND_ERROR_MESSAGE);
        }
    }
}
