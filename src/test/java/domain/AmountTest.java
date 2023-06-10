package domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class AmountTest {

    private static final int INIT_AMOUNT = 100;
    private static final int CHANGE_AMOUNT = 150;
    private static final int SET_AMOUNT = 50;
    private static final double MULTIPLE = 1.5;

    private Amount amount;

    @BeforeEach
    void beforeEach() {
        amount = new Amount(INIT_AMOUNT);
    }

    @Test
    void setAmount() {
        //when
        amount.setAmount(SET_AMOUNT);

        //then
        assertThat(amount.getAmount()).isEqualTo(SET_AMOUNT);
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
        amount.add(SET_AMOUNT);

        //then
        assertThat(amount.getAmount()).isEqualTo(INIT_AMOUNT + SET_AMOUNT);
    }
}
