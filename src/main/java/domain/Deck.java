package domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {

    private static final String JACK = "J";
    private static final String KING = "K";
    private static final String QUEEN = "Q";
    private static final String ACE = "A";
    private static final int SPECIAL_CARD_VALUE = 10;
    private static final int ACE_CARD_VALUE = 1;
    private static final int BLACK_JACK = 21;
    private static final int INIT_SUM_CARD = 0;

    private final List<Card> cards = new ArrayList<>();

    public void add(Card card) {
        cards.add(card);
    }

    public List<Card> getCards() {
        return Collections.unmodifiableList(cards);
    }

    public int getSumOfValue() {
        int sumOfCard = INIT_SUM_CARD;
        for (final Card card : cards) {
            sumOfCard += getValue(card.getNumber());
        }
        if (hasAce()) {
            return tempAceOfValue(sumOfCard);
        }
        return sumOfCard;
    }

    private int getValue(final String number) {
        if (isSpecialCard(number)) {
            return SPECIAL_CARD_VALUE;
        }
        if (isAceCard(number)) {
            return ACE_CARD_VALUE;
        }
        return Integer.parseInt(number);
    }

    private boolean isSpecialCard(final String number) {
        return number.equals(JACK) || number.equals(KING) || number.equals(QUEEN);
    }

    private boolean isAceCard(final String number) {
        return number.equals(ACE);
    }

    private int tempAceOfValue(final int sumOfCard) {
        if (sumOfCard + SPECIAL_CARD_VALUE <= BLACK_JACK) {
            return sumOfCard + SPECIAL_CARD_VALUE;
        }
        return sumOfCard;
    }

    private boolean hasAce() {
        return cards.stream().anyMatch(card -> isAceCard(card.getNumber()));
    }
}
