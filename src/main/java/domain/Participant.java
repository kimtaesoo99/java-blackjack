package domain;


import java.util.List;

public abstract class Participant {

    private final Name name;
    private final Deck deck;

    public Participant(final Name name) {
        this.name = name;
        this.deck = new Deck();
    }

    public List<Card> getCards() {
        return deck.getCards();
    }

    public int getSumOfDeck() {
        return deck.getSumOfValue();
    }

    public void add(final Card card) {
        deck.add(card);
    }

    public String getName() {
        return name.getName();
    }

    public abstract boolean canDraw();
}
