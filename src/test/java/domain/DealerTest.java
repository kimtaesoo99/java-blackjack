package domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class DealerTest {

    private static final String DEALER = "딜러";
    private static final int DECK_SUM = 20;

    private Dealer dealer;

    @BeforeEach
    void beforeEach() {
        Name name = new Name(DEALER);
        dealer = new Dealer(name);
        Card card = new Card(Pattern.CLOVER, Number.KING);
        dealer.add(card);
    }

    @Test
    public void canDrawSuccess() {
        //when
        boolean canDraw = dealer.canDraw();

        //then
        assertThat(canDraw).isTrue();
    }

    @Test
    public void canDrawFail() {
        //given
        Card card2 = new Card(Pattern.HEART, Number.JACK);
        dealer.add(card2);

        //when
        boolean canDraw = dealer.canDraw();

        //then
        assertThat(canDraw).isFalse();
    }

    @Test
    public void getSumOfDeck() {
        //given
        Card card2 = new Card(Pattern.HEART, Number.JACK);
        dealer.add(card2);

        //when
        int sumOfDeck = dealer.getSumOfDeck();

        //then
        assertThat(sumOfDeck).isEqualTo(DECK_SUM);
    }
}
