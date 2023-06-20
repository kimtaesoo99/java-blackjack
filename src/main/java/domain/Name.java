package domain;

import exception.BlankNameException;

import java.util.Objects;

public class Name {

    private static final String BLANK_ERROR_MESSAGE = "이름은 공백일 수 없습니다.";

    private final String name;

    public Name(final String name) {
        validation(name);
        this.name = name;
    }

    private void validation(final String name) {
        if (name.isBlank()) {
            throw new BlankNameException(BLANK_ERROR_MESSAGE);
        }
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Name name1 = (Name) o;

        return Objects.equals(getName(), name1.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName());
    }
}
