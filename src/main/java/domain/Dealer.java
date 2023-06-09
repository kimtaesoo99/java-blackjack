package domain;

public class Dealer extends Participant {

    private static final int DRAW_STANDARD = 16;

    public Dealer(final Name name) {
        super(name);
    }

    @Override
    public boolean canDraw() {
        return getSumOfDeck() <= DRAW_STANDARD;
    }
}
