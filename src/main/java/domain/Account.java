package domain;

public class Account {

    private final Name name;
    private final Amount amount;

    public Account(final Name name, final Amount amount) {
        this.name = name;
        this.amount = amount;
    }

    public void add(final int amount) {
        this.amount.add(amount);
    }

    public void subtract(final int amount) {
        this.amount.subtract(amount);
    }

    public void multiply(final double multiple) {
        this.amount.multiply(multiple);
    }

    public void reverseAmount() {
        this.amount.reverse();
    }

    public void initAmount() {
        this.amount.init();
    }

    public String getName() {
        return name.getName();
    }

    public int getAmount() {
        return amount.getAmount();
    }
}
