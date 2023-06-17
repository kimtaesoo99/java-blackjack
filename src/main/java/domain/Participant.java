package domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class Participant {

    protected final Account account;

    private final Deck deck;

    public Participant(final Account account, final Deck deck) {
        this.account = account;
        this.deck = deck;
    }

    public int getSumOfDeck() {
        return deck.getSumOfValue();
    }

    public void add(final Card card) {
        deck.add(card);
    }

    public void initCardSetting(final Cards cards) {
        deck.add(cards.pick());
        deck.add(cards.pick());
    }

    public String getName() {
        return account.getName();
    }

    public List<String> getCardsName() {
        List<String> names = new ArrayList<>();
        deck.getCards()
            .forEach(card -> names.add(card.getFullName()));

        return Collections.unmodifiableList(names);
    }

    public int getAmount() {
        return account.getAmount();
    }

    public abstract boolean canDraw();
}
