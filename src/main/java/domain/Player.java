package domain;

public class Player extends Participant{

    private static final int DRAW_STANDARD = 21;

    public Player(final Name name) {
        super(name);
    }

    @Override
    public boolean canDraw() {
        return getSumOfDeck() <= DRAW_STANDARD;
    }
}
