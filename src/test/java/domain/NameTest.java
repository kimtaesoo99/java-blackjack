package domain;

import exception.BlankNameException;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class NameTest {

    private static final String NAME = "Alice";
    private static final String BLANK = " ";

    @Test
    public void getName() {
        //given
        Name name = new Name(NAME);

        //when
        String AliceName = name.getName();

        //then
        assertThat(AliceName).isEqualTo(NAME);
    }

    @Test
    public void validationBlank() {
        //when, then
        assertThatThrownBy(() -> new Name(BLANK))
            .isInstanceOf(BlankNameException.class);
    }

    @Test
    public void equals() {
        //given
        Name name1 = new Name(NAME);
        Name name2 = new Name(NAME);

        //when
        boolean correct = name1.equals(name2);

        //then
        assertThat(correct).isTrue();
    }
}
