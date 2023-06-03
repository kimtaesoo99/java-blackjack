package domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class CardsTest {

    @Test
    public void pick() {
        //given
        Cards cards = new Cards();

        //when
        for (int i = 0; i < 52; i++){
            cards.pick();
        }

        //then
        assertThatThrownBy(cards::pick)
            .isInstanceOf(IndexOutOfBoundsException.class);
    }
}
