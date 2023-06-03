package domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class RefereeTest {

    private static final String DEALER = "딜러";
    private static final String PLAYER = "플레이어";
    private static final String WIN = "승";
    private static final String LOSE = "패";
    private static final int HEART_FOUR = 4;
    private static final int CLOVER_KING = 10;
    private static final int HEART_ACE = 0;
    private static final int HEART_TWO = 40;

    @Test
    public void compareSumOfCard() {
        //given
        Referee referee = new Referee();
        Dealer dealer = new Dealer(new Name(DEALER));
        Player player = new Player(new Name(PLAYER));
        dealer.add(new Card(HEART_FOUR));
        dealer.add(new Card(HEART_ACE));
        player.add(new Card(CLOVER_KING));
        player.add(new Card(HEART_TWO));

        //when
        String result = referee.compareSumOfCard(dealer, player);

        //then
        Assertions.assertThat(result).isEqualTo(LOSE);
    }

    @Test
    public void compareSumOfCard2() {
        //given
        Referee referee = new Referee();
        Dealer dealer = new Dealer(new Name(DEALER));
        Player player = new Player(new Name(PLAYER));
        player.add(new Card(HEART_FOUR));
        player.add(new Card(HEART_ACE));
        dealer.add(new Card(CLOVER_KING));
        dealer.add(new Card(HEART_TWO));

        //when
        String result = referee.compareSumOfCard(dealer, player);

        //then
        Assertions.assertThat(result).isEqualTo(WIN);
    }
}
