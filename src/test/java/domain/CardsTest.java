package domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class CardsTest {

    private static final int FIRST_CARD = 0;
    private static final int LAST_CARD = 51;

    @Test
    public void createAutoCards() {
        //given
        Cards cards = Cards.createAutoCards();

        //when
        for (int i = FIRST_CARD; i <= LAST_CARD; i++) {
            cards.pick();
        }

        //then
        assertThatThrownBy(cards::pick)
            .isInstanceOf(IndexOutOfBoundsException.class);
    }

    @Test
    public void pick() {
        //given
        Cards cards = Cards.createAutoCards();

        //when
        Card pick = cards.pick();

        //then
        assertThat(pick).isInstanceOf(Card.class);
    }
}
