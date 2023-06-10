package domain;

import exception.BetAmountException;

import java.util.Objects;

public class Amount {

    private static final String AMOUNT_ERROR_MESSAGE = "1이상의 수여야 합니다.";
    private static final int STANDARD = 0;

    private int amount;

    public Amount(final int amount) {
        validation(amount);
        this.amount = amount;
    }

    public void setAmount(final int amount) {
        this.amount = amount;
    }

    public int getAmount() {
        return amount;
    }

    public void multiply(final double number) {
        amount *= number;
    }

    public void add(final int amount) {
        this.amount += amount;
    }

    private void validation(final int amount) {
        if (amount < STANDARD) {
            throw new BetAmountException(AMOUNT_ERROR_MESSAGE);
        }
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
