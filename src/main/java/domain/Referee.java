package domain;

public class Referee {

    private static final String WIN = "승";
    private static final String DRAW = "무";
    private static final String LOSE = "패";
    private static final int BLACK_JACK = 21;

    public String compareSumOfCard(final int firstSum, final int secondSum) {
        return compare(firstSum, secondSum);
    }

    public boolean checkBlackJack(final int playerCardOfSum) {
        return playerCardOfSum == BLACK_JACK;
    }

    private String compare(final int firstSum, final int secondSum) {
        if (isWinCase(firstSum, secondSum)) {
            return WIN;
        }
        if (isDrawCase(firstSum, secondSum)) {
            return DRAW;
        }
        return LOSE;
    }

    private boolean isWinCase(final int firstSum, final int secondSum) {
        if (firstSum > secondSum && firstSum <= BLACK_JACK) {
            return true;
        }
        return secondSum > BLACK_JACK && firstSum <= BLACK_JACK;
    }

    private boolean isDrawCase(final int firstSum, final int secondSum) {
        if (firstSum == secondSum) {
            return true;
        }
        return firstSum > BLACK_JACK && secondSum > BLACK_JACK;
    }
}
