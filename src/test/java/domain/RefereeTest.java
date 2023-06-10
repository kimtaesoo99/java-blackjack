package domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class RefereeTest {

    private static final String DEALER = "딜러";
    private static final String PLAYER = "플레이어";
    private static final String DRAW = "무";
    private static final String LOSE = "패";
    private static final int INIT_AMOUNT = 100;
    private static final int MINUS_AMOUNT = -100;
    private static final int DOUBLE_AMOUNT = 200;
    private static final int CLOVER_JACK = 38;
    private static final int SPADE_JACK = 25;
    private static final int HEART_ACE = 0;
    private static final int BLACK_JACK = 21;

    @Test
    public void compareSumOfCardDealerWin() {
        //given
        Referee referee = new Referee();
        Dealer dealer = new Dealer(new Name(DEALER), new Amount(INIT_AMOUNT));
        Player player = new Player(new Name(PLAYER), new Amount(INIT_AMOUNT));
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
        Dealer dealer = new Dealer(new Name(DEALER), new Amount(INIT_AMOUNT));
        Player player = new Player(new Name(PLAYER), new Amount(INIT_AMOUNT));
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

    @Test
    public void distributeRevenue() {
        //given
        Referee referee = new Referee();
        Dealer dealer = new Dealer(new Name(DEALER), new Amount(INIT_AMOUNT));
        Player player = new Player(new Name(PLAYER), new Amount(INIT_AMOUNT));

        //when
        referee.distributeRevenue(LOSE, player, dealer);

        //then
        assertThat(player.getAmount()).isEqualTo(MINUS_AMOUNT);
        assertThat(dealer.getAmount()).isEqualTo(DOUBLE_AMOUNT);
    }
}
