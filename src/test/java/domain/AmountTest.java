package domain;

import exception.AmountException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class AmountTest {

    private static final int INIT_AMOUNT = 100;
    private static final int ZERO_AMOUNT = 0;
    private static final int CHANGE_AMOUNT = 150;
    private static final int MINUS_AMOUNT = -150;
    private static final int ADD_AMOUNT = 50;
    private static final double MULTIPLE = 1.5;
    private static final int WRONG_AMOUNT = -1;

    private Amount amount;

    @BeforeEach
    void beforeEach() {
        amount = new Amount(INIT_AMOUNT);
    }

    @Test
    void subtract() {
        //when
        amount.subtract(INIT_AMOUNT);

        //then
        assertThat(amount.getAmount()).isEqualTo(ZERO_AMOUNT);
    }

    @Test
    void multiply() {
        //when
        amount.multiply(MULTIPLE);

        //then
        assertThat(amount.getAmount()).isEqualTo(CHANGE_AMOUNT);
    }

    @Test
    void add() {
        //when
        amount.add(ADD_AMOUNT);

        //then
        assertThat(amount.getAmount()).isEqualTo(INIT_AMOUNT + ADD_AMOUNT);
    }

    @Test
    void reverse() {
        //when
        amount.add(ADD_AMOUNT);
        amount.reverse();

        //then
        assertThat(amount.getAmount()).isEqualTo(MINUS_AMOUNT);
    }

    @Test
    void init() {
        //when
        amount.init();

        //then
        assertThat(amount.getAmount()).isEqualTo(ZERO_AMOUNT);
    }

    @Test
    public void wringAmountException() {
        //when, then
        assertThatThrownBy(() -> new Amount(WRONG_AMOUNT))
            .isInstanceOf(AmountException.class);
    }

    @Test
    public void equals() {
        //given
        Amount amount1 = new Amount(INIT_AMOUNT);
        Amount amount2 = new Amount(INIT_AMOUNT);

        //when
        boolean correct = amount1.equals(amount2);

        //then
        assertThat(correct).isTrue();
    }
}
