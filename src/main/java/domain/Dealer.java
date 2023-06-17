package domain;

import java.util.ArrayList;
import java.util.List;

public class Dealer extends Participant {

    private static final int DRAW_STANDARD = 16;

    private Dealer(final Account account, final Deck deck) {
        super(account, deck);
    }

    public static Dealer create(final Account account) {
        List<Card> emptyCards = new ArrayList<>();
        Deck deck = new Deck(emptyCards);

        return new Dealer(account, deck);
    }

    public void winGame(final int amount) {
        this.account.add(amount);
    }

    public void loseGame(final int amount) {
        this.account.subtract(amount);
    }

    @Override
    public boolean canDraw() {
        return getSumOfDeck() <= DRAW_STANDARD;
    }
}
