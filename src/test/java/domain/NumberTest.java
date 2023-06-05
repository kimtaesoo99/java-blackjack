package domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class NumberTest {

    private static final String ACE_VALUE = "A";
    private static final int MATCH_NUMBER = 13;

    @Test
    public void getValue() {
        //given
        Number number = Number.ACE;

        //when
        String value = number.getName();

        //then
        assertThat(value).isEqualTo(ACE_VALUE);
    }

    @Test
    public void findMatchingNumber() {
        //when
        Number matchNumber = Number.findMatchingNumber(MATCH_NUMBER);

        //then
        assertThat(matchNumber).isEqualTo(Number.ACE);
    }
}
