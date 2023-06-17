package domain;

import java.util.ArrayList;
import java.util.List;

public class Player extends Participant {

    private static final int DRAW_STANDARD = 21;

    private Player(final Account account, final Deck deck) {
        super(account, deck);
    }

    public static Player create(final Account account) {
        List<Card> emptyCards = new ArrayList<>();
        Deck deck = new Deck(emptyCards);

        return new Player(account, deck);
    }

    public void multiplyAmount(final double multiple) {
        this.account.multiply(multiple);
    }

    public void loseGame() {
        this.account.reverseAmount();
    }

    public void drawGame() {
        this.account.initAmount();
    }

    @Override
    public boolean canDraw() {
        return getSumOfDeck() <= DRAW_STANDARD;
    }
}
