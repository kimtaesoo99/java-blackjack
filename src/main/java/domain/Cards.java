package domain;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Cards {

    private static final int TOP_CARD = 0;
    private static final int CARD_TOTAL_COUNT = 52;
    private static final int FIRST_CARD_NUMBER = 0;

    private final List<Card> cards;

    public static Cards createAutoCards() {
        List<Card> cards = makeShuffleCards();
        return new Cards(cards);
    }

    private Cards(final List<Card> cards) {
        this.cards = cards;
    }

    public Card pick() {
        return cards.remove(TOP_CARD);
    }

    private static List<Card> makeShuffleCards() {
        List<Card> cards = new LinkedList<>();

        for (int number = FIRST_CARD_NUMBER; number < CARD_TOTAL_COUNT; number++) {
            cards.add(Card.createWithMatchNumber(number));
        }

        Collections.shuffle(cards);

        return cards;
    }
}
