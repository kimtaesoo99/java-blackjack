package domain;

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

    private void validation(String name) {
        if (name.isBlank()) {
            throw new IllegalStateException(BLANK_ERROR_MESSAGE);
        }
    }
}
