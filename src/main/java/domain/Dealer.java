package domain;

public class Dealer extends Participant {

    private static final int DRAW_STANDARD = 16;

    private Amount amount;

    public Dealer(final Name name, final Amount amount) {
        super(name);
        this.amount = amount;
    }

    public void addAmount(int amount) {
        this.amount.add(amount);
    }

    public int getAmount() {
        return amount.getAmount();
    }

    @Override
    public boolean canDraw() {
        return getSumOfDeck() <= DRAW_STANDARD;
    }
}
