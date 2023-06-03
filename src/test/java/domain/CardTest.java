package domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class CardTest {

    private static final String DIAMOND_PATTERN_NAME = "다이아몬드";
    private static final String JACK_NUMBER_VALUE = "J";
    private static final int MATCHING_NUMBER = 51;

    @Test
    public void getPattern() {
        //given
        Card card = new Card(MATCHING_NUMBER);

        //when
        String patternName = card.getPattern();

        //then
        assertThat(patternName).isEqualTo(DIAMOND_PATTERN_NAME);
    }

    @Test
    public void getNumber() {
        //given
        Card card = new Card(MATCHING_NUMBER);

        //when
        String numberValue = card.getNumber();

        //when
        assertThat(numberValue).isEqualTo(JACK_NUMBER_VALUE);
    }

    @Test
    public void getFullName() {
        //given
        Card card = new Card(MATCHING_NUMBER);

        //when
        String fullName = card.getFullName();

        //when
        assertThat(fullName).isEqualTo(JACK_NUMBER_VALUE + DIAMOND_PATTERN_NAME);
    }
}
