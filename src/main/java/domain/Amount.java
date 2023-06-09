package domain;

import exception.AmountException;

import java.util.Objects;

public class Amount {

    private static final String AMOUNT_ERROR_MESSAGE = "1이상의 수여야 합니다.";
    private static final int STANDARD = 0;
    private static final int EMPTY_AMOUNT = 0;
    private static final int MINUS = -1;

    private int amount;

    public Amount(final int amount) {
        validation(amount);
        this.amount = amount;
    }

    private void validation(final int amount) {
        if (amount < STANDARD) {
            throw new AmountException(AMOUNT_ERROR_MESSAGE);
        }
    }

    public void add(final int amount) {
        this.amount += amount;
    }

    public void subtract(final int amount) {
        this.amount -= amount;
    }

    public void init() {
        this.amount = EMPTY_AMOUNT;
    }

    public void multiply(final double multiple) {
        this.amount *= multiple;
    }

    public void reverse() {
        this.amount *= MINUS;
    }

    public int getAmount() {
        return amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Amount amount = (Amount) o;
        return this.amount == amount.amount;
    }

    @Override
    public int hashCode() {
        return Objects.hash(amount);
    }
}
