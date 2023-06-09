package domain;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class Participant {

    private final Name name;
    private final Deck deck;

    public Participant(final Name name) {
        this.name = name;
        List<Card> emptyCards = new ArrayList<>();
        this.deck = new Deck(emptyCards);
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
        return name.getName();
    }

    public List<String> getCardsName() {
        List<String> names = new ArrayList<>();
        deck.getCards()
            .forEach(card -> names.add(card.getFullName()));

        return Collections.unmodifiableList(names);
    }

    public abstract boolean canDraw();
}
