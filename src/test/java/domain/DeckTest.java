package domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class DeckTest {

    private static final int SUM_CARD = 20;
    private static final int CARDS_SIZE = 2;
    private static final int CLOVER_JACK = 38;
    private static final int SPADE_JACK = 25;

    private Deck deck;

    @BeforeEach
    void beforeEach() {
        deck = new Deck(new ArrayList<>());
        Card card1 = Card.createWithMatchNumber(CLOVER_JACK);
        Card card2 = Card.createWithMatchNumber(SPADE_JACK);
        deck.add(card1);
        deck.add(card2);

    }

    @Test
    public void getSumOfValue() {
        //when
        int sumOfValue = deck.getSumOfValue();

        //then
        assertThat(sumOfValue).isEqualTo(SUM_CARD);
    }

    @Test
    public void getCards() {
        //when
        List<Card> cards = deck.getCards();

        //then
        assertThat(cards.size()).isEqualTo(CARDS_SIZE);
    }
}
