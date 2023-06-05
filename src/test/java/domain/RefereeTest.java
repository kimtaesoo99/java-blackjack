package domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class RefereeTest {

    private static final String DEALER = "딜러";
    private static final String PLAYER = "플레이어";
    private static final String WIN = "승";
    private static final String LOSE = "패";

    @Test
    public void compareSumOfCardDealerWin() {
        //given
        Referee referee = new Referee();
        Dealer dealer = new Dealer(new Name(DEALER));
        Player player = new Player(new Name(PLAYER));
        dealer.add(new Card(Pattern.HEART, Number.FOUR));
        player.add(new Card(Pattern.CLOVER, Number.TWO));

        //when
        String result = referee.compareSumOfCard(dealer.getSumOfDeck(), player.getSumOfDeck());

        //then
        Assertions.assertThat(result).isEqualTo(WIN);
    }

    @Test
    public void compareSumOfCardDealerLose() {
        //given
        Referee referee = new Referee();
        Dealer dealer = new Dealer(new Name(DEALER));
        Player player = new Player(new Name(PLAYER));
        dealer.add(new Card(Pattern.CLOVER, Number.JACK));
        player.add(new Card(Pattern.HEART, Number.ACE));

        //when
        String result = referee.compareSumOfCard(dealer.getSumOfDeck(), player.getSumOfDeck());

        //then
        Assertions.assertThat(result).isEqualTo(LOSE);
    }
}
