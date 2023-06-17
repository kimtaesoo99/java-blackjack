package domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class RefereeTest {

    private static final String DEALER = "딜러";
    private static final String PLAYER = "플레이어";
    private static final String DRAW = "무";
    private static final String LOSE = "패";
    private static final int INIT_AMOUNT = 100;
    private static final int CLOVER_JACK = 38;
    private static final int SPADE_JACK = 25;
    private static final int HEART_ACE = 0;
    private static final int BLACK_JACK = 21;

    @Test
    public void compareSumOfCardDealerWin() {
        //given
        Referee referee = new Referee();
        Dealer dealer = Dealer.create(new Account(new Name(DEALER), new Amount(INIT_AMOUNT)));
        Player player = Player.create(new Account(new Name(PLAYER), new Amount(INIT_AMOUNT)));
        dealer.add(Card.createWithMatchNumber(CLOVER_JACK));
        player.add(Card.createWithMatchNumber(SPADE_JACK));

        //when
        String result = referee.compareSumOfCard(player.getSumOfDeck(), dealer.getSumOfDeck());

        //then
        assertThat(result).isEqualTo(DRAW);
    }

    @Test
    public void compareSumOfCardDealerLose() {
        //given
        Referee referee = new Referee();
        Dealer dealer = Dealer.create(new Account(new Name(DEALER), new Amount(INIT_AMOUNT)));
        Player player = Player.create(new Account(new Name(PLAYER), new Amount(INIT_AMOUNT)));
        dealer.add(Card.createWithMatchNumber(CLOVER_JACK));
        player.add(Card.createWithMatchNumber(HEART_ACE));

        //when
        String result = referee.compareSumOfCard(dealer.getSumOfDeck(), player.getSumOfDeck());

        //then
        assertThat(result).isEqualTo(LOSE);
    }

    @Test
    public void checkBlackJack() {
        //given
        Referee referee = new Referee();

        //when
        boolean blackJack = referee.checkBlackJack(BLACK_JACK);

        //then
        assertThat(blackJack).isTrue();
    }
}
