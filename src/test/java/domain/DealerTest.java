package domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class DealerTest {

    private static final String DEALER = "딜러";
    private static final int CLOVER_KING = 10;
    private static final int HEART_JACK = 12;
    private static final int DECK_SUM = 20;

    private Dealer dealer;

    @BeforeEach
    void beforeEach() {
        Name name = new Name(DEALER);
        dealer = new Dealer(name);
        Card card1 = new Card(CLOVER_KING);
        dealer.add(card1);
    }

    @Test
    public void canDraw() {
        //given

        //when
        boolean canDraw = dealer.canDraw();

        //then
        assertThat(canDraw).isTrue();
    }

    @Test
    public void cannotDraw() {
        //given
        Card card2 = new Card(HEART_JACK);
        dealer.add(card2);

        //when
        boolean canDraw = dealer.canDraw();

        //then
        assertThat(canDraw).isFalse();
    }

    @Test
    public void getSumOfDeck() {
        //given
        Card card2 = new Card(HEART_JACK);
        dealer.add(card2);

        //when
        int sumOfDeck = dealer.getSumOfDeck();

        //then
        assertThat(sumOfDeck).isEqualTo(DECK_SUM);
    }
}
