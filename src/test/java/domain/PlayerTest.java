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
    private static final int ZERO_AMOUNT = 0;
    private static final int INIT_AMOUNT = 100;
    private static final int REVERSE_AMOUNT = -100;

    private Player player;
    private Amount amount;

    @BeforeEach
    void beforeEach() {
        Name name = new Name(DEALER_NAME);
        amount = new Amount(INIT_AMOUNT);
        Account account = new Account(name, amount);
        player = Player.create(account);
        Card card1 = Card.createWithMatchNumber(CLOVER_KING);
        Card card2 = Card.createWithMatchNumber(HEART_JACK);
        player.add(card1);
        player.add(card2);
    }

    @Test
    public void canDrawSuccess() {
        //when
        boolean canDraw = player.canDraw();

        //then
        assertThat(canDraw).isTrue();
    }

    @Test
    public void canDraWFail() {
        //given
        Card card = Card.createWithMatchNumber(HEART_TWO);
        player.add(card);

        //when
        boolean canDraw = player.canDraw();

        //then
        assertThat(canDraw).isFalse();
    }

    @Test
    public void getSumOfDeck() {
        //when
        int sumOfDeck = player.getSumOfDeck();

        //then
        assertThat(sumOfDeck).isEqualTo(DECK_SUM);
    }

    @Test
    public void loseGame() {
        //when
        player.loseGame();

        //then
        assertThat(amount.getAmount()).isEqualTo(REVERSE_AMOUNT);
    }

    @Test
    public void drawGame() {
        //when
        player.drawGame();

        //then
        assertThat(amount.getAmount()).isEqualTo(ZERO_AMOUNT);
    }
}
