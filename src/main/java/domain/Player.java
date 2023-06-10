package domain;

public class Player extends Participant {

    private static final int DRAW_STANDARD = 21;
    private static final double MULTIPLY_NUMBER = 1.5;

    private final Amount amount;

    public Player(final Name name, final Amount amount) {
        super(name);
        this.amount = amount;
    }

    public void multiplyAmount() {
        amount.multiply(MULTIPLY_NUMBER);
    }

    public void setAmount(final int betAmount) {
        this.amount.setAmount(betAmount);
    }

    public int getAmount() {
        return amount.getAmount();
    }

    @Override
    public boolean canDraw() {
        return getSumOfDeck() <= DRAW_STANDARD;
    }
}
