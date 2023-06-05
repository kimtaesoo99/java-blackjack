package domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class CardTest {

    private static final String DIAMOND_PATTERN_NAME = "다이아몬드";
    private static final String JACK_NUMBER_VALUE = "J";

    private Card card;

    @BeforeEach
    void beforeEach() {
        card = new Card(Pattern.DIAMOND, Number.JACK);
    }

    @Test
    public void getPattern() {
        //when
        String patternName = card.getPattern();

        //then
        assertThat(patternName).isEqualTo(DIAMOND_PATTERN_NAME);
    }

    @Test
    public void getNumber() {
        //when
        String numberValue = card.getNumber();

        //when
        assertThat(numberValue).isEqualTo(JACK_NUMBER_VALUE);
    }

    @Test
    public void getFullName() {
        //when
        String fullName = card.getFullName();

        //when
        assertThat(fullName).isEqualTo(JACK_NUMBER_VALUE + DIAMOND_PATTERN_NAME);
    }
}
