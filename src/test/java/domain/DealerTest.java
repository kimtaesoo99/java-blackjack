package domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class DealerTest {

    private static final String DEALER = "딜러";
    private static final String CLOVER_JACK_NAME = "J클로버";
    private static final String SPADE_JACK_NAME = "J스페이드";
    private static final int DECK_SUM = 20;
    private static final int INIT_AMOUNT = 0;
    private static final int CLOVER_JACK = 38;
    private static final int SPADE_JACK = 25;
    private static final int CHANGE_AMOUNT = 100;
    private static final int MINUS = -1;

    private Dealer dealer;
    private Amount amount;

    @BeforeEach
    void beforeEach() {
        Name name = new Name(DEALER);
        amount = new Amount(INIT_AMOUNT);
        Account account = new Account(name, amount);
        dealer = Dealer.create(account);
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

    @Test
    public void winGame() {
        //when
        dealer.winGame(CHANGE_AMOUNT);

        //then
        assertThat(amount.getAmount()).isEqualTo(CHANGE_AMOUNT);
    }

    @Test
    public void loseGame() {
        //when
        dealer.loseGame(CHANGE_AMOUNT);

        //then
        assertThat(amount.getAmount()).isEqualTo(CHANGE_AMOUNT * MINUS);
    }

    @Test
    public void getName() {
        //when
        String name = dealer.getName();

        //then
        assertThat(name).isEqualTo(DEALER);
    }

    @Test
    public void getCardsName() {
        //when
        List<String> cardsName = dealer.getCardsName();

        //then
        assertThat(cardsName).containsExactly(CLOVER_JACK_NAME);
    }

    @Test
    public void initCardSetting() {
        //when
        dealer.initCardSetting(Cards.createAutoCards());

        //then
        assertThat(dealer.getCardsName().size()).isEqualTo(3);
    }

    @Test
    public void add() {
        //given
        Card card = Card.createWithMatchNumber(SPADE_JACK);

        //when
        dealer.add(card);

        //then
        assertThat(dealer.getCardsName()).containsExactly(CLOVER_JACK_NAME, SPADE_JACK_NAME);
    }
}
