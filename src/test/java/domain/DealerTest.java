package domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class DealerTest {

    private static final String DEALER = "딜러";
    private static final int DECK_SUM = 20;
    private static final int INIT_AMOUNT = 0;
    private static final int CLOVER_JACK = 38;
    private static final int SPADE_JACK = 25;

    private Dealer dealer;

    @BeforeEach
    void beforeEach() {
        Name name = new Name(DEALER);
        Amount amount = new Amount(INIT_AMOUNT);
        dealer = new Dealer(name, amount);
        Card card = Card.createWithMatchNumber(CLOVER_JACK);
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
        Card card2 = Card.createWithMatchNumber(SPADE_JACK);
        dealer.add(card2);

        //when
        boolean canDraw = dealer.canDraw();

        //then
        assertThat(canDraw).isFalse();
    }

    @Test
    public void getSumOfDeck() {
        //given
        Card card2 = Card.createWithMatchNumber(SPADE_JACK);
        dealer.add(card2);

        //when
        int sumOfDeck = dealer.getSumOfDeck();

        //then
        assertThat(sumOfDeck).isEqualTo(DECK_SUM);
    }
}
