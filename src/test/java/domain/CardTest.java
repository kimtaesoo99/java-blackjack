package domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class CardTest {

    private static final String CLOVER_PATTERN_NAME = "클로버";
    private static final String JACK_NUMBER_VALUE = "J";
    private static final int CLOVER_JACK = 38;

    private Card card;

    @BeforeEach
    void beforeEach() {
        card = Card.createWithMatchNumber(CLOVER_JACK);
    }

    @Test
    public void getPattern() {
        //when
        String patternName = card.getPattern();

        //then
        assertThat(patternName).isEqualTo(CLOVER_PATTERN_NAME);
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
        assertThat(fullName).isEqualTo(JACK_NUMBER_VALUE + CLOVER_PATTERN_NAME);
    }
}
