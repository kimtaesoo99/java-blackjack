package domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class PlayerTest {

    private static final String DEALER_NAME = "player";
    private static final int CLOVER_KING = 10;
    private static final int HEART_JACK = 12;
    private static final int HEART_TWO = 1;
    private static final int DECK_SUM = 20;

    private Player player;

    @BeforeEach
    void beforeEach() {
        Name name = new Name(DEALER_NAME);
        player = new Player(name);
        Card card1 = new Card(CLOVER_KING);
        Card card2 = new Card(HEART_JACK);
        player.add(card1);
        player.add(card2);
    }

    @Test
    public void canDraw() {
        //given

        //when
        boolean canDraw = player.canDraw();

        //then
        assertThat(canDraw).isTrue();
    }

    @Test
    public void cannotDraW() {
        //given
        Card card = new Card(HEART_TWO);
        player.add(card);

        //when
        boolean canDraw = player.canDraw();

        //then
        assertThat(canDraw).isFalse();
    }

    @Test
    public void getSumOfDeck() {
        //given

        //when
        int sumOfDeck = player.getSumOfDeck();

        //then
        assertThat(sumOfDeck).isEqualTo(DECK_SUM);
    }
}
