package domain;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class DeckTest {

    private static final int DIAMOND_QUEEN = 11;
    private static final int HEART_JACK = 12;
    private static final int SUM_CARD = 20;

    @Test
    public void getSumOfValue() {
        //given
        Deck deck = new Deck();
        Card card1 = new Card(DIAMOND_QUEEN);
        Card card2 = new Card(HEART_JACK);
        deck.add(card1);
        deck.add(card2);

        //when
        int sumOfValue = deck.getSumOfValue();

        //then
        assertThat(sumOfValue).isEqualTo(SUM_CARD);
    }

    @Test
    public void getCards() {
        //given
        Deck deck = new Deck();
        Card card1 = new Card(DIAMOND_QUEEN);
        Card card2 = new Card(HEART_JACK);
        deck.add(card1);
        deck.add(card2);

        //when
        List<Card> cards = deck.getCards();

        //then
        assertThat(cards).containsExactly(card1, card2);
    }
}
