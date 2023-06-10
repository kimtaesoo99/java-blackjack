package domain;

import exception.WrongCommandException;

import java.util.Arrays;

public enum Command {

    YES("y", true),
    NO("n", false);

    private static final String COMMAND_ERROR_MESSAGE = "y나 n을 입력해주세요.";

    private final String input;
    private final boolean status;

    Command(final String input, final boolean status) {
        this.input = input;
        this.status = status;
    }

    public static boolean isCorrectInput(final String input) {
        return Arrays.stream(Command.values())
            .anyMatch(command -> command.input.equals(input));
    }

    public static Command getCommand(final String input) {
        return Arrays.stream(Command.values())
            .filter(command -> command.input.equals(input))
            .findAny()
            .orElseThrow(() -> new WrongCommandException(COMMAND_ERROR_MESSAGE));
    }

    public boolean isDraw() {
        return status;
    }
}
