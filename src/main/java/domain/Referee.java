package domain;

public class Referee {

    private static final String WIN = "승";
    private static final String DRAW = "무";
    private static final String LOSE = "패";
    private static final int BLACK_JACK = 21;

    public String compareSumOfCard(final int firstSum, final int secondSum) {
        return compare(firstSum, secondSum);
    }

    private String compare(final int firstSum, final int secondSum) {
        if (winCase(firstSum, secondSum)) {
            return WIN;
        }
        if (drawCase(firstSum, secondSum)) {
            return DRAW;
        }
        return LOSE;
    }

    private boolean winCase(final int firstSum, final int secondSum) {
        if (firstSum > secondSum && firstSum <= BLACK_JACK) {
            return true;
        }
        return secondSum > BLACK_JACK && firstSum <= BLACK_JACK;
    }

    private boolean drawCase(final int firstSum, final int secondSum) {
        if (firstSum == secondSum) {
            return true;
        }
        return firstSum > BLACK_JACK && secondSum > BLACK_JACK;
    }
}
