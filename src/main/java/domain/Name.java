package domain;

import exception.BlankNameException;

public class Name {

    private static final String BLANK_ERROR_MESSAGE = "이름은 공백일 수 없습니다.";

    private final String name;

    public Name(final String name) {
        validation(name);
        this.name = name;
    }

    public String getName() {
        return name;
    }

    private void validation(final String name) {
        if (name.isBlank()) {
            throw new BlankNameException(BLANK_ERROR_MESSAGE);
        }
    }
}
