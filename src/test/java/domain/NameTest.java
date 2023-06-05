package domain;

import exception.BlankNameException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

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
        Assertions.assertThat(AliceName).isEqualTo(NAME);
    }

    @Test
    public void validationBlank() {
        //when, then
        Assertions.assertThatThrownBy(() -> new Name(BLANK))
            .isInstanceOf(BlankNameException.class);
    }
}
